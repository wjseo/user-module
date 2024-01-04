package com.roouty.usermodule

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "user", schema = "roouty")
class User(
    @Column(name = "name")
    var name: String = "",

    @Column(name = "age")
    var age: Int = 0,

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    val createDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "update_date")
    val updateDate: LocalDateTime = LocalDateTime.now()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null

    fun toUserDTO(): UserDTO {
        return UserDTO(name = name, age = age)
    }

    fun updateUser(name: String, age: Int): User {
        this.name = name
        this.age = age
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (age != other.age) return false
        if (createDate != other.createDate) return false
        if (updateDate != other.updateDate) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + createDate.hashCode()
        result = 31 * result + updateDate.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

}
