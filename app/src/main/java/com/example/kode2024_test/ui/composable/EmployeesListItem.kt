package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kode2024_test.domain.entity.Employee
import com.example.kode2024_test.ui.entity.Intent

@Composable
fun EmployeesListItem(
    employee: Employee,
    clickListener: (Intent.Details) -> Unit,
    birthday: String
) {
    val textName = "${employee.firstName}  ${employee.lastName}"
    val textUserTag = employee.userTag.lowercase()
    val textPosition = employee.position

    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable { clickListener(Intent.Details(employee.id)) }
    ) {

        GlideImageWithPreview(
            data = employee.avatarUrl,
            modifier = Modifier
                .size(66.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(start = 14.dp)
                .align(Alignment.CenterVertically)
        ) {

            Row {
                Text(
                    text = textName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = textUserTag,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.Bottom)
                        .alpha(0.6f)
                )
            }

            Text(
                text = textPosition,
                fontSize = 12.sp,
                modifier = Modifier.alpha(0.6f)
            )
        }

        if (birthday != "") Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.End)
                .alpha(0.6f),
            text = birthday
        )
    }
}