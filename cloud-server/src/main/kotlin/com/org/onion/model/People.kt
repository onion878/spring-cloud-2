package com.org.onion.model

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "people")
data class People(
        var name: String? = null,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)