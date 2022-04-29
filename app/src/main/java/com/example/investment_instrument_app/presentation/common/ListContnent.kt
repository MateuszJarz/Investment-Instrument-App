package com.example.investment_instrument_app.presentation.common


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.RootGroupName
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.investment_instrument_app.R
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.navigation.Screen
import com.example.investment_instrument_app.presentation.components.ShimmerEffect
import com.example.investment_instrument_app.ui.theme.LARGE_PADDING
import com.example.investment_instrument_app.ui.theme.MEDIUM_PADDING
import com.example.investment_instrument_app.ui.theme.SMALL_PADDING
import com.example.investment_instrument_app.ui.theme.topAppBarContentColor
import com.example.investment_instrument_app.untill.Constants.BASE_URL
import androidx.compose.foundation.layout.R as R1


@ExperimentalCoilApi
@Composable
fun ListContent(
    instruments: LazyPagingItems<Instrument>,
    navController: NavHostController
) {
    val result = handlePagingResult(instruments = instruments)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = instruments,
                key = { instrument ->
                    instrument.id
                }
            ) { instrument ->
                instrument?.let {
                    InstrumentItem(instrument = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    instruments: LazyPagingItems<Instrument>
): Boolean {
    instruments.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, instruments = instruments)
                false
            }
            instruments.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun InstrumentItem(
    instrument: Instrument,
    navController: NavHostController
) {
    val painter = rememberImagePainter(data = "$BASE_URL${instrument.image}") {
        placeholder(R.drawable.ic_network_error)
         error(R.drawable.ic_network_error)
    }

    Box(
        modifier = Modifier
            .height(400.dp)
            .clickable {
                navController.navigate(Screen.Details.passInstrumentId(instrumentId = instrument.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = "Instrument Image",
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = instrument.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = instrument.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /*RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = instrument.rating
                    )*/
                    Text(
                        text = "(${instrument.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun  InstrumentItemPreview() {
    InstrumentItem(
        instrument = Instrument(
            id = 1,
            name = "Gold",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
            rating = 0.0,
            family = listOf(),

        ),
        navController = rememberNavController()
    )
}

@ExperimentalCoilApi
@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun  InstrumentItemDarkPreview() {
    InstrumentItem(
        instrument = Instrument(
            id = 1,
            name = "Gold",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
            rating = 0.0,
            family = listOf(),
        ),
        navController = rememberNavController()
    )
}