package com.springstudy.utils;

import net.minidev.json.JSONObject;

import java.util.Map;

public class MetricsCalculationUtil {
    public static String getReportJSON(Map<String, Object> jsonParamMap) {
        JSONObject jsonObject = new JSONObject();
        for (String key : jsonParamMap.keySet()) {
            jsonObject.put(key, jsonParamMap.get(key));
        }
        return jsonObject.toJSONString();
    }
}
