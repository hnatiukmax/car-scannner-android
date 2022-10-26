package dev.hnatiuk.carscanner.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.hnatiuk.carscanner.R.color
import dev.hnatiuk.carscanner.R.font

val fonts = FontFamily(
    Font(font.avenir_next_rounded_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(font.avenir_next_rounded_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(font.avenir_next_rounded_demi, weight = FontWeight.W800, style = FontStyle.Normal)
)

@Composable
fun textStyleDescription() = TextStyle(
    color = colorResource(color.text_hint),
    fontSize = 20.sp,
    fontFamily = fonts,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal
)

@Composable
fun textStyleAction() = TextStyle(
    color = colorResource(color.main_color),
    fontSize = 15.sp,
    fontFamily = fonts,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Normal
)

@Composable
fun textStyleMainButton() = TextStyle(
    color = colorResource(color.white),
    fontSize = 15.sp,
    fontFamily = fonts,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Normal
)