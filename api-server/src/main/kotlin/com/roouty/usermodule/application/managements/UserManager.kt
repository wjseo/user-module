package com.roouty.usermodule.application.managements

import com.roouty.usermodule.User
import java.util.*

interface UserManager {
    fun createUser(user: User): User
    fun findById(id: Long): Optional<User>
    fun updateUser(user: User): User
}