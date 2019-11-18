package com.itstyle.interceptor;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.itstyle.common.ResultType;
import com.itstyle.common.jwt.JWTUtils;
import com.itstyle.common.redis.RedisTokenManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Value("${isEnableJwt}")
    private String isEnableJwt;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if ("true".equals(isEnableJwt)){
            return true;
        }
        OutputStream out = response.getOutputStream();
        response.setContentType("application/json;charset=UTF-8");
        try {
            String userid = request.getHeader("userid");
            String token = request.getHeader("token");

            if ((StringUtils.isBlank(userid)) || (StringUtils.isBlank(token))) {
                String result = getErrorJson("请登录", ResultType.login.getNeedLogin());

                out.write(result.getBytes("UTF-8"));
                out.flush();
                out.close();
                return false;
            }

            Map map = JWTUtils.validateToken(token);
            if (map == null) {
                String result = getErrorJson("令牌失效,请重新登录", ResultType.login.getTokenInvalid());
                out.write(result.getBytes("UTF-8"));
                out.flush();
                out.close();
                return false;
            }

            if (!RedisTokenManager.checkToken(userid, token)) {
                String result = getErrorJson("令牌失效,请重新登录", ResultType.login.getTokenInvalid());
                out.write(result.getBytes("UTF-8"));
                out.flush();
                out.close();

                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            String exception = e.toString().replace(":", "").replace(",", " ");

            String result = getErrorJson(exception, ResultType.login.getNeedLogin());
            out.write(result.getBytes("UTF-8"));
            out.flush();
            out.close();
        }
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    public static String getErrorJson(String resultMessage, Object resultStatus) {
        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("status", resultStatus);
            jsonResult.put("message", resultMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResult.toJSONString();
    }
}