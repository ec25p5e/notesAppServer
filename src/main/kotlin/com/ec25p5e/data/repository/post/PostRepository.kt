package com.ec25p5e.data.repository.post

import com.ec25p5e.data.responses.PostResponse
import com.ec25p5e.util.Constants

interface PostRepository {

    suspend fun getPostsByFollows(
        ownUserId: String,
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): List<PostResponse>
}