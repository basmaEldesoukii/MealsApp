package com.basma.homepage.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.basma.homepage.domain.entity.Announcement
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HighlightsSection(highlights: List<Announcement>) {
    val images = highlights.map { it.strThumb }
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = Modifier.height(260.dp)
    ) { page ->
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(images[page])
                .crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator(
                    color = Color.LightGray,
                    modifier = Modifier.padding(48.dp)
                )
            },
            contentDescription = "Meal Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
        )
    }

    Spacer(modifier = Modifier.size(12.dp))

    HorizontalTabs(
        items = images,
        pagerState = pagerState
    )
    Spacer(modifier = Modifier.size(12.dp))
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HorizontalTabs(
    items: List<String>,
    pagerState: PagerState,
) {
    val dotRadius = 4.dp
    val dotSpacing = 8.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dotSpacing)
            .wrapContentSize(Alignment.Center)
    ) {
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        ) {
            items.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(dotRadius * 2)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) Color.Gray else Color.LightGray
                        ),
                )
            }
        }
    }
}
