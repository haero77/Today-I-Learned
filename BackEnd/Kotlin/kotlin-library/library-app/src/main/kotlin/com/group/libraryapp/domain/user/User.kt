package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import javax.persistence.*

@Entity
class User constructor(
  var name: String,

  val age: Int? = null,
) {
  @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
  val userLoanHistories: MutableList<UserLoanHistory> = ArrayList()

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null

  init {
    if (name.isBlank()) {
      throw IllegalArgumentException("이름은 공백일 수 없습니다.")
    }
  }

  fun updateName(newName: String) {
    if (newName.isBlank()) {
      throw IllegalArgumentException("이름은 공백일 수 없습니다.")
    }
    this.name = newName
  }

  fun loanBook(book: Book) {
    val loanHistory = UserLoanHistory(
      user = this,
      bookName = book.name,
    )
    this.userLoanHistories.add(loanHistory)
  }

  fun returnBook(bookName: String) {
    val targetHistory = this.userLoanHistories.first { it.bookName == bookName }
    targetHistory.doReturn()
  }
}