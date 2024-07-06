package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")

public class LikeController {
    private final LikeService likeService;


}
