package com.example.kode2024_test.ui.composable

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kode2024_test.R
import com.example.kode2024_test.domain.entity.Employee
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun Details(
    employee: Employee,
    onBackPressed: () -> Unit
) {
    BackHandler { onBackPressed() }
    Log.d("MyTag", Locale.getDefault().toString())

    val textName = "${employee.firstName}  ${employee.lastName}"
    val textUserTag = employee.userTag.lowercase()
    val textPosition = employee.position
    val textBirthDay = employee.birthday
        .format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault()))
    val textAge = ageWordForm(ZonedDateTime.now().year.minus(employee.birthday.year))
    val textPhone = employee.phone

    Column(modifier = Modifier.padding(horizontal = 10.dp)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            GlideImageWithPreview(
                data = employee.avatarUrl,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .align(Alignment.Center)
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Image(
                painter = painterResource(R.drawable.arrow_back_ios_new_24),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .clickable { onBackPressed() },
                colorFilter = themeFilter()
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)) {
            Text(
                text = textName,
                fontSize = 24.sp,
                fontWeight = Bold,
                )
            Text(
                text = textUserTag,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
                    .alpha(0.6f)
                )
        }

        Text(
            text = textPosition,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .alpha(0.6f)
        )

        Row(
            modifier = Modifier.padding(vertical = 25.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_star_border_24),
                contentDescription = null,
                colorFilter = themeFilter()
            )
            Text(
                text = textBirthDay.toString(),
                modifier = Modifier.padding(start = 10.dp)
                )
            Text(
                text = textAge,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                )
        }

        Row {
            Image(
                painter = painterResource(R.drawable.baseline_phone_24),
                contentDescription = null,
                colorFilter = themeFilter()
            )
            Text(
                text = textPhone,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

fun ageWordForm(age: Int): String {
    return when {
        age > 20 && age%10 == 1 -> "$age год"
        age > 20 && age%10 in 2..4 -> "$age года"
        else -> "$age лет"
    }
}

@Composable
fun themeFilter(): ColorFilter = ColorFilter.tint(
    if (isSystemInDarkTheme()) Color.White
    else Color.Black
)