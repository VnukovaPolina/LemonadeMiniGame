package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                MakeLemonadeApp()
            }
        }
    }
}

@Composable
fun MakeLemonade(
    imageOnScreen : Int,
    imageDescription : Int,
    textOfAction : Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(imageOnScreen),
                contentDescription = stringResource(imageDescription)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(textOfAction),
            fontSize = 18.sp
        )
    }
}

@Composable
fun MakeLemonadeApp(modifier: Modifier = Modifier) {
    var step by remember {mutableStateOf(1)}
    var numberOfSqeezes by remember {mutableStateOf(2)}

    when (step) {
        1 -> {
            MakeLemonade(
                imageOnScreen = R.drawable.lemon_tree,
                imageDescription = R.string.Lemon_tree_description,
                textOfAction = R.string.Tap_tree,
                onImageClick = {
                    step = 2
                    numberOfSqeezes = (2..4).random()
                }
            )
        }

        2 -> {
            MakeLemonade(
                imageOnScreen = R.drawable.lemon_squeeze,
                imageDescription = R.string.Lemon_description,
                textOfAction = R.string.Squeeze_lemon,
                onImageClick = {
                    numberOfSqeezes--
                    if (numberOfSqeezes == 0) {
                        step = 3
                    }
                }
            )
        }

        3 -> {
            MakeLemonade(
                imageOnScreen = R.drawable.lemon_drink,
                imageDescription = R.string.glass_lemonade_description,
                textOfAction = R.string.Drink,
                onImageClick = {
                    step = 4
                }
            )
        }

        4 -> {
            MakeLemonade(
                imageOnScreen = R.drawable.lemon_restart,
                imageDescription = R.string.empty_glass_description,
                textOfAction = R.string.Fill_glass_again,
                onImageClick = {
                    step = 1
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MakeLemonadePreview(modifier : Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {
    LemonadeTheme() {
        MakeLemonadeApp()
    }
}