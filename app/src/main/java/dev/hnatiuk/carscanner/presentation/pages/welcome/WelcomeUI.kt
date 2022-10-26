package dev.hnatiuk.carscanner.presentation.pages.welcome;

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.hnatiuk.carscanner.R.color
import dev.hnatiuk.carscanner.R.drawable
import dev.hnatiuk.carscanner.R.string
import dev.hnatiuk.carscanner.presentation.theme.textStyleAction
import dev.hnatiuk.carscanner.presentation.theme.textStyleDescription
import dev.hnatiuk.carscanner.presentation.theme.textStyleMainButton

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun Welcome() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp)
    ) {
        val (logo, title, continueButton, googleButton, facebookButton, skipButton) = createRefs()

        Image(
            modifier = Modifier
                .height(80.dp)
                .width(160.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top, margin = 72.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = drawable.ic_logo),
            contentDescription = null
        )

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(logo.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(string.welcome_title),
            style = textStyleDescription()
        )

        Button(
            modifier = Modifier
                .height(48.dp)
                .constrainAs(continueButton) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(googleButton.top, margin = 16.dp)
                },
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(color.main_color))
        ) {
            Text(
                text = stringResource(string.welcome_continue_with_email),
                style = textStyleMainButton(),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }

        AuthButton(
            modifier = Modifier.constrainAs(googleButton) {
                width = Dimension.fillToConstraints
                start.linkTo(parent.start)
                end.linkTo(facebookButton.start)
                bottom.linkTo(skipButton.top, margin = 48.dp)
            },
            text = stringResource(string.welcome_google),
            icon = painterResource(drawable.ic_google)
        )

        AuthButton(
            modifier = Modifier.constrainAs(facebookButton) {
                width = Dimension.fillToConstraints
                start.linkTo(googleButton.end, margin = 8.dp)
                end.linkTo(parent.end)
                bottom.linkTo(googleButton.bottom)
            },
            text = stringResource(string.welcome_facebook),
            icon = painterResource(drawable.ic_facebook)
        )

        Text(
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(skipButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                },
            text = stringResource(string.welcome_skip_for_now),
            style = textStyleAction(),
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun AuthButton(
    modifier: Modifier,
    text: String,
    icon: Painter
) {
    Row(
        modifier = Modifier
            .height(38.dp)
            .border(
                width = 1.dp,
                color = colorResource(color.auth_stroke),
                shape = RoundedCornerShape(4.dp)
            )
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            text = text,
            style = textStyleDescription(),
            fontSize = 14.sp
        )
    }
}