package com.waqas028.jetpack_compose_components_tutorials.bmi_calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.waqas028.jetpack_compose_components_tutorials.R

@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    name: String,
    background: Int = R.color.very_soft_yellow,
    textColor: Int = R.color.black,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = { onButtonClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(colorResource(id = background)),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = textColor)
            )
        }
    }
}