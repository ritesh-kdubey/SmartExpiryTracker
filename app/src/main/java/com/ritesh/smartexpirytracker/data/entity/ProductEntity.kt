package com.ritesh.smartexpirytracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,

    val productName: String,
    val category: String,
    val quantity: Int,
    val expiryDate: String
)