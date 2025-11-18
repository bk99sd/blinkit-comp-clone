package com.example.blinkitclone.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// State
data class LoginState(
    val phoneNumber: String = "",
    val isValidPhoneNumber: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

// Events
sealed interface LoginEvent {
    data class OnPhoneNumberChange(val phoneNumber: String) : LoginEvent
    data object OnContinueClick : LoginEvent
    data object OnSkipLogin : LoginEvent
}

class LoginViewModel(
    private val repository: LoginRepository = LoginRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnPhoneNumberChange -> {
                handlePhoneNumberChange(event.phoneNumber)
            }

            is LoginEvent.OnContinueClick -> {
                handleContinueClick()
            }

            is LoginEvent.OnSkipLogin -> {
                // Handle skip login if needed
            }
        }
    }

    private fun handlePhoneNumberChange(phoneNumber: String) {
        // Only allow digits and max 10 digits
        val filteredNumber = phoneNumber.filter { it.isDigit() }.take(10)
        val isValid = repository.isValidPhoneNumber(filteredNumber)

        _state.update { currentState ->
            currentState.copy(
                phoneNumber = filteredNumber,
                isValidPhoneNumber = isValid,
                errorMessage = null
            )
        }
    }

    private fun handleContinueClick() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = repository.login(_state.value.phoneNumber)

            _state.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    errorMessage = if (result.isFailure) {
                        "Failed to login. Please try again."
                    } else null
                )
            }
        }
    }
}
