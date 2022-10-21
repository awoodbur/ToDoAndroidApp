package com.github.awoodbur.to_docompose.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = { action ->
                    //checks if "X" or back arrow is pressed
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else { //if check mark or update pressed
                        if (sharedViewModel.validateFields()){
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
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
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
