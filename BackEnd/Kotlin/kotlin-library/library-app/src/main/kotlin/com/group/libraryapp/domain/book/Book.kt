package com.group.libraryapp.domain.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book(
    val name: String, // nullable=false 를 대체

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // default 파라미터는 가장 마지막에 있는 게 관례
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("책 이름은 공백일 수 없습니다.")
        }
    }
}