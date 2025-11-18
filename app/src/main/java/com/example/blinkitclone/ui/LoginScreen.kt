package com.example.blinkitclone.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.components.ScrollingProductBackground
import com.example.blinkitclone.ui.login.LoginEvent
import com.example.blinkitclone.ui.login.LoginState
import com.example.blinkitclone.ui.login.LoginViewModel
import com.example.blinkitclone.ui.theme.BlinkitCloneTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToHome: () -> Unit,
    onSkipLogin: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigateToHome = onNavigateToHome,
        onSkipLogin = onSkipLogin
    )
}

@Composable
fun LoginScreenContent(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onNavigateToHome: () -> Unit,
    onSkipLogin: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
    ) {
        // Scrolling product background
        ScrollingProductBackground()

        // Skip login button at top right
        TextButton(
            onClick = onSkipLogin,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 24.dp, end = 16.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 2.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.white),
            )
        ) {
            Text(
                text = "Skip login",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(350.dp))

            // Blinkit logo
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "India's last minute app",
                style = MaterialTheme.typography.displayLarge,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            Text(
                text = "Log In or Sign Up",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Phone number input
            PhoneNumberInput(
                phoneNumber = state.phoneNumber,
                onPhoneNumberChange = { onEvent(LoginEvent.OnPhoneNumberChange(it)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Continue button
            Button(
                onClick = {
                    onEvent(LoginEvent.OnContinueClick)
                    if (state.isValidPhoneNumber) {
                        onNavigateToHome()
                    }
                },
                enabled = state.isValidPhoneNumber && !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.green),
                    disabledContainerColor = colorResource(R.color.colorBluishGray),
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White
                    )
                } else {
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))
        }

        // Fixed footer with terms and privacy text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(colorResource(R.color.white))
                .shadow(
                    elevation = .5.dp
                )
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                text = "By continuing, you agree to our Terms of service & Privacy policy",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 11.sp
                ),
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PhoneNumberInput(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Country code
            Text(
                text = "+91",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Phone number input
            BasicTextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                modifier = Modifier.weight(1f),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (phoneNumber.isEmpty()) {
                        Text(
                            text = "Enter mobile number",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

// Previews
@Preview(showBackground = true, name = "Login Screen - Empty")
@Composable
fun LoginScreenPreview() {
    BlinkitCloneTheme {
        LoginScreenContent(
            state = LoginState(),
            onEvent = {},
            onNavigateToHome = {},
            onSkipLogin = {}
        )
    }
}

@Preview(showBackground = true, name = "Login Screen - With Phone Number")
@Composable
fun LoginScreenWithPhonePreview() {
    BlinkitCloneTheme {
        LoginScreenContent(
            state = LoginState(
                phoneNumber = "9876543210",
                isValidPhoneNumber = true
            ),
            onEvent = {},
            onNavigateToHome = {},
            onSkipLogin = {}
        )
    }
}

@Preview(showBackground = true, name = "Login Screen - Invalid Phone")
@Composable
fun LoginScreenInvalidPhonePreview() {
    BlinkitCloneTheme {
        LoginScreenContent(
            state = LoginState(
                phoneNumber = "98765",
                isValidPhoneNumber = false
            ),
            onEvent = {},
            onNavigateToHome = {},
            onSkipLogin = {}
        )
    }
}

@Preview(showBackground = true, name = "Login Screen - Loading")
@Composable
fun LoginScreenLoadingPreview() {
    BlinkitCloneTheme {
        LoginScreenContent(
            state = LoginState(
                phoneNumber = "9876543210",
                isValidPhoneNumber = true,
                isLoading = true
            ),
            onEvent = {},
            onNavigateToHome = {},
            onSkipLogin = {}
        )
    }
}

@Preview(showBackground = true, name = "Phone Number Input - Empty")
@Composable
fun PhoneNumberInputEmptyPreview() {
    BlinkitCloneTheme {
        PhoneNumberInput(
            phoneNumber = "",
            onPhoneNumberChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Phone Number Input - Filled")
@Composable
fun PhoneNumberInputFilledPreview() {
    BlinkitCloneTheme {
        PhoneNumberInput(
            phoneNumber = "9876543210",
            onPhoneNumberChange = {}
        )
    }
}
