//package com.example.server.infrastructure.redis.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author duchieu212
// */
//public interface RedisService {
//
//    void set(String key, String value);
//
//    Object get(String key);
//
//    void setTimeToLive(String key, long timeOut);
//
//    void hashSet(String key, String field, Object value);
//
//    Object hashGet(String key, String field);
//
//    Map<String, Object> getField(String key);
//
//    <T> void listSet(String key, List<T> values) throws JsonProcessingException;
//
//    <T> List<T> listGet(String key, Class<T> clazz);
//
//    List<Object> hashGetByFieldPrefix(String key, String fieldPrefix);
//
//    void delete(String key);
//
//    void delete(String key, String field);
//
//    void delete(String key, List<String> fields);
//
//    void clear();
//
//}
