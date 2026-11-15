package com.example.vasyalocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
    var screen by remember { mutableStateOf("boot") }

    when (screen) {
        "boot" -> BootScreen { screen = "ransom" }
        "ransom" -> RansomScreen() // Більше ніяких переходів далі
    }
}

@Composable
fun BootScreen(onDone: () -> Unit) {
    var text by remember { mutableStateOf("") }
    val errors = listOf(
        "Boot error: 0x03527737",
        "Boot error: 0x0266712",
        "Boot error: 0x02897593",
        "Boot error: 0x01447812",
        "Boot error: 0x0150974",
        "Boot error: 0x03873700",
        "Boot error: 0x0700882",
        "Boot error: 0x03803618",
        "Memory section at address 0x0424* is locked!",
        "Service UXCryptor started.",
        "",
        "* Windows blocked!"
    )

    LaunchedEffect(Unit) {
        for (err in errors) {
            delay(300)
            text += "$err\n"
        }
        delay(2000)
        onDone()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp,
            lineHeight = 16.sp
        )
        
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "👁",
                color = Color.White,
                fontSize = 64.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "ВАС ЗАМЕТИЛИ",
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
fun RansomScreen() {
    val blueBg = Color(0xFF0000AA)
    var inputText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isError) Color.Red else blueBg) // Ефект помилки
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.White)
                .padding(4.dp)
        ) {
            // Заголовок
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ваши файлы зашифрованы!",
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Основний текст
            Text(
                text = "Упс! Вы подверглись масштабной хакерской атаке и теперь ваш компьютер заблокирован, а все имеющиеся диски и файлы на них зашифрованы хакерской группировкой. Любые действия, связанные с попыткой обмануть систему нанесут непоправимый вред Вашему компьютеру и приведут к потере всех важных файлов без возможности восстановления. При попытке снять блокировку главный загрузчик материнки будет снесён и будет подана рекурсивная нагрузка на ваш процессор, что приведёт к его неисправности.\nУ вас есть 48 часов с момента запуска чтобы ввести код\nЧтобы получить код, напиши @Vasya (Telegram)",
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(8.dp)
            )

            // Поле вводу
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Введите код разблокировки:",
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(4.dp)
                ) {
                    BasicTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            // Нижня панель
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ,
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp
                )
                
                // Кнопка Enter з імітацією помилки
                Box(
                    modifier = Modifier
                        .clickable {
                            isError = true // Робимо екран червоним
                        }
                        .border(1.dp, Color.White)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Enter [Ввод]",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

    // Таймер, який повертає синій колір через 0.5 секунди після натискання
    if (isError) {
        LaunchedEffect(Unit) {
            delay(500)
            isError = false
            inputText = "" // Очищає поле вводу
        }
    }
}
