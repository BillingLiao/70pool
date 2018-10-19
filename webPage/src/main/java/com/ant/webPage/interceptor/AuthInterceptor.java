package com.ant.webPage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ant.common.tool.CodeConstant;
import com.ant.entity.User;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.CheckTool;

import com.ant.webPage.tool.HttpTool;
import com.ant.webPage.tool.TokenTool;
import com.ant.webPage.util.Ding;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限拦截器
 * @author Billing
 *
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		
		if(CheckTool.isString(token)) {
			int userId = TokenTool.verify(token);
			ValueOperations<String, User> operations = null;
			boolean exists = false;
			try {
				operations = redisTemplate.opsForValue();
			    exists = redisTemplate.hasKey("user:"+userId);
			}catch (Exception e){
				Ding.ErrorDing("redis出现了问题，请速去处理！",new String[]{"17608170325"});
			}
			if(exists) {
				//有缓存的情况，直接从缓存中取
				User user = operations.get("user:" + userId);
				if(user.getStatus() == 0) {
					//被锁定的情况，从数据库中确认
					user = userService.selectById(userId);
					operations.set("user:"+userId, user);
					HttpTool.jsonMsg(response, Result.error(CodeConstant.LOCK,"您被管理员锁定，请联系管理员处理"));
				} else {
					request.getSession().setAttribute("user", user);
					return true;
				}
			} else {
				//没有缓存的情况，从数据库中取
				if(userId > 0) {
					User user = userService.selectById(userId);
					if(user != null) {
						if(user.getStatus() == 0) {
							//被锁定的情况
							HttpTool.jsonMsg(response, Result.error(CodeConstant.LOCK,"您被管理员锁定，请联系管理员处理"));
						} else {
							request.getSession().setAttribute("user", user);
							operations.set("user:"+userId, user);
							return true;
						}
					} else {
						HttpTool.jsonMsg(response, Result.error(CodeConstant.ERR_TOKEN,"没有找到指定用户"));
					}
				} else if(userId == 0) {
					HttpTool.jsonMsg(response, Result.error(CodeConstant.ERR_AUTH,"token超时，请重新登录"));
				} else {
					HttpTool.jsonMsg(response, Result.error(CodeConstant.ERR_AUTH,"token有误"));
				}
			}
		}else {
			//没有带token的情况
			HttpTool.jsonMsg(response, Result.error(CodeConstant.ERR_AUTH,"请先登录"));
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(response.getStatus() == 200) {
			//访问正常的情况
			return ;
		} else if(response.getStatus() == 500) {
			Result.error("Server Error");
			//msg.set("Server Error", CodeConstant.ERROR, null);
		} else if(response.getStatus() == 404) {
			Result.error("URL Error");
			//msg.set("URL Error", CodeConstant.ERROR, null);
		} else {
			Result.error("Nuknow Error:" + response.getStatus());
			//msg.set("Nuknow Error:" + response.getStatus(), CodeConstant.ERROR, null);
		}
//		modelAndView.addObject(msg);
//		HttpTool.jsonMsg(response, msg);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
