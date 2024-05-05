package com.waqas028.jetpack_compose_components_tutorials.bmi_calculator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.waqas028.jetpack_compose_components_tutorials.R
import com.waqas028.jetpack_compose_components_tutorials.navigation.Screen
import com.waqas028.jetpack_compose_components_tutorials.ui.theme.Jetpack_Compose_Components_TutorialsTheme

@Composable
fun HeightScreen(navController: NavController?) {
    var mExpanded by remember { mutableStateOf(false) }
    var selectHeightUnit by remember { mutableStateOf("Cm") }
    var userSelectedHeight by rememberSaveable { mutableDoubleStateOf(4.0) }
    val heightUnit = listOf("Cm", "Feet")
    val heightInCmList = (98..252).toList().map { it.toDouble() }
    val heightsInFeet = listOf(1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bmi_bg_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .drawPreviewBorder(Color.Magenta),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .drawPreviewBorder(Color.Red),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .drawPreviewBorder(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.select_height),
                            color = colorResource(id = R.color.very_soft_yellow),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.drawPreviewBorder(),
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        val isWholeNumber = userSelectedHeight % 1.0 == 0.0
                        Text(
                            text = (if (isWholeNumber) userSelectedHeight.toInt() else userSelectedHeight).toString(),
                            color = colorResource(id = R.color.very_soft_yellow),
                            fontSize = 70.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.drawPreviewBorder(),
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier
                                .width(80.dp)
                                .height(30.dp)
                                .background(
                                    color = colorResource(id = R.color.Gunmetal),
                                    shape = RoundedCornerShape(40.dp)
                                )
                                .clickable { mExpanded = true }
                                .drawPreviewBorder(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                Text(
                                    text = selectHeightUnit,
                                    color = colorResource(id = R.color.very_soft_yellow),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "",
                                    tint = colorResource(
                                        id = R.color.very_soft_yellow
                                    )
                                )
                            }
                            DropdownMenu(
                                expanded = mExpanded,
                                onDismissRequest = { mExpanded = false },
                            ) {
                                heightUnit.forEach { label ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = label,
                                                //color = colorResource(id = R.color.very_soft_yellow),
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                        onClick = {
                                            selectHeightUnit = label
                                            mExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        //.fillMaxSize()
                        .drawPreviewBorder(Color.Cyan),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 70.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                        }
                        FadingEdgeNumberPicker(
                            modifier = Modifier
                                .padding(top = 60.dp, bottom = 60.dp, end = 90.dp)
                                .background(
                                    color = colorResource(id = R.color.Gunmetal),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            dataList = if(selectHeightUnit.contains(stringResource(id = R.string.cm))) heightInCmList else heightsInFeet,
                            selectedHeight = { userSelectedHeight = it },
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_drop_down),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier//.weight(1f)
                                .align(Alignment.Center)
                                .padding(start = 20.dp)
                                .rotate(90f)
                                .scale(1.3f)
                                .drawPreviewBorder(Color.Red)
                        )

                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.14f)
                    .padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                NextButton(
                    modifier = Modifier
                        .width(150.dp)
                        .height(70.dp)
                        .drawPreviewBorder(),
                    name = stringResource(id = R.string.prev),
                    background = R.color.Gunmetal,
                    textColor = R.color.very_soft_yellow
                ) {
                    navController?.popBackStack()
                }
                NextButton(
                    modifier = Modifier
                        .width(150.dp)
                        .height(70.dp)
                        .drawPreviewBorder(),
                    name = stringResource(id = R.string.next)
                ) {
                    navController?.navigate(Screen.WeightScreen.route)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FadingEdgeNumberPicker(
    modifier: Modifier = Modifier,
    dataList: List<Double> = emptyList(),
    selectedHeight: (Double) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val leftRightFade = Brush.verticalGradient(
        0f to Color.Transparent,
        0.4f to Color.White,
        0.4f to Color.White,
        1f to Color.Transparent
    )

    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(3)
    }

    LazyColumn(
        state = lazyListState,
        flingBehavior = snapBehavior,
        modifier = modifier
            .fadingEdge(brush = leftRightFade)
            .fillMaxWidth()
            .drawPreviewBorder(Color.Magenta),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        itemsIndexed(dataList) { index, item ->
            AnimatedText(
                lazyListState = lazyListState,
                index = index,
                item = item,
            ) {
                selectedHeight(it)
            }
        }
    }
}

@Composable
private fun AnimatedText(
    lazyListState: LazyListState,
    index: Int,
    item: Double,
    onSelection: (Double) -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }
    val textColor by remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            val itemInfo = visibleItemsInfo.firstOrNull { it.index == index }
            itemInfo?.let {
                val delta = it.size / 2 //use your custom logic
                val center = lazyListState.layoutInfo.viewportEndOffset / 2
                val childCenter = it.offset + it.size / 2
                val target = childCenter - center
                if (target in -delta..delta) {
                    isSelected = true
                    return@derivedStateOf R.color.very_soft_yellow
                }
            }
            isSelected = false
            R.color.white
        }
    }

    LaunchedEffect(isSelected) {
        if (isSelected) onSelection(item)
    }
    val isWholeNumber = item % 1.0 == 0.0
    val text = if (item in 100.0..250.0 || item in 3.0..9.5) {
        if (isWholeNumber) item.toInt().toString() else item.toString()
    } else ""
    Text(
        text = text,
        fontSize = if (isSelected) 28.sp else 20.sp,
        textAlign = TextAlign.Center,
        color = colorResource(id = textColor),
        modifier = Modifier
            .width(70.dp)
            .height(60.dp)
            .wrapContentHeight()
            .drawPreviewBorder()
    )
}

private fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

@Preview
@Composable
fun HeightScreenPreview() {
    Jetpack_Compose_Components_TutorialsTheme {
        HeightScreen(navController = null)
    }
}