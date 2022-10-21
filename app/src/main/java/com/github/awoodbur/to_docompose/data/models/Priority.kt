package com.github.awoodbur.to_docompose.data.models

import androidx.compose.ui.graphics.Color
import com.github.awoodbur.to_docompose.ui.theme.HighPriorityColor
import com.github.awoodbur.to_docompose.ui.theme.LowPriorityColor
import com.github.awoodbur.to_docompose.ui.theme.MediumPriorityColor
import com.github.awoodbur.to_docompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}