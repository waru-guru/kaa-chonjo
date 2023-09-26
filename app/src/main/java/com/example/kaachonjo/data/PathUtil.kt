package com.example.kaachonjo.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadFroTo(from:Offset, to:Offset){
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + from.x) / 2f,
        abs(from.y + from.y) / 2f,
    )
}