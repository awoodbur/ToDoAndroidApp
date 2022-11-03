package com.github.awoodbur.to_docompose.ui.screens.task

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.github.awoodbur.to_docompose.data.models.Priority
import com.github.awoodbur.to_docompose.data.models.ToDoTask
import com.github.awoodbur.to_docompose.ui.viewmodels.SharedViewModel
import com.github.awoodbur.to_docompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String = sharedViewModel.title
    val description: String = sharedViewModel.description
    val priority: Priority = sharedViewModel.priority

    val context = LocalContext.current

    //BackHandler(onBackPressed = { navigateToListScreen(Action.NO_ACTION) })
    BackHandler() {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = { action ->
                    //checks if "X" or back arrow is pressed
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else { //if check mark or update pressed
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }

                },
                selectedTask = selectedTask
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.updateDescription(newDescription = it)
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.updatePriority(newPriority = it)
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fileds Empty",
        Toast.LENGTH_SHORT
    ).show()
}

//@Composable
//fun BackHandler(
//    backDispatcher: OnBackPressedDispatcher? =
//        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
//    onBackPressed: () -> Unit
//) {
//    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)
//
//    val backCallBack = remember {
//        object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                currentOnBackPressed()
//            }
//        }
//    }
//
//    DisposableEffect(key1 = backDispatcher) {
//        backDispatcher?.addCallback(backCallBack)
//
//        onDispose {
//            backCallBack.remove()
//        }
//    }
//}
