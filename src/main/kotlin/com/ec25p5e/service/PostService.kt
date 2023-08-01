package com.ec25p5e.service

import com.ec25p5e.data.repository.post.PostRepository
import com.ec25p5e.data.responses.PostResponse
import com.ec25p5e.util.Constants

class PostService(
    private val repository: PostRepository
) {

    suspend fun getPostsForFollows(
        ownUserId: String,
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): List<PostResponse> {
        return repository.getPostsByFollows(ownUserId, page, pageSize)
    }
}