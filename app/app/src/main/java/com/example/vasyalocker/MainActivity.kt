package com.example.vasyalocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
        "terminal" -> TerminalScreen { screen = "ransom" }
        "ransom"   -> RansomScreen { screen = "game" }
        "game"     -> MiniGameScreen { screen = "unlock" }
        "unlock"   -> UnlockScreen { screen = "joke" }
        "joke"     -> JokeScreen { screen = "terminal" }
    }
}

@Composable
fun TerminalScreen(onDone: () -> Unit) {
    val lines = remember { mutableStateListOf<String>() }
    val files = listOf("vasya_photo.jpg", "document.pdf", "music.mp3", "notes.txt")

    LaunchedEffect(Unit) {
        lines += "> Ініціалізація VasyaLocker 3000..."
        delay(800)
        files.forEach { f ->
            lines += "> Шифрую $f → $f.vasya 🔒"
            delay(1200)
        }
        lines += "> Готово. Файлів зашифровано: ${files.size}"
        delay(1200)
        onDone()
    }

    LazyColumn(
        Modifier.fillMaxSize().background(Color.Black).padding(16.dp)
    ) {
        items(lines) { line ->
            Text(line, color = Color(0xFF00FF00), fontFamily = FontFamily.Monospace, fontSize = 14.sp)
        }
    }
}

@Composable
fun RansomScreen(onPay: () -> Unit) {
    Column(
        Modifier.fillMaxSize().background(Color(0xFF8B0000)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🔒", fontSize = 72.sp)
        Text("ВАШІ ФАЙЛИ ЗАШИФРОВАНО", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("Заплатіть 500 грн, щоб отримати ключ", color = Color.White)
        Spacer(Modifier.height(32.dp))
        Button(onClick = onPay) { Text("Отримати ключ 💸") }
    }
}

@Composable
fun MiniGameScreen(onWin: () -> Unit) {
    val a = remember { (2..9).random() }
    val b = remember { (2..9).random() }
    var input by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Доведи, що ти не бот 🤖", fontSize = 20.sp)
        Spacer(Modifier.height(16.dp))
        Text("$a + $b = ?", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = input,
            onValueChange = { input = it.filter { c -> c.isDigit() }; error = false },
            label = { Text("Відповідь") }
        )
        if (error) Text("Неправильно 😈", color = Color.Red)
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (input.toIntOrNull() == a + b) onWin() else error = true
        }) { Text("Перевірити") }
    }
}

@Composable
fun UnlockScreen(onDone: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        while (progress < 1f) {
            delay(80)
            progress += 0.02f
        }
        onDone()
    }
    Column(
        Modifier.fillMaxSize().background(Color.Black).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🔓 Розшифровка...", color = Color.Green, fontSize = 24.sp, fontFamily = FontFamily.Monospace)
        Spacer(Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
            color = Color.Green
        )
        Spacer(Modifier.height(8.dp))
        Text("${(progress * 100).toInt()}%", color = Color.Green)
    }
}

@Composable
fun JokeScreen(onRestart: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("😂", fontSize = 96.sp)
        Text("ЖАРТ!", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("Василь тебе не зашифрував.", fontSize = 18.sp)
        Text("Твої файли в безпеці 😎", fontSize = 18.sp)
        Spacer(Modifier.height(32.dp))
        Button(onClick = onRestart) { Text("Ще раз 🔁") }
    }
}
