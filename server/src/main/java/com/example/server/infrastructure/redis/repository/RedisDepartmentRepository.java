//package com.example.server.infrastructure.redis.repository;
//
//import com.example.server.entity.Departments;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class RedisDepartmentRepository {
//
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    public void save(Departments departments) {
//        System.err.println("savaaaaaaaaaaaaaaa");
//        redisTemplate.opsForValue().set(String.valueOf(departments.getId()), departments);
//    }
//
//    public Departments findById(String id) {
//        return (Departments) redisTemplate.opsForValue().get(String.valueOf(id));
//    }
//}
