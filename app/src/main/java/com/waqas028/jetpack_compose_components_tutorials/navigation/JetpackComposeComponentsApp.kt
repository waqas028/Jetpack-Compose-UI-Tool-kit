package com.waqas028.jetpack_compose_components_tutorials.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.waqas028.jetpack_compose_components_tutorials.bmi_calculator.GenderScreen
import com.waqas028.jetpack_compose_components_tutorials.bmi_calculator.HeightScreen
import com.waqas028.jetpack_compose_components_tutorials.bmi_calculator.WeightScreen

@Composable
fun JetpackComposeComponentsApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SelectGenderScreen.route){

        composable(route = Screen.SelectGenderScreen.route){
            GenderScreen(navController)
        }

        composable(route = Screen.HeightScreen.route){
            HeightScreen(navController)
        }

        composable(route = Screen.WeightScreen.route){
            WeightScreen(navController)
        }
    }
}