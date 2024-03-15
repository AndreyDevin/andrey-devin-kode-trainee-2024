package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.kode2024_test.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageWithPreview(
    data: Any?,
    modifier: Modifier? = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    if (data == null) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = contentDescription,
            modifier = modifier ?: Modifier,
            alignment = Alignment.Center,
            contentScale = contentScale
        )
    } else {
        GlideImage(
            model = data,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier ?: Modifier
        )
    }
}
