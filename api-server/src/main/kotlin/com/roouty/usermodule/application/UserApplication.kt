package com.roouty.usermodule.application

import com.roouty.usermodule.UserDTO
import com.roouty.usermodule.application.managements.UserService
import org.springframework.stereotype.Service

@Service
class UserApplication(
    private val userService: UserService
) {
    fun getUserDTO(id: Long): UserDTO = userService.getUserDTO(id)
    fun createUser(name: String, age: Int) = userService.createUser(name, age)
    fun updateUser(id: Long, name: String, age: Int) = userService.updateUser(id, name, age)
}