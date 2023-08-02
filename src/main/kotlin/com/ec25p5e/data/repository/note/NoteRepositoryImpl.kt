package com.ec25p5e.data.repository.note

import com.ec25p5e.data.models.Note
import com.ec25p5e.data.requests.CreateNoteRequest
import com.ec25p5e.data.requests.NoteRequest
import com.ec25p5e.data.requests.PushNotesRequest
import com.ec25p5e.data.responses.BasicApiResponse
import com.ec25p5e.data.responses.NoteResponse
import com.ec25p5e.util.ApiResponseMessages.PUSH_INSERTED_NOTES
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.`in`

class NoteRepositoryImpl(
    db: CoroutineDatabase
): NoteRepository {

    private val notesDb = db.getCollection<Note>()

    override suspend fun getNotes(userId: String): List<NoteResponse> {
        return notesDb.find(Note::userId `in` userId)
            .toList()
            .map { note ->
                NoteResponse(
                    noteId = note.noteId,
                    userId = note.userId,
                    title = note.title,
                    content = note.content,
                    timestamp = note.timestamp,
                    color = note.color,
                    isArchived = note.isArchived,
                    categoryId = note.categoryId
                )
            }
    }

    override suspend fun pushNotes(notes: List<PushNotesRequest>): BasicApiResponse<Unit> {
        notesDb.insertMany(
            notes.map {
                it.toNote(it)
            }
        )

        return BasicApiResponse(
            successful = true,
            message = PUSH_INSERTED_NOTES,
            data = null
        )
    }

    override suspend fun createNote(note: CreateNoteRequest) {
        notesDb.insertOne(note.toNote(note))
    }
}