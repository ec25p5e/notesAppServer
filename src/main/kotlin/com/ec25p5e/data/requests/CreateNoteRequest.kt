package com.ec25p5e.data.requests

import com.ec25p5e.data.models.Note

data class CreateNoteRequest(
    val userId: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val isArchived: Boolean,
    val categoryId: Int,
) {

    fun toNote(createNote: CreateNoteRequest): Note {
        return Note(
            userId = createNote.userId,
            title = createNote.title,
            content = createNote.content,
            timestamp = createNote.timestamp,
            color = createNote.color,
            isArchived = createNote.isArchived,
            categoryId = createNote.categoryId,
        )
    }

    fun isIncomplete(): Boolean {
        if(userId.isBlank())
            return true

        if(title.isBlank())
            return true

        if(content.isBlank())
            return true

        if(timestamp.toString().isBlank())
            return true

        if(color.toString().isBlank())
            return true

        if(isArchived.toString().isBlank())
            return true

        if(categoryId.toString().isBlank())
            return true

        return false
    }
}