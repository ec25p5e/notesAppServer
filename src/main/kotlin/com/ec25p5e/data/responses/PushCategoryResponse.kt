package com.ec25p5e.data.responses

import com.ec25p5e.data.models.Category

data class PushCategoryResponse(
    val categoryId: String,
    val localId: Int,
    val userId: String,
    val name: String,
    val color: Int,
    val timestamp: Long,
    val numNotesAssoc: Int,
) {

    fun toCategory(): Category {
        return Category(
            userId = userId,
            name = name,
            color = color,
            timestamp = timestamp,
            localId = localId,
            numNotesAssoc = numNotesAssoc
        )
    }
}
