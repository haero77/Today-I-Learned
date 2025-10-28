package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

@Entity
class UserLoanHistory constructor(
  @ManyToOne
  val user: User,

  val bookName: String,
) {
  var isReturn: Boolean = false

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null // default 파라미터는 가장 마지막에 있는 게 관례

  fun doReturn() {
    this.isReturn = true
  }
}