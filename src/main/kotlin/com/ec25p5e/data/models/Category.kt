package com.ec25p5e.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Category(
    val userId: String,
    val name: String,
    val color: Int,
    val timestamp: Long,
    @BsonId
    val categoryId: String = ObjectId().toString()
)