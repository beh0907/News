package com.skymilk.news.presentation.details

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skymilk.news.R
import com.skymilk.news.domain.model.Article
import com.skymilk.news.domain.model.Source
import com.skymilk.news.presentation.Dimens.ArticleImageHeight
import com.skymilk.news.presentation.Dimens.MediumPaddingSpacer
import com.skymilk.news.presentation.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                //해당 기사 URL로 이동
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)

                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                //기사 URL 공유
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEMPLATE, article.url)
                    it.type = "text/plain"

                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPaddingSpacer,
                end = MediumPaddingSpacer,
                top = MediumPaddingSpacer
            )
        ) {
            item {
                //메인 이미지
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                //공백 mediumPaddingSpacer(24dp)
                Spacer(modifier = Modifier.height(MediumPaddingSpacer))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {

    DetailsScreen(article = Article(
        source = Source(
            id = "engadget",
            name = "Engadget"
        ),
        author = "Lawrence Bonk",
        title = "NordVPN comes to the Apple TV",
        description = "Apple’s recently-released tvOS 17 update allows for native VPN apps\r\n and big-name providers are wasting no time. ExpressVPN dropped an app a couple of weeks ago\r\n and now the same is true of one of its primary competitors. NordVPN now has an official Apple T…",
        url = "https://www.engadget.com/nordvpn-comes-to-the-apple-tv-162030095.html",
        urlToImage = "https://s.yimg.com/ny/api/res/1.2/nxmgOoJGD97XKdGof_sVvg--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD02NzI-/https://s.yimg.com/os/creatr-uploaded-images/2023-12/98473830-9dc0-11ee-bf0f-d092ca365dd9",
        publishedAt = "2023-12-18T16:20:30Z",
        content = "Apples recently-released tvOS 17 update allows for native VPN apps\r\n and big-name providers are wasting no time. ExpressVPN dropped an app a couple of weeks ago\r\n and now the same is true of one of i… [+2136 chars]"

    ), event = {

    }) {

    }
}
