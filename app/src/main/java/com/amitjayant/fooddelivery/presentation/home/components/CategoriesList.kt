package com.amitjayant.fooddelivery.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.util.Dimensions.dimenMedium
import com.amitjayant.fooddelivery.data.util.Dimensions.dimenSmall
import com.amitjayant.fooddelivery.domain.Category

@Composable
fun CategoriesList(categories: List<Category>) {
    val context = LocalContext.current

    LazyHorizontalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        rows = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(dimenMedium)
    ) {
        items(categories) {
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clip(MaterialTheme.shapes.small)
                    .clickable { },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(it.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.vd_category),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
                Text(
                    modifier = Modifier.padding(top = dimenSmall),
                    text = it.name.split(" ").joinToString("\n"),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoriesListPreview() {
    CategoriesList(
        listOf(
            Category("Burger"),
            Category("Pizza"),
            Category("Tacos"),
            Category("Biryani")
        )
    )
}