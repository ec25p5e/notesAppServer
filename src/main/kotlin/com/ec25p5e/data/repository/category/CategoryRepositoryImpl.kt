package com.ec25p5e.data.repository.category

import com.ec25p5e.data.models.Category
import com.ec25p5e.data.requests.category.CreateCategoryRequest
import com.ec25p5e.data.requests.category.GetCategoryRequest
import com.ec25p5e.data.responses.category.CategoryResponse
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class CategoryRepositoryImpl(
    db: CoroutineDatabase
): CategoryRepository {

    private val categoryDb = db.getCollection<Category>()

    override suspend fun createCategory(category: CreateCategoryRequest) {
        categoryDb.insertOne(category.toCategory())
    }

    override suspend fun getCategories(data: GetCategoryRequest): List<CategoryResponse> {
        return categoryDb.find(Category::userId `eq` data.userId)
            .toList()
            .map { category ->
                CategoryResponse(
                    userId = category.userId,
                    categoryId = category.categoryId,
                    name = category.name,
                    color = category.color,
                    timestamp = category.timestamp
                )
            }
    }
}