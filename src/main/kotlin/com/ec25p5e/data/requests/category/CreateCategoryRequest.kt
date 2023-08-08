package com.ec25p5e.data.requests.category

import com.ec25p5e.data.models.Category
import com.ec25p5e.data.requests.note.CreateNoteRequest

data class CreateCategoryRequest(
    val userId: String,
    val localId: Int,
    val remoteId: String,
    val name: String,
    val color: Int,
    val timestamp: Long
) {

    fun toCategory(): Category {
        return Category(
            userId = userId,
            localId = localId,
            name = name,
            color = color,
            timestamp = timestamp
        )
    }

    fun isIncomplete(): Boolean {
        if(userId.isBlank())
            return true

        if(name.isBlank())
            return true

        if(color.toString().isBlank())
            return true

        if(timestamp.toString().isBlank())
            return true

        if(localId.toString().isBlank())
            return true

        return false
    }
}