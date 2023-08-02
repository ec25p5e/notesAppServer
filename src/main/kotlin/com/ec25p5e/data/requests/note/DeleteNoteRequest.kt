package com.ec25p5e.data.requests.note

data class DeleteNoteRequest(
    val noteId: String,
    val userId: String,
) {

    fun isIncomplete(): Boolean {
        if(noteId.isBlank())
            return true

        if(userId.isBlank())
            return true

        return false
    }
}