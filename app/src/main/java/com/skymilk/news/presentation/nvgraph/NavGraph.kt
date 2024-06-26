package com.skymilk.news.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.skymilk.news.presentation.bookmark.BookmarkScreen
import com.skymilk.news.presentation.bookmark.BookmarkViewModel
import com.skymilk.news.presentation.newsNavigator.NewsNavigator
import com.skymilk.news.presentation.onBoarding.OnBoardingScreen
import com.skymilk.news.presentation.onBoarding.OnBoardingViewModel


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        //온보딩 화면 네비게이션 설정
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()

                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        //앱 메인 화면 네비게이션 설정
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ) {
            composable(
                route = Route.NewsNavigationScreen.route
            ) {
                NewsNavigator()
            }
        }

    }
}