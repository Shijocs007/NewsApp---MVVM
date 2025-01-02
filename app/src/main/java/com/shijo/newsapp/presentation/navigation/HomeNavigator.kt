package com.shijo.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.country.CountryListScreen
import com.shijo.newsapp.presentation.country.CountryListViewModel
import com.shijo.newsapp.presentation.headlines.HeadlineScreen
import com.shijo.newsapp.presentation.headlines.HeadlineViewModel

@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.HeadLineScreen,
        modifier = modifier
    ) {
        composable<Route.HeadLineScreen> { backStackEntry ->
            val viewModel: HeadlineViewModel = hiltViewModel()
            HeadlineScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onCountryClicked = {
                    navController.navigate(Route.CountryListScreen)
                }
            )
        }
        composable<Route.SearchScreen> { backStackEntry ->
            ErrorScreen(message = "Search screen will be implemented.")
        }
        composable<Route.BookmarkScreen> { backStackEntry ->
            ErrorScreen(message = "Bookmark screen will be implemented")
        }
        composable<Route.CountryListScreen> { backStackEntry ->
            val viewModel : CountryListViewModel = hiltViewModel()
            CountryListScreen(uiState = viewModel.uiState.collectAsState().value)
        }
    }
}