package com.ec25p5e.data.repository.category

import com.ec25p5e.data.models.Category
import com.ec25p5e.data.requests.category.CreateCategoryRequest
import com.ec25p5e.data.requests.category.GetCategoryRequest
import com.ec25p5e.data.responses.category.CategoryResponse
import com.mongodb.client.model.Filters.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class CategoryRepositoryImpl(
    db: CoroutineDatabase
): CategoryRepository {

    private val categoryDb = db.getCollection<Category>()

    override suspend fun createCategory(category: CreateCategoryRequest): CategoryResponse {
        categoryDb.insertOne(category.toCategory())
        return categoryDb.find(
            and(
                Category::localId `eq` category.localId,
                Category::userId `eq` category.userId
            )
        ).toList().map { category ->
            CategoryResponse(
                userId = category.userId,
                localId = category.localId,
                remoteId = category.categoryId,
                name = category.name,
                timestamp = category.timestamp,
                color = category.color,
                numNotesAssoc = category.numNotesAssoc
            )
        }[0]
    }

    override suspend fun getCategories(data: GetCategoryRequest): List<CategoryResponse> {
        return categoryDb.find(Category::userId `eq` data.userId)
            .toList()
            .map { category ->
                CategoryResponse(
                    userId = category.userId,
                    remoteId = category.categoryId,
                    localId = category.localId,
                    name = category.name,
                    color = category.color,
                    timestamp = category.timestamp,
                    numNotesAssoc = category.numNotesAssoc
                )
            }
    }

    override suspend fun pushCategories(categories: List<CreateCategoryRequest>): List<CategoryResponse> {
        categoryDb.insertMany(
            categories.map {
                it.toCategory()
            }
        )

        return categoryDb.find(Category::userId `eq` categories[0].userId)
            .toList()
            .map { category ->
                CategoryResponse(
                    userId = category.userId,
                    remoteId = category.categoryId,
                    localId = category.localId,
                    name = category.name,
                    color = category.color,
                    timestamp = category.timestamp,
                    numNotesAssoc = category.numNotesAssoc
                )
            }
    }
}