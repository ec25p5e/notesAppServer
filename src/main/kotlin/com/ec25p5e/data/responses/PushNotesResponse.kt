package com.ec25p5e.data.responses

import com.ec25p5e.data.models.Note

data class PushNotesResponse(
    val noteId: String,
    val userId: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val isArchived: Boolean,
    val isLocked: Boolean,
    val image: ArrayList<String>,
    val background: String,
    val isCopied: Int,
    val categoryId: Int,
) {

    fun toNote(pushNote: PushNotesResponse): Note {
        return Note(
            userId = pushNote.userId,
            title = pushNote.title,
            content = pushNote.content,
            timestamp = pushNote.timestamp,
            color = pushNote.color,
            isArchived = pushNote.isArchived,
            categoryId = pushNote.categoryId,
        )
    }

    fun isIncomplete(): Boolean {
        if(noteId.isBlank())
            return true

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