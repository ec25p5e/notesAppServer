package com.ec25p5e.data.models

import com.ec25p5e.data.requests.PushNotesRequest
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.litote.kmongo.push

data class Note(
    val userId: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val isArchived: Boolean,
    val categoryId: Int,
    @BsonId
    val noteId: String = ObjectId().toString()
)
