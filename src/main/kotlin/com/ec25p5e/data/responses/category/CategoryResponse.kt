package com.ec25p5e.data.responses.category

data class CategoryResponse(
    val userId: String,
    val categoryId: String,
    val name: String,
    val color: Int,
    val timestamp: Long
)