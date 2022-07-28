package com.sample.bookstore.book

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(
    val name: String,
    val authorName: String,
    @Embedded
    val price: Money,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
) {
    fun toResponse():BookResponse {
        return BookResponse(id,name,authorName,price)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
