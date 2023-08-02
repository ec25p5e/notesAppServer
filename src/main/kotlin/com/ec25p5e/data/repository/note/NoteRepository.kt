package com.ec25p5e.data.repository.note

import com.ec25p5e.data.requests.note.CreateNoteRequest
import com.ec25p5e.data.requests.note.DeleteNoteRequest
import com.ec25p5e.data.responses.PushNotesResponse
import com.ec25p5e.data.responses.BasicApiResponse
import com.ec25p5e.data.responses.NoteResponse

interface NoteRepository {

    suspend fun getNotes(userId: String): List<NoteResponse>

    suspend fun pushNotes(notes: List<PushNotesResponse>): BasicApiResponse<Unit>

    suspend fun createNote(note: CreateNoteRequest)

    suspend fun deleteNote(note: DeleteNoteRequest)
}