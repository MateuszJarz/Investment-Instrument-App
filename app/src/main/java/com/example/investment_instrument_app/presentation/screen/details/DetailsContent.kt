package com.example.investment_instrument_app.presentation.screen.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.investment_instrument_app.R
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.domain.use_case.get_selected_instrument.GetSelectedInstrument
import com.example.investment_instrument_app.presentation.components.OrderedList
import com.example.investment_instrument_app.ui.theme.*
import com.example.investment_instrument_app.untill.Constants.BASE_URL


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedInstrument: Instrument?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            0.dp
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 140.dp,
        sheetContent = {
            selectedInstrument?.let { BottomSheetContent(selectedInstrument = it) }
        },
        content = {
            selectedInstrument?.let { instrument ->
                BackgroundContent(
                    instrumentImage = instrument.image,
                    imageFraction = currentSheetFraction,
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}
@Composable
fun BottomSheetContent(
    selectedInstrument: Instrument,

    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = androidx.compose.ui.Modifier
                    .size(32.dp)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_network_error),
                contentDescription = "Icon Logo",
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedInstrument.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About",
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedInstrument.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 7
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = "Family",
                items = selectedInstrument.family,
                textColor = contentColor
            )

        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    instrumentImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${instrumentImage}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.ic_network_error)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + 0.4f)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = "Instrument Image",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedInstrument = Instrument(
            id = 1,
            name = "Naruto",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            rating = 4.5,
            family = listOf("Minato", "Kushina", "Boruto", "Himawari"),

        )
    )
}

