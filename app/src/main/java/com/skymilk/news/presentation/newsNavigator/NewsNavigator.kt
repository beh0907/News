@file:OptIn(ExperimentalMaterial3Api::class)

package com.skymilk.news.presentation.newsNavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.skymilk.news.R
import com.skymilk.news.domain.model.Article
import com.skymilk.news.presentation.bookmark.BookmarkScreen
import com.skymilk.news.presentation.bookmark.BookmarkViewModel
import com.skymilk.news.presentation.details.DetailsEvent
import com.skymilk.news.presentation.details.DetailsScreen
import com.skymilk.news.presentation.details.DetailsViewModel
import com.skymilk.news.presentation.home.HomeScreen
import com.skymilk.news.presentation.home.HomeViewModel
import com.skymilk.news.presentation.newsNavigator.components.BottomNavigationItem
import com.skymilk.news.presentation.newsNavigator.components.NewsBottomNavigation
import com.skymilk.news.presentation.nvgraph.Route
import com.skymilk.news.presentation.search.SearchScreen
import com.skymilk.news.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, title = "홈"),
            BottomNavigationItem(icon = R.drawable.ic_search, title = "검색"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, title = "북마크")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            //isBottomBarVisible 상태 정보 체크 처리
            if (!isBottomBarVisible) return@Scaffold

            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigationToTab(
                            navController,
                            Route.HomeScreen.route
                        )

                        1 -> navigationToTab(
                            navController,
                            Route.SearchScreen.route
                        )

                        2 -> navigationToTab(
                            navController,
                            Route.BookmarkScreen.route
                        )
                    }
                }
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier
                .padding(bottom = bottomPadding)
        ) {

            //메인 화면 탭
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigationToTab(
                            navController,
                            Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = {
                        navigationToDetail(
                            navController,
                            it
                        )
                    }
                )
            }

            //검색 화면 탭
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigationToDetail(
                            navController,
                            it
                        )
                    })
            }

            //북마크 화면 탭
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails = {
                    navigationToDetail(
                        navController,
                        it
                    )
                })
            }

            //상세 화면 탭
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()

                if (viewModel.message != null) {
                    Toast.makeText(LocalContext.current, viewModel.message, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveMessage)
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let {
                        DetailsScreen(
                            article = it,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() })
                    }

            }
        }
    }
}

fun navigationToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

fun navigationToDetail(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)

    navController.navigate(
        route = Route.DetailsScreen.route
    )
}