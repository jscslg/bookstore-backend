package com.sample.bookstore.book

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Money (
        @Column(columnDefinition = "NUMERIC")
        val amount: Int,
        val currency:String
        )
