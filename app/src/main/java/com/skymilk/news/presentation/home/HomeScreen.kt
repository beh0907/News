package com.skymilk.news.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.skymilk.news.R
import com.skymilk.news.domain.model.Article
import com.skymilk.news.presentation.Dimens
import com.skymilk.news.presentation.Dimens.MediumPaddingSpacer
import com.skymilk.news.presentation.common.ArticlesList
import com.skymilk.news.presentation.common.SearchBar
import com.skymilk.news.presentation.nvgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPaddingSpacer)
            .statusBarsPadding()
    ) {
        //앱 로고
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPaddingSpacer)
        )

        //공백
        Spacer(modifier = Modifier.height(MediumPaddingSpacer))

        //검색 바
        SearchBar(
            modifier = Modifier.padding(horizontal = Dimens.SearchBarHorizontalPadding),
            text = "",
            readOnly = true,
            onValueChange = {

            },
            onClick = {
                //메인화면에서 검색바를 터치하면 검색 화면으로 이동시킨다
                navigateToSearch()
            },
            onSearch = {

            }
        )

        //공백
        Spacer(modifier = Modifier.height(MediumPaddingSpacer))

        //화면 타이틀 정보
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPaddingSpacer)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        //공백
        Spacer(modifier = Modifier.height(MediumPaddingSpacer))

        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPaddingSpacer),
            articles = articles,
            onclick = {
                navigateToDetails(it)
            }
        )
    }
}