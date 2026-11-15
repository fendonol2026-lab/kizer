package com.example.vasyalocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                VasyaLockerApp()
            }
        }
    }
}

@Composable
fun VasyaLockerApp() {
    var screen by remember { mutableStateOf("terminal") }

    when (screen) {
        "terminal" -> TerminalScreen { screen = "blocked" }
        "blocked" -> BlockedScreen { screen = "joke" }
        "joke" -> JokeScreen { screen = "terminal" }
    }
}

@Composable
fun TerminalScreen(onDone: () -> Unit) {
    var text by remember { mutableStateOf("") }
    val errors = listOf(
        "Boot error: 0x03527737",
        "Boot error: 0x0266712",
        "Boot error: 0x02897593",
        "Boot error: 0x01447812",
        "Boot error: 0x0150974",
        "Boot error: 0x03873700",
        "Memory section at address 0x0424* is locked!",
        "Service UXCryptor started.",
        "* Windows blocked!",
        "\n[!] SYSTEM COMPROMISED...",
        "[!] ALL FILES ENCRYPTED...",
        "\nInitializing Ransomware protocol..."
    )

    LaunchedEffect(Unit) {
        for (err in errors) {
            delay(250) // Швидкість появи тексту
            text += "$err\n"
        }
        delay(1500)
        onDone()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Текст, що біжить зверху
        Text(
            text = text,
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
        
        // Око та напис по центру
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "( 0_0 )", // Імітація ока текстом
                color = Color.White,
                fontSize = 48.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "ВАС ЗАМЕТИЛИ",
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )
        }
    }
}

@Composable
fun BlockedScreen(onUnlock: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "***********************************\n" +
                   "*   FATAL SYSTEM ERROR   *\n" +
                   "***********************************",
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "ВАШІ ФАЙЛИ ЗАШИФРОВАНО.\n\n" +
                   "Ключ для розшифровки знаходиться\n" +
                   "у вашого друга Василя.\n\n" +
                   "Натисніть [ENTER], щоб почати\n" +
                   "процес розшифровки.",
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Кнопка у стилі старого терміналу
        Box(
            modifier = Modifier
                .clickable { onUnlock() }
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Text(
                text = "[ ENTER ]",
                color = Color.Black,
                backgroundColor = Color.White,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun JokeScreen(onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Білий екран для контрасту
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ЖАРТ! 😂",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = FontFamily.Monospace
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Це просто симуляція.\nВаші файли в безпеці.\nНічого не зашифровано.",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontFamily = FontFamily.Monospace
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onRestart,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("ЗАПУСТИТИ ЗНОВУ", color = Color.White, fontFamily = FontFamily.Monospace)
        }
    }
}
