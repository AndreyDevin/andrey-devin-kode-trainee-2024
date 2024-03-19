package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kode2024_test.R

@Composable
fun SearchResultNothing() {

    val modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)

    Column(
        modifier = modifier.padding(top = 40.dp)
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(R.drawable.magnifying_glass),
            contentDescription = ""
        )
        Text(
            modifier = modifier,
            text = "Мы никого не нашли",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
            )
        Text(
            modifier = modifier.alpha(0.6f),
            text = "Попробуй скорректировать запрос"
        )
    }
}