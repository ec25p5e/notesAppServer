package com.ec25p5e.service

import com.ec25p5e.data.models.User
import com.ec25p5e.data.requests.CreateAccountRequest
import com.ec25p5e.data.repository.user.UserRepository
import com.ec25p5e.data.responses.ProfileResponse
import com.ec25p5e.util.Constants

class UserService(
    private val userRepository: UserRepository
) {

    suspend fun getUserByEmail(email: String): User? {
        return userRepository.getUserByEmail(email)
    }

    suspend fun createUser(request: CreateAccountRequest) {
        userRepository.createUser(
            User(
                email = request.email,
                username = request.username,
                password = request.password,
                profileImageUrl = Constants.DEFAULT_PROFILE_PICTURE_PATH,
                bannerUrl = Constants.DEFAULT_BANNER_IMAGE_PATH
            )
        )
    }

    suspend fun getUserProfile(userId: String, callerUserId: String): ProfileResponse? {
        val user = userRepository.getUserById(userId) ?: return null

        return ProfileResponse(
            userId = user.id,
            email = user.email,
            username = user.username,
            profilePictureUrl = user.profileImageUrl,
            bannerUrl = user.bannerUrl
        )
    }

    suspend fun doesUserWithEmailExist(email: String): Boolean {
        return userRepository.getUserByEmail(email) != null
    }

    fun isValidPassword(enteredPassword: String, actualPassword: String): Boolean {
        return enteredPassword == actualPassword
    }

    fun validateCreateAccountRequest(request: CreateAccountRequest): ValidationEvent {
        if(request.email.isBlank() || request.password.isBlank() || request.username.isBlank())
            return ValidationEvent.ErrorFieldEmpty;

        return ValidationEvent.Success;
    }

    sealed class ValidationEvent {
        object ErrorFieldEmpty: ValidationEvent()
        object Success: ValidationEvent()
    }
}