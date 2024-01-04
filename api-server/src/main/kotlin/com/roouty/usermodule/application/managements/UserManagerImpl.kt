package com.roouty.usermodule.application.managements

import com.roouty.usermodule.User
import com.roouty.usermodule.UserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserManagerImpl(private val userRepository: UserRepository) : UserManager {
    override fun createUser(user: User): User =
        userRepository.save(user)

    override fun findById(id: Long): Optional<User> =
        userRepository.findById(id)

    override fun updateUser(user: User): User =
        userRepository.save(user)
}