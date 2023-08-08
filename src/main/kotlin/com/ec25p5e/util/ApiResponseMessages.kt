package com.ec25p5e.util

import com.ec25p5e.util.Constants

object ApiResponseMessages {

    const val USER_ALREADY_EXISTS = "A user with this email already exists."
    const val USER_NOT_FOUND = "The user couldn't be found."
    const val INVALID_CREDENTIALS = "Oops, that is not correct, please try again."
    const val FIELDS_BLANK = "The fields my not be empty."
    const val COMMENT_TOO_LONG = "The comment length must not exceed ${Constants.MAX_COMMENT_LENGTH} characters."
    const val SUCCESSFULLY_FETCHED_NOTE = "All notes are fetched"
    const val SUCCESSFULLY_FETCHED_CATEGORY = "All category are fetched"
    const val PUSH_INSERTED_NOTES = "All notes are pushed successfully"
    const val NOTE_CREATE_SUCCESSFULLY = "Note is created successfully"
    const val NOTE_DELETED_SUCCESSFULLY = "Note is deleted successfully"
    const val CATEGORY_CREATE_SUCCESSFULLY = "Category created successfully"
    const val CATEGORY_PUSHED_SUCCESSFULLY = "Category pushed successfully"
}