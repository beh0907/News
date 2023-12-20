package com.skymilk.news.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skymilk.news.R
import com.skymilk.news.domain.model.Article
import com.skymilk.news.domain.model.Source
import com.skymilk.news.presentation.Dimens.ArticleCardImageSize
import com.skymilk.news.presentation.Dimens.ExtraSmallPadding
import com.skymilk.news.presentation.Dimens.SmallIconSize
import com.skymilk.news.presentation.Dimens.SmallPadding
import com.skymilk.news.ui.theme.NewsTheme

//뉴스 목록 아이템 카드
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(modifier = Modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .size(ArticleCardImageSize)
                .clip(MaterialTheme.shapes.medium), // 테두리 둥글게
            model = ImageRequest.Builder(context).data(article.urlToImage).build(), // 이미지 불러오기
            contentDescription = null,
            contentScale = ContentScale.Crop // 사이즈에 맞게 이미지 자르기
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardImageSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Spacer(modifier = Modifier.width(SmallPadding))

                Icon(
                    painter = painterResource(id = R.drawable.ic_time), contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(SmallPadding))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsTheme {
        ArticleCard(
            article = Article(
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

            ),
            onClick = {
                println("HI! HI! HI! HI!")
            }
        )
    }
}