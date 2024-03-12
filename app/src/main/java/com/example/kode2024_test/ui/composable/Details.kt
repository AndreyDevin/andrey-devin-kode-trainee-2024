package com.example.kode2024_test.ui.composable

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kode2024_test.R
import com.example.kode2024_test.data.dto.Employee

@Composable
fun Details(
    employee: Employee,
    onBackPressed: () -> Unit
) {
    BackHandler { onBackPressed() }

    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            GlideImageWithPreview(
                data = employee.avatarUrl,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )

            Image(
                painter = painterResource(R.drawable.arrow_back_ios_new_24),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBackPressed() }
            )
        }
        Row {

        }
        Row {

        }
    }
}