package com.ec25p5e.di

import com.ec25p5e.data.repository.note.NoteRepository
import com.ec25p5e.data.repository.note.NoteRepositoryImpl
import com.ec25p5e.data.repository.post.PostRepository
import com.ec25p5e.data.repository.post.PostRepositoryImpl
import com.ec25p5e.data.repository.user.UserRepositoryImpl
import com.ec25p5e.data.repository.user.UserRepository
import com.ec25p5e.service.NoteService
import com.ec25p5e.service.PostService
import com.ec25p5e.service.UserService
import com.ec25p5e.util.Constants
import com.google.gson.Gson
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val mainModule = module {
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase(Constants.DATABASE_NAME)
    }

    // Repositories
    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<PostRepository> {
        PostRepositoryImpl(get())
    }

    single<NoteRepository> {
        NoteRepositoryImpl(get())
    }

    // Services
    single { UserService(get()) }
    single { PostService(get()) }
    single { NoteService(get()) }

    single { Gson() }
}