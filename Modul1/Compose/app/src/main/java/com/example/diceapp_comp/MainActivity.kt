package com.example.diceapp_comp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceApp_Comp()
        }
    }
}

@Composable
fun DiceApp_Comp() {
    var dice1 by remember { mutableIntStateOf(0) }
    var dice2 by remember { mutableIntStateOf(0) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row {
                DiceImage(dice1)
                DiceImage(dice2)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    dice1 = (1..6).random()
                    dice2 = (1..6).random()

                    val message = if (dice1 == dice2) {
                        "Selamat, anda dapat dadu double!"
                    } else {
                        "Anda belum beruntung!"
                    }

                    scope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            ) {
                Text("Roll")
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun DiceImage(value: Int) {

    val imageRes = when (value){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Dice",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(180.dp)
    )
}