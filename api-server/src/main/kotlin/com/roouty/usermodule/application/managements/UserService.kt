package com.roouty.usermodule.application.managements

import com.roouty.usermodule.User
import com.roouty.usermodule.UserDTO
import org.springframework.stereotype.Service

/**
 * @note 도메인과 관련된 로직만 작성한다.
 * */
@Service
class UserService(
    private val userManager: UserManager,
) {
    fun findById(id: Long): User = getUser(id)

    fun createUser(name: String, age: Int): User =
        userManager.createUser(createUserEntity(name, age))

    fun updateUser(id: Long, name: String, age: Int): User =
        getUser(id).let {
            it.updateUser(name, age)
            userManager.updateUser(it)
        }

    fun getUserDTO(id: Long): UserDTO = getUser(id).toUserDTO()
    private fun createUserEntity(name: String, age: Int) = User(name, age)

    private fun getUser(id: Long): User =
        userManager.findById(id).orElseThrow { RuntimeException("$id 를 가진 사용자를 찾을 수 없습니다.") }
}