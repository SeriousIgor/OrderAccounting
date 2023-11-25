package com.springstudy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;

public class ServiceUtil {

    public static Pageable getPagination(Integer pageNumber, Integer pageSize) {
        boolean isInvalidPageNumber = (pageNumber == null || pageNumber < 0);
        boolean isInvalidPageSize = (pageSize == null || pageSize < 0);
        Integer pgNum = isInvalidPageNumber ? Integer.valueOf(0) : pageNumber;
        Integer pgSize = isInvalidPageSize ? Integer.MAX_VALUE : pageSize;
        return PageRequest.of(pgNum, pgSize);
    }

    public static <T>String getJsonFromObject(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static <T>T getParserRecordFromJson(String jsonValue, Class<T> modelClass) throws JsonProcessingException, IllegalAccessException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JsonNode jsonNode = objectMapper.readTree(jsonValue);

        T result = objectMapper.treeToValue(jsonNode, modelClass);
        handleNestedObjects(result, jsonNode, objectMapper);

        return result;
    }

    private static void handleNestedObjects(Object result, JsonNode jsonNode, ObjectMapper objectMapper) throws JsonProcessingException, IllegalAccessException {
        for (Field field : result.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (!field.getType().isPrimitive() && jsonNode.has(field.getName())) {
                field.set(result, objectMapper.treeToValue(jsonNode.path(field.getName()), field.getType()));
            }
        }
    }
}
