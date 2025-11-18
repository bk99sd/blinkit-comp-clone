package com.example.blinkitclone.ui.login

class LoginRepository {

    /**
     * Validates if the phone number is valid (10 digits)
     */
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 10 && phoneNumber.all { it.isDigit() }
    }

    /**
     * Simulates login process (can be replaced with actual API call)
     */
    suspend fun login(phoneNumber: String): Result<Boolean> {
        return try {
            // Simulate network delay
            kotlinx.coroutines.delay(500)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
