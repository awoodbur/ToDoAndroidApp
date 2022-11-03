package com.github.awoodbur.to_docompose.navigation

import androidx.navigation.NavHostController
import com.github.awoodbur.to_docompose.util.Action
import com.github.awoodbur.to_docompose.util.Constants.LIST_SCREEN
import com.github.awoodbur.to_docompose.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION.name}") {
            //removes splash screen from back stack
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }
    val list: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
    val task: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

}