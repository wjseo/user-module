package com.roouty.usermodule.controller

import com.roouty.usermodule.application.UserApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userApplication: UserApplication,
) {
    // logger
    val logger: Logger = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping("/{id}")
    fun findUserById(
        @PathVariable("id") id: Long
    ): String {
        logger.info("아이디로 사용자 조회: $id")
        val findUser = userApplication.getUserDTO(id)
        logger.info("아이디로 사용자 조회: {}", findUser)
        return findUser.toString()
    }
}