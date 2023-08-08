package com.ec25p5e.data.responses.category

data class CategoryResponse(
    val userId: String,
    val remoteId: String,
    val localId: Int,
    val name: String,
    val color: Int,
    val timestamp: Long
)