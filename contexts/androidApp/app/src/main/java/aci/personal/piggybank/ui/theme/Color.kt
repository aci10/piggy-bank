package aci.personal.piggybank.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

enum class CustomTheme {
    DARK, LIGHT
}

val White = Color(0xfffffffe)
val Black = Color(0xff33272a)

val Grey1f = Color(0xffe6e1e6)
val Grey2f = Color(0xffe6e0ec)
val Grey4f = Color(0xff79757f)
val Grey5f = Color(0xff72757e)
val Grey6f = Color(0xff847375)
val Grey7f = Color(0xff48454e)
val Grey8f = Color(0xff524345)

val Pink2f = Color(0xfff3dde0)
val Pink3f = Color(0xffffd9df)
val Pink8f = Color(0xffff8ba7)

val Magenta1f = Color(0xffffd9e2)
val Magenta2f = Color(0xffffb1c7)
val Magenta6f = Color(0xff9d3d58)
val Magenta7f = Color(0xff7b2948)
val Magenta8f = Color(0xff5e1132)
val Magenta9f = Color(0xff3f0017)

val Green3f = Color(0xff9bf6b3)
val Green7f = Color(0xff006d38)
val Green9f = Color(0xff00210d)

val Cream = Color(0xfffaeee7)

val Yellow3f = Color(0xffffddb1)
val Yellow5f = Color(0xfff9bc60)
val Yellow7f = Color(0xff815600)
val Yellow8f = Color(0xff442b00)
val Yellow9f = Color(0xff291800)

val Noir6f = Color(0xff242629)
val Noir9f = Color(0xff16161a)

val Violet1f = Color(0xffe7deff)
val Violet2f = Color(0xffccbdff)
val Violet4f = Color(0xff4b388e)
val Violet8f = Color(0xff341e76)
val Violet9f = Color(0xff1f0060)

@Stable
class CustomColors(
    backgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    textColor: Color
) {
    var backgroundColor by mutableStateOf(backgroundColor)
        private set

    var buttonBackgroundColor by mutableStateOf(buttonBackgroundColor)
        private set

    var buttonTextColor by mutableStateOf(buttonTextColor)
        private set

    var textColor by mutableStateOf(textColor)
        private set

    fun update(colors: CustomColors) {
        this.backgroundColor = colors.backgroundColor
        this.buttonBackgroundColor = colors.buttonBackgroundColor
        this.buttonTextColor = colors.buttonTextColor
        this.textColor = colors.textColor
    }

    fun copy() = CustomColors(
        backgroundColor,
        buttonBackgroundColor,
        buttonTextColor,
        textColor
    )
}
