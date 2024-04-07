package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.DataState

@Composable
fun DataState(dataState: DataState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    ) {
        when(dataState) {

        is Data.Loading ->
            Text(
                modifier = Modifier
                    .size(width = 250.dp, height = 60.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Blue)
                    .wrapContentSize(Alignment.Center),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Loading...",
                color = Color.White
            )

        is Data.Error ->
            Text(
                modifier = Modifier
                    .size(width = 250.dp, height = 60.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Red)
                    .wrapContentSize(Alignment.Center),
                text = dataState.errorText
            )
        }
    }
}