package com.amitjayant.fooddelivery.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amitjayant.fooddelivery.data.util.Constants.ROUTE_HOME
import com.amitjayant.fooddelivery.data.util.Constants.ROUTE_LOGIN
import com.amitjayant.fooddelivery.data.util.Constants.ROUTE_RESTAURANT
import com.amitjayant.fooddelivery.domain.Restaurant
import com.amitjayant.fooddelivery.presentation.home.HomeScreen
import com.amitjayant.fooddelivery.presentation.login.LoginScreen
import com.amitjayant.fooddelivery.presentation.restaurant.RestaurantScreen

sealed class Destinations(val route: String) {
    data object LoginScreen : Destinations(route = ROUTE_LOGIN)
    data object HomeScreen : Destinations(route = ROUTE_HOME)
    data object RestaurantScreen : Destinations(route = ROUTE_RESTAURANT)
}

@Composable
fun NavigationGraph(
    startDestination: String,
    viewModel: CoreViewModel,
    onSignInClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    val hapticFeedback = LocalHapticFeedback.current

    // Animation transitions
    val transitionFadeOut: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        fadeOut(animationSpec = tween(500))
    }
    val transitionFadeIn: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        fadeIn(animationSpec = tween(500))
    }

    // Compose states
    val navController: NavHostController = rememberNavController()

    // NavHost composable
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Login screen destination
        composable(
            route = Destinations.LoginScreen.route,
            enterTransition = transitionFadeIn,
            exitTransition = transitionFadeOut,
            popEnterTransition = transitionFadeIn,
            popExitTransition = transitionFadeOut
        ) {
            LoginScreen(
                onSignInClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onSignInClick()

                    // Get data from api.
                    viewModel.getData()

                    // Navigate to home screen.
                    navController.navigate(Destinations.HomeScreen.route) {
                        popUpTo(Destinations.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Home screen destination
        composable(
            route = Destinations.HomeScreen.route,
            enterTransition = transitionFadeIn,
            exitTransition = transitionFadeOut,
            popEnterTransition = transitionFadeIn,
            popExitTransition = transitionFadeOut
        ) {
            HomeScreen(
                viewModel = viewModel,
                onSignOut = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onSignOutClick()

                    // Navigate to login screen.
                    navController.navigate(Destinations.LoginScreen.route) {
                        popUpTo(route = Destinations.HomeScreen.route) {
                            inclusive = true
                        }
                    }
                },
                navigateTo = { route: String, argument: Restaurant ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("restaurant", argument)
                    navController.navigate(route)
                }
            )
        }

        // Restaurant screen destination
        composable(
            route = Destinations.RestaurantScreen.route,
            enterTransition = transitionFadeIn,
            exitTransition = transitionFadeOut,
            popEnterTransition = transitionFadeIn,
            popExitTransition = transitionFadeOut
        ) {
            val argument: Restaurant = navController.previousBackStackEntry?.savedStateHandle?.get("restaurant") ?: Restaurant()

            RestaurantScreen(
                restaurant = argument,
                navigateUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}