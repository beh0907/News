package com.skymilk.news.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.skymilk.news.presentation.bookmark.BookmarkScreen
import com.skymilk.news.presentation.bookmark.BookmarkViewModel
import com.skymilk.news.presentation.home.HomeScreen
import com.skymilk.news.presentation.home.HomeViewModel
import com.skymilk.news.presentation.onBoarding.OnBoardingScreen
import com.skymilk.news.presentation.onBoarding.OnBoardingViewModel
import com.skymilk.news.presentation.search.SearchScreen
import com.skymilk.news.presentation.search.SearchViewModel


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
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(articles = articles, navigate = {})

//                val viewModel: SearchViewModel = hiltViewModel()
//                val articles = viewModel.state.value.articles?.collectAsLazyPagingItems()
//                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})
                
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(state = viewModel.state.value, navigate = {})
            }
        }

    }
}