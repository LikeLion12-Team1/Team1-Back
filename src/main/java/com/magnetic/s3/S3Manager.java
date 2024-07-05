//package com.magnetic.s3;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.magnetic.config.S3Config;
//import com.magnetic.s3.entity.Uuid;
//import com.magnetic.s3.repository.UuidRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class S3Manager {
//
//    private final AmazonS3 amazonS3;
//
//    private final S3Config s3Config;
//
//    private final UuidRepository uuidRepository;
//
//    public String uploadFile(String keyName, MultipartFile file) {
//        return null;
//    }
//}