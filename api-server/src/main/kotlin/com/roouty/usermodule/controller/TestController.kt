package com.roouty.usermodule.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TestController {
    @RequestMapping("/test")
    fun test(): String {
        return "test"
    }
}