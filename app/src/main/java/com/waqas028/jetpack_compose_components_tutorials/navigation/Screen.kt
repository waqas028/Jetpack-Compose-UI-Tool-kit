package com.waqas028.jetpack_compose_components_tutorials.navigation

sealed class Screen(val route: String ){
    data object SelectGenderScreen : Screen("SelectGenderScreen")
    data object HeightScreen : Screen("HeightScreen")
    data object WeightScreen : Screen("WeightScreen")
}