package com.cursokotlin.jetpackcomponentscatalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cursokotlin.jetpackcomponentscatalog.model.Routes
import com.cursokotlin.jetpackcomponentscatalog.model.Routes.Pantalla2
import com.cursokotlin.jetpackcomponentscatalog.model.Routes.Pantalla3

@ExperimentalFoundationApi
@Composable
fun NavigationComplete(){
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.Pantalla1.route
    ) {
        composable(Routes.Pantalla1.route) { Screen1(navigationController) }
        composable(Pantalla2.route) { Screen2(navigationController) }
        composable(Pantalla3.route) { Screen3(navigationController) }
        composable(
            Routes.Pantalla4.route,
            arguments = listOf(navArgument("age") { type = NavType.IntType })
        ) { backStackEntry ->
            Screen4(
                navigationController,
                backStackEntry.arguments?.getInt("age") ?: 0
            )
        }
        composable(
            Routes.Pantalla5.route,
            arguments = listOf(navArgument("name") { defaultValue = "Pepe" })
        )
        { backStackEntry ->
            Screen5(
                navigationController,
                backStackEntry.arguments?.getString("name")
            )

        }
    }
}

@Composable
fun Screen1(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(
            text = "Pantalla 1",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Pantalla2.route) })
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(text = "Pantalla 2", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                navController.navigate(
                    Pantalla3.route
                )
            })
    }
}

@Composable
fun Screen3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(
            text = "Pantalla 3",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla4.createRoute(29)) })
    }
}

@Composable
fun Screen4(navController: NavHostController, age: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(text = "Tengo $age años", modifier = Modifier.align(Alignment.Center).clickable { navController.navigate(
            "pantalla5") })
    }
}

@Composable
fun Screen5(navController: NavHostController, name: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Me llamo $name", modifier = Modifier.align(Alignment.Center))
    }
}