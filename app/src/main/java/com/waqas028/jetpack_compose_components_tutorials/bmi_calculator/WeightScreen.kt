package com.waqas028.jetpack_compose_components_tutorials.bmi_calculator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
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
fun WeightScreen(navController: NavController?) {
    var userSelectedWeight by rememberSaveable { mutableIntStateOf(45) }
    val weightList = (28..152).toList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bmi_bg_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .drawPreviewBorder(Color.Magenta),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.select_weight),
                color = colorResource(id = R.color.very_soft_yellow),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .drawPreviewBorder(),
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = userSelectedWeight.toInt().toString(),
                color = colorResource(id = R.color.very_soft_yellow),
                fontSize = 70.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.drawPreviewBorder(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .width(120.dp)
                    .height(30.dp)
                    .background(
                        color = colorResource(id = R.color.Gunmetal),
                        shape = RoundedCornerShape(40.dp)
                    )
                    .drawPreviewBorder(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.kilogram),
                    color = colorResource(id = R.color.very_soft_yellow),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier.padding(start = 30.dp, top = 70.dp, end = 30.dp, bottom = 0.dp)
            ){
                FadingEdgeNumberPicker(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.Gunmetal),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    dataList = weightList,
                    selectedWeight = { userSelectedWeight = it }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_drop_down),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier//.weight(1f)
                        .padding(top = 80.dp)
                        .align(Alignment.Center)
                        .rotate(180f)
                        .scale(1.3f)
                        .drawPreviewBorder(Color.Red)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.weight(.14f)
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
    dataList: List<Int> = emptyList(),
    selectedWeight: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val leftRightFade = Brush.horizontalGradient(
        0f to Color.Transparent,
        0.5f to Color.White,
        0.5f to Color.White,
        1f to Color.Transparent
    )


    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(3)
    }

    LazyRow(
        state = lazyListState,
        flingBehavior = snapBehavior,
        modifier = modifier
            .fadingEdge(brush = leftRightFade)
            .fillMaxWidth()
            .height(90.dp)
            .drawPreviewBorder(Color.Magenta),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(dataList) { index, item ->
            AnimatedText(
                lazyListState = lazyListState,
                index = index,
                item = item,
            ) {
                selectedWeight(it)
            }
        }
    }
}

@Composable
private fun AnimatedText(
    lazyListState: LazyListState,
    index: Int,
    item: Int,
    onSelection: (Int) -> Unit
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
    val text = if(item in 30..150){
        if (isWholeNumber) item.toString() else item.toString()
    }else ""
    Text(
        text = text,
        fontSize = if (isSelected) 28.sp else 20.sp,
        textAlign = TextAlign.Center,
        color = colorResource(id = textColor),
        modifier = Modifier
            .width(70.dp)
            .height(90.dp)
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
fun WeightScreenPreview() {
    Jetpack_Compose_Components_TutorialsTheme {
        WeightScreen(navController = null)
    }
}