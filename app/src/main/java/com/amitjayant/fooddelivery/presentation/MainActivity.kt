package com.amitjayant.fooddelivery.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            val isUserLoggedIn = viewModel.isUserLoggedIn().first()
            val startDestination =
                if (isUserLoggedIn) Destinations.HomeScreen.route
                else Destinations.LoginScreen.route

            // Get data from api.
            if (isUserLoggedIn) viewModel.getData()

            setContent {
                FoodDeliveryTheme {
                    NavigationGraph(
                        startDestination = startDestination,
                        viewModel = viewModel,
                        onSignInClick = {
                            viewModel.signIn()
                        },
                        onSignOutClick = {
                            viewModel.signOut()
                        }
                    )
                }
            }
        }
    }
}