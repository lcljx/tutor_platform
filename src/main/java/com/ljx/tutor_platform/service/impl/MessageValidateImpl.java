package com.ljx.tutor_platform.service.impl;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.stereotype.Service;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.ljx.tutor_platform.service.MessageValidate;
@Service
public class MessageValidateImpl implements MessageValidate{
	// 短信应用SDK AppID
	private static int appid = 1400180887; // 1400开头

	// 短信应用SDK AppKey
	private static String appkey = "7f16cf9087402e937319dceeacd718f4";

    // 短信模板ID，需要在短信应用中申请
    // NOTE: 这里的模板ID`7839`只是一个示例，
    // 真实的模板ID需要在短信控制台中申请
    private static int templateId = 267297;

    // 签名
    // NOTE: 这里的签名"腾讯云"只是一个示例，
    // 真实的签名需要在短信控制台中申请，另外
    // 签名参数使用的是`签名内容`，而不是`签名ID`
    private static String smsSign = "";
	
	public String sendMsg(String[] phoneNumbers,HttpServletRequest request) {
		JSONObject json = null;
		SmsMultiSenderResult result = null;
		// 需要发送短信的手机号码:phoneNumbers
		// 指定模板ID单发短信
        try {
        	String code = getCode();
            String[] params = {code};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            result =  msender.sendWithParam("86", phoneNumbers,
                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            HttpSession session = request.getSession();
            json = new JSONObject();
            json.put("code", code);
            json.put("createTime", System.currentTimeMillis());
            // 将认证码存入SESSION
            request.getSession().setAttribute("code", json);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
		return result.toString();
	}
	
	public String getCode() {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for(int i=0;i<6;i++) {
			sb.append(r.nextInt(10));
		}
		return sb.toString();
	}
}
