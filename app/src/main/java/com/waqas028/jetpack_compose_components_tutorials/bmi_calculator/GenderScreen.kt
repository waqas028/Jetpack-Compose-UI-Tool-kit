package com.waqas028.jetpack_compose_components_tutorials.bmi_calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.waqas028.jetpack_compose_components_tutorials.R
import com.waqas028.jetpack_compose_components_tutorials.navigation.Screen
import com.waqas028.jetpack_compose_components_tutorials.ui.theme.Jetpack_Compose_Components_TutorialsTheme

@Composable
fun GenderScreen(navController: NavController?) {
    var selectGender by rememberSaveable { mutableStateOf("Female") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bmi_bg_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .drawPreviewBorder(Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.select_gender),
                    color = colorResource(id = R.color.very_soft_yellow),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.drawPreviewBorder(),
                )
                Spacer(modifier = Modifier.height(30.dp))
                GenderBox(
                    name = stringResource(id = R.string.male),
                    icon = R.drawable.ic_male,
                    selectGender = selectGender
                ) {
                    selectGender = it
                }
                Spacer(modifier = Modifier.height(20.dp))
                GenderBox(
                    name = stringResource(id = R.string.female),
                    icon = R.drawable.ic_female,
                    selectGender = selectGender
                ) {
                    selectGender = it
                }
                Spacer(modifier = Modifier.height(20.dp))
                GenderBox(
                    name = stringResource(id = R.string.transgender),
                    icon = R.drawable.ic_transgender,
                    selectGender = selectGender
                ) {
                    selectGender = it
                }
            }
            NextButton(
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
                    .drawPreviewBorder(),
                name = stringResource(id = R.string.next)
            ) {
                navController?.navigate(Screen.HeightScreen.route)
            }
        }

    }
}

@Composable
private fun GenderBox(
    modifier: Modifier = Modifier,
    name: String,
    icon: Int,
    selectGender: String,
    onSelectGender: (String) -> Unit
) {
    Column(
        modifier = modifier
            .size(130.dp)
            .background(
                color = colorResource(id = if (selectGender == name) R.color.very_soft_yellow else R.color.Gunmetal),
                shape = RoundedCornerShape(40.dp)
            )
            .clickable { onSelectGender(name) }
            .drawPreviewBorder(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = colorResource(id = if (selectGender == name) R.color.black else R.color.very_soft_yellow))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = name,
            color = colorResource(id = if (selectGender == name) R.color.black else R.color.very_soft_yellow),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Preview(device = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240")
@Composable
fun GenderScreenPreview() {
    Jetpack_Compose_Components_TutorialsTheme {
        GenderScreen(navController = null)
    }
}

val isInPreview @Composable get() = LocalInspectionMode.current

fun Modifier.drawPreviewBorder(color: Color = Color.Gray): Modifier = composed {
    composed {
        if (isInPreview) {
            this then Modifier.border(width = 1.dp, color = color)
        } else {
            this
        }
    }
}