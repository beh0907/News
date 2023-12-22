package com.skymilk.news.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.skymilk.news.domain.model.Article
import com.skymilk.news.presentation.Dimens.MediumPaddingSpacer
import com.skymilk.news.presentation.common.ArticlesList
import com.skymilk.news.presentation.common.SearchBar
import com.skymilk.news.presentation.nvgraph.Route
import kotlinx.coroutines.flow.collect

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = MediumPaddingSpacer,
                start = MediumPaddingSpacer,
                end = MediumPaddingSpacer
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        //검색바
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {event(SearchEvent.SearchNews)}
        )

        //공백 mediumPaddingSpacer(24dp)
        Spacer(modifier = Modifier.height(MediumPaddingSpacer))

        //검색 결과 정보가 있다면 목록 출력
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
//                modifier = Modifier.padding(horizontal = MediumPaddingSpacer),
                articles = articles,
                onclick = {
                    navigateToDetails(it)
                }
            )
        }
    }

}