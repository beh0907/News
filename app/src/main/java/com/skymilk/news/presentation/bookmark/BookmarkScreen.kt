package com.skymilk.news.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.skymilk.news.R
import com.skymilk.news.domain.model.Article
import com.skymilk.news.presentation.Dimens.MediumPaddingSpacer
import com.skymilk.news.presentation.common.ArticlesList
import com.skymilk.news.presentation.nvgraph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = MediumPaddingSpacer,
                start = MediumPaddingSpacer,
                end = MediumPaddingSpacer
            )
            .statusBarsPadding()
    ) {
        Text(
            text = "북마크",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_title)
            )
        )

        //공백 mediumPaddingSpacer(24dp)
        Spacer(modifier = Modifier.height(MediumPaddingSpacer))

        ArticlesList(articles = state.articles, onclick = {navigateToDetails(it)})
    }


}