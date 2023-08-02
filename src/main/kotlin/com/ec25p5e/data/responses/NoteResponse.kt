package com.ec25p5e.data.responses

data class NoteResponse(
    val noteId: String,
    val userId: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val isArchived: Boolean,
    val categoryId: Int,
)