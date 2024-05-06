//package com.example.server.infrastructure.redis.service.impl;
//
//import com.example.server.infrastructure.redis.service.RedisService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author duchieu212
// */
//@Service
//public class RedisServiceImpl implements RedisService {
//
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    private final HashOperations<String, String, Object> hashOperations;
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        this.hashOperations = redisTemplate.opsForHash();
//    }
//
//    @Override
//    public void set(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    @Override
//    public void setTimeToLive(String key, long timeOut) {
////        redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void hashSet(String key, String field, Object value) {
//        hashOperations.put(key, field, value);
//    }
//
//    @Override
//    public Object hashGet(String key, String field) {
//        return hashOperations.get(key, field);
//    }
//
//    @Override
//    public Object get(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    @Override
//    public Map<String, Object> getField(String key) {
//        return hashOperations.entries(key);
//    }
//
//    @Override
//    public List<Object> hashGetByFieldPrefix(String key, String fieldPrefix) {
//        List<Object> list = new ArrayList<>();
//        Map<String, Object> hashEntries = hashOperations.entries(key);
//        for (Map.Entry<String, Object> entry : hashEntries.entrySet()) {
//            list.add(entry.getValue());
//        }
//        return list;
//    }
//
//    @Override
//    public <T> void listSet(String key, List<T> values) {
//        for (T value : values) {
//            String jsonValue = null;
//            try {
//                jsonValue = objectMapper.writeValueAsString(value);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            redisTemplate.opsForList().leftPush(key, jsonValue);
//        }
//    }
//
//    @Override
//    public <T> List<T> listGet(String key, Class<T> clazz) {
//        List<T> resultList = new ArrayList<>();
//        List<Object> jsonStringList = redisTemplate.opsForList().range(key, 0, -1);
//        if (jsonStringList != null) {
//            for (Object obj : jsonStringList) {
//                try {
//                    if (obj instanceof String) {
//                        String jsonString = (String) obj;
//                        T result = objectMapper.readValue(jsonString, clazz);
//                        resultList.add(result);
//                    }
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return resultList;
//    }
//
//    @Override
//    public void delete(String key) {
//        redisTemplate.delete(key);
//    }
//
//    @Override
//    public void delete(String key, String field) {
//        hashOperations.delete(key, field);
//    }
//
//    @Override
//    public void delete(String key, List<String> fields) {
//        for (String field : fields) {
//            hashOperations.delete(key, field);
//        }
//    }
//
//    @Override
//    public void clear() {
//        redisTemplate.getConnectionFactory().getConnection().flushAll();
//    }
//
////    @Override
////    public <T> List<T> jsonToList(String json, Class<T> clazz) {
////        try {
////            TypeFactory typeFactory = objectMapper.getTypeFactory();
////            JavaType listType = typeFactory.constructCollectionType(List.class, clazz);
////            return objectMapper.readValue(json, listType);
////        } catch (JsonProcessingException e) {
////            e.printStackTrace();
////            return null;
////        }
////    }
//
//}
