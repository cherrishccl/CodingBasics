package com.boot.basics.coding.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2021/3/29 15:32
 * @Version 1.0
 * @Description Crawler1
 */
public class CrawlerCq315house {
    public static void main(String[] args) throws IOException {
        String url = "http://www.cq315house.com/WebService/WebFormService.aspx/getParamDatas";
        Map<String, String> param = new HashMap<>(8);
        param.put("siteid","");
        param.put("useType","");
        param.put("projectname","");
        param.put("minrow","1");
        param.put("maxrow","2");
        param.put("location","");
        param.put("entName","");
        param.put("areaType","");

        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "www.cq315house.com");
        headers.put("Origin", "http://www.cq315house.com");
        headers.put("Referer", "");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.63");

        String jsonParamStr = "{\"siteid\":\"\",\"useType\":\"\",\"areaType\":\"\",\"projectname\":\"\",\"entName\":\"\",\"location\":\"\",\"minrow\":\"1\",\"maxrow\":\"11\"}";
        jsonParamStr = JSON.toJSONString(param);
        String result = HttpClientUtils.post(url, null, jsonParamStr);
        JSONObject resultJsonObject = JSONObject.parseObject(result);
        String dstr = resultJsonObject.getString("d");

        List<LouPanInfo> infos = JSONArray.parseArray(dstr, LouPanInfo.class);
        if(null != infos && infos.size() > 0){
            String showRoomUrl = "http://www.cq315house.com/HtmlPage/ShowRooms.html?buildingid=%s&block=%s";
            String roomStatusUrl = "http://www.cq315house.com/WebService/WebFormService.aspx/GetJsonStatus";
            String paraStr = "{\"para\":\"\"}";
            for(LouPanInfo info : infos){
                if(null != info.getBlockname() && !"".equals(info.getBlockname())){
                    String[] blocknames = info.getBlockname().split(",");
                    for(String blockname : blocknames){
                        String shoRoomStr = String.format(showRoomUrl, info.getBuildingid(), blockname);
                        headers.put("Referer", shoRoomStr);
                        String result1 = HttpClientUtils.post(roomStatusUrl, headers, paraStr);
                        System.out.println(result1);
                    }
                }
            }
        }
    }
}
