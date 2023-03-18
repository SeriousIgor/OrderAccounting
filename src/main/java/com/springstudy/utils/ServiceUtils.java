package com.springstudy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ServiceUtils {
    public static Map<String, Integer> getPagination(Integer pageNumber, Integer pageSize) {
        int offset;
        int limit;
        if ((pageNumber == null) || (pageNumber < 1)) {
            pageNumber = 1;
        }
        if ((pageSize == null) || pageSize < 1) {
            offset = 0;
            limit = Integer.MAX_VALUE;
        } else {
            offset = (pageNumber - 1) * pageSize;
            limit = pageNumber * pageSize;
        }
        return Map.of("offset", offset, "limit", limit);
    }

    public static Object getParserRecordFromJson(String jsonValue, Class modelClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonValue, modelClass);
    }
}
