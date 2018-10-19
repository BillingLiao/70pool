package com.ant.webPage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenUtil {

    //自定义的秘钥
    private static final String PRIVATE_KEY = "asf234afa.s;//";

    //设定时效，单位毫秒（一天，根据需求自定义）
    private static final int MAX_TIME = 24 * 60 * 60 * 1000;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 生成token，时间+用户id+用户名/手机/邮箱 采用AES对称加密方式
     *
     * @param userId
     * @return
     */
    public static final String getToken(int userId, String userName) {
        long time = new Date().getTime();
        //加密内容使用|分隔开来
        String content = time + "|" + userId + "|" + userName;
        String password = AESUtil.AESEncode(PRIVATE_KEY, content);
        return password;
    }

    /**
     * 解密token，验证时效
     * @param tokenString
     * @return
     */
    public static boolean checkToken(String tokenString) {
        try {
            //解密token字符串
            String content = AESUtil.AESDncode(PRIVATE_KEY, tokenString);
            System.out.println("加密前conten = " + content);
            //验证时效性
            String[] contents = content.split("\\|");
            long tokenTime = Long.parseLong(contents[0]);
            long nowTime = new Date().getTime();
            System.out.println(nowTime - tokenTime);
            if (nowTime - tokenTime > MAX_TIME) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
//        int userId = 2;
//        String userName = "577207298@qq.com";
//        String tokenString = TokenUtil.getToken(userId, userName);
        TokenUtil.checkToken("+P9KHGGb5jrJcN61zTRcUWzJTv/p6IPwXF/uwVy3CUROOk2jAfOCKn2iomxU2D8r4GWO5vpc0flze1MSf3ievw==");
//        System.out.println("加密后tokenString = " + tokenString);
//        boolean flag = TokenUtil.checkToken(tokenString);
//        System.out.println("flag = " + flag);
    }
}
