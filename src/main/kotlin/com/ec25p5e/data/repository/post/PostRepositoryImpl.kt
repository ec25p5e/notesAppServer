package com.ec25p5e.data.repository.post

import com.ec25p5e.data.models.Following
import com.ec25p5e.data.models.Like
import com.ec25p5e.data.models.Post
import com.ec25p5e.data.models.User
import com.ec25p5e.data.responses.PostResponse
import com.ec25p5e.util.Constants
import org.litote.kmongo.`in`
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.inc

class PostRepositoryImpl(
    db: CoroutineDatabase
): PostRepository {

    private val posts = db.getCollection<Post>()
    private val following = db.getCollection<Following>()
    private val users = db.getCollection<User>()
    private val likes = db.getCollection<Like>()

    override suspend fun getPostsByFollows(
        ownUserId: String,
        page: Int,
        pageSize: Int
    ): List<PostResponse> {
        val userIdsFromFollows = following.find(Following::followingUserId eq ownUserId)
            .toList()
            .map {
                it.followedUserId
            }

        return posts.find(Post::userId `in` userIdsFromFollows)
            .skip(page * pageSize)
            .limit(pageSize)
            .descendingSort(Post::timestamp)
            .toList()
            .map { post ->
                val isLiked = likes.findOne(and(
                    Like::parentId eq post.id,
                    Like::userId eq ownUserId
                )) != null

                val user = users.findOneById(post.userId)

                PostResponse(
                    id = post.id,
                    userId = ownUserId,
                    username = user?.username ?: "",
                    imageUrl = post.imageUrl,
                    profilePictureUrl = user?.profileImageUrl ?: "",
                    description = post.description,
                    likeCount = post.likeCount,
                    commentCount = post.commentCount,
                    isLiked = isLiked,
                    isOwnPost = ownUserId == post.userId
                )
            }
    }
}