package com.ant.webPage.util;


import com.ant.webPage.tool.HttpTool;

import java.util.HashMap;
import java.util.Map;

public class Ding {

    private static final String token = "b621c865e76c6a6caff9ec8919019d04c52629361332db375f146c0e4b0c95c6";

    public static String InfoDing(String text,String[] phone){
        Map map = new HashMap();
        map.put("msgtype","text");
        map.put("text",new HashMap(){{put("content","【通知】"+text);}});
        if (phone != null)
        map.put("at",new HashMap(){{put("atMobiles",phone);}});
        return HttpTool.post("https://oapi.dingtalk.com/robot/send?access_token="+token,map);
    }

    public static String WarnDing(String text,String[] phone){
        Map map = new HashMap();
        map.put("msgtype","text");
        map.put("text",new HashMap(){{put("content","【警告】"+text);}});
        if (phone != null)
        map.put("at",new HashMap(){{put("atMobiles",phone);}});
        return HttpTool.post("https://oapi.dingtalk.com/robot/send?access_token="+token,map);
    }


    public static String ErrorDing(String text,String[] phone){
        Map map = new HashMap();
        map.put("msgtype","text");
        map.put("text",new HashMap(){{put("content","【错误】"+text);}});
        if (phone != null)
        map.put("at",new HashMap(){{put("atMobiles",phone);}});
        return HttpTool.post("https://oapi.dingtalk.com/robot/send?access_token="+token,map);
    }

}
