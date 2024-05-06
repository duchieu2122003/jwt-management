//package com.example.server.infrastructure.listener;
//
//import com.example.server.entity.Departments;
//import com.example.server.infrastructure.redis.repository.RedisDepartmentRepository;
//import jakarta.persistence.PostUpdate;
//import jakarta.persistence.PreUpdate;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author duchieu212
// */
//@Slf4j
//@Component
//public class DepartmentsListener {
//
//    private RedisDepartmentRepository redisDepartmentRepository;
//
//    @Autowired
//    public void setRedisDepartmentRepository(RedisDepartmentRepository redisDepartmentRepository) {
//        this.redisDepartmentRepository = redisDepartmentRepository;
//    }
//
//    @PreUpdate
//    public void preUpdate(Departments departments) {
//        log.info("Trước UPDATE DEPARTMENTS");
//    }
//
//    @PostUpdate
//    public void postUpdate(Departments departments) {
//        log.info("POST UPDATEEEEEEEEE DEPARTMENTS");
//        System.err.println("aaaaaaaaaaaaaaaaaaaa");
//        System.err.println(departments.toString());
//    }
//}
