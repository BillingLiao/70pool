package com.ant.webPage.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.Proxy;
import com.ant.entity.User;
import com.ant.webPage.service.ProxyService;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.CheckTool;
import com.ant.webPage.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

import static com.ant.webPage.tool.CheckTool.isPhone;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired private RedisUtils redisUtils;

    @Autowired
    private ProxyService proxyService;

    /**
     * 手机登录
     * @param telphone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("telphone") String telphone, @RequestParam("password") String password){
        return userService.login(telphone,password);
    }

    /**
     * 发送验证码
     */
    @PostMapping("/sendCode")
    public Result sendCode(@RequestParam String phone,@RequestParam Integer msgType) throws ClientException {
        ValidatorUtils.validateEntity(phone);
        String tempCode = "";
        //注册发送验证码时，要判断用户是否有账号
        if(msgType == 0){
            User user = userService.findByPhone(phone);
            if(user != null){
                return Result.error("您已经注册过账号");
            }
            tempCode = "SMS_144375029";
        }
        else if(msgType == 1){
            User user = userService.findByPhone(phone);
            if(user == null){
                return Result.error("您还未注册过账号，请先注册");
            }
            tempCode = "SMS_146802920";
        }

        if( redisUtils.get(Constant.RESET_PASS_SMS_CODE_KEY+phone)!= null) {
            return Result.error("发送验证码比较频繁，等一分钟之后再试试");
        }
        String verificationCode = RandomUtil.randomNumbers(4);
        SendSmsResponse response = Alimsg.sendSms(phone,tempCode,verificationCode);
        String resultCode = response.getCode();
        if(!resultCode.equals("OK")){
            return Result.error("获取验证码失败，请重新获取");
        }
        redisUtils.set(Constant.RESET_PASS_SMS_CODE_KEY+phone, verificationCode, 60 * 1);
        redisUtils.set(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone, verificationCode, 60 * 5);
        return Result.ok("验证码获取成功");
    }

    /**
     * 注册
     * @param phone
     * @param password
     * @param verification
     * @return
     */
    @PostMapping("/register")
    @Transactional(rollbackFor=Exception.class)
    public Result register(@RequestParam String phone,@RequestParam String password,@RequestParam String verification,@RequestParam(required = false) String invitationCode){
        Assert.notBlank(verification,"短信验证码不能为空");
        String codeRedis = redisUtils.get(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone);
        if(codeRedis == null) {
            return Result.error("验证码已经失效，请重新获取");
        }
        if(!verification.equals(codeRedis)) {
            return Result.error("输入验证码有误，请重新填写");
        }
        if(password==null || "".equals(password)){
            return Result.error("密码不能为空");
        }
        String salt = RandomUtil.randomString(20);
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(password);
        BigDecimal btc = new BigDecimal(0);
        BigDecimal cny = new BigDecimal(0);

        Date createTime = new Date();
        User user = new User(phone,psw,salt,btc,cny,1,createTime);
        //判断有无邀请码
        if(CheckTool.isString(invitationCode)) {
            //判断能否找到邀请用户
            User fatherUser = userService.selectByInvitationCode(invitationCode);
            if (fatherUser == null) {
                return Result.error("邀请码有误，请重新填写");
            }else{
                userService.insert(user);
                //设置邀请码 5位随机数
                String invitationCodeNew = SerialNumberUtil.toSerialNumber(user.getUserId());
                user.setInvitationCode(invitationCodeNew);
                userService.updateAllColumnById(user);
                Proxy fatherProxy = new Proxy();
                fatherProxy.setUserId(fatherUser.getUserId());
                //设置子代理
                fatherProxy.setSonId(user.getUserId());
                proxyService.insert(fatherProxy);
            }

        }else{
            userService.insert(user);
            //设置邀请码 5位随机数
            String invitationCodeNew = SerialNumberUtil.toSerialNumber(user.getUserId());
            user.setInvitationCode(invitationCodeNew);
            userService.updateAllColumnById(user);
        }
        return userService.login(phone,password);
    }


    /**
     * 修改密码
     * @param phone 手机号码
     * @param password  密码
     * @param confirmPassword   确认密码
     * @param verification  验证码
     * @return
     */
    @PostMapping("/resetPass")
    public Result resetPass(@RequestParam String phone,@RequestParam String password,@RequestParam String confirmPassword,@RequestParam String verification){
        ValidatorUtils.validateEntity(phone);
        Assert.notBlank(verification,"短信验证码不能为空");
        String codeRedis = redisUtils.get(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone);
        if(codeRedis == null) {
            return Result.error("验证码已经失效，请重新获取");
        }
        if(!verification.equals(codeRedis)) {
            return Result.error("输入验证码有误，请重新填写");
        }

        if(password==null || "".equals(password)){
            return Result.error("新密码不能为空");
        }
        if(confirmPassword==null || "".equals(confirmPassword)){
            return Result.error("请确认新密码");
        }
        if(!password.equals(confirmPassword)){
            return Result.error("二次密码输入不一致,请重新输入新密码");
        }
        User user = userService.findByPhone(phone);
        String salt = user.getSalt();
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(password);
        user.setPassword(psw);
        userService.updateAllColumnById(user);
        return Result.ok("修改密码成功");
    }

    /**
     * 无验证码注册
     * @param phone
     * @param password
     * @param invitationCode
     * @return
     */
    @PostMapping("/registerNoCode")
    public Result registerNoCode(@RequestParam String phone,@RequestParam String password,@RequestParam(required = false) String invitationCode){
        if(phone == null || "".equals(phone)){
            return Result.error("手机号码不能为空");
        }else if(!isPhone(phone)){
            return Result.error("手机号码格式不对");
        }
        if(password==null || "".equals(password)){
            return Result.error("密码不能为空");
        }
        User invitationUser = userService.findByPhone(phone);
        if(invitationUser != null){
            return Result.error("您已经注册过账号，请登录");
        }
        String salt = RandomUtil.randomString(20);
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(password);
        BigDecimal btc = new BigDecimal(0);
        BigDecimal cny = new BigDecimal(0);

        Date createTime = new Date();
        User user = new User(phone,psw,salt,btc,cny,1,createTime);
        userService.insert(user);
        //设置邀请码 6位随机数
        String invitationCodeNew = SerialNumberUtil.toSerialNumber(user.getUserId());
        user.setInvitationCode(invitationCodeNew);
        userService.updateAllColumnById(user);
        //判断有无邀请码
        if(invitationCode != null) {
            //判断能否找到邀请用户
            User fatherUser = userService.selectByInvitationCode(invitationCode);
            if (fatherUser == null) {
                return Result.error("验证码有误，请重新填写");
            }else{
                Proxy fatherProxy = new Proxy();
                fatherProxy.setUserId(fatherUser.getUserId());
                //设置子代理
                fatherProxy.setSonId(user.getUserId());
                proxyService.insert(fatherProxy);
            }

        }
        return Result.ok();
    }

    /**
     * 无验证码修改密码
     * @param phone
     * @param password
     * @param confirmPassword
     * @return
     */
    @PostMapping("/resetPassNoCode")
    public Result resetPassNoCode(@RequestParam String phone,@RequestParam String password,@RequestParam String confirmPassword){
        if(phone == null || "".equals(phone)){
            return Result.error("手机号码不能为空");
        }else if(!isPhone(phone)){
            return Result.error("手机号码格式不对");
        }
        if(password==null || "".equals(password)){
            return Result.error("新密码不能为空");
        }
        if(confirmPassword==null || "".equals(confirmPassword)){
            return Result.error("请确认新密码");
        }
        if(!password.equals(confirmPassword)){
            return Result.error("二次密码输入不一致,请重新输入新密码");
        }
        User user = userService.findByPhone(phone);
        if(user == null){
            return Result.error("您还未注册过账号，请先注册");
        }
        String salt = user.getSalt();
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(password);
        user.setPassword(psw);
        userService.updateAllColumnById(user);
        return Result.ok("修改密码成功");
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        //ShiroUtils.logout();
        return "redirect:login.html";
    }
}
