package com.github.awoodbur.to_docompose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.github.awoodbur.to_docompose.ui.screens.splash.SplashScreen
import com.github.awoodbur.to_docompose.util.Constants.SPLASH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(500)
            )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}