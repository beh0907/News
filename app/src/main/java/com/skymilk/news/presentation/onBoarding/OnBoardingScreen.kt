package com.skymilk.news.presentation.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.skymilk.news.presentation.Dimens.MediumPaddingText
import com.skymilk.news.presentation.Dimens.PageIndicatorSize
import com.skymilk.news.presentation.common.NewsButton
import com.skymilk.news.presentation.common.NewsTextButton
import com.skymilk.news.presentation.onBoarding.components.OnBoardingPage
import com.skymilk.news.presentation.onBoarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                //페이지 상태에 따라 버튼 텍스트가 달라진다
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    else -> listOf("Back", "Start")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            //소개 화면
            OnBoardingPage(page = pages[index])
        }

        //공백
        Spacer(modifier = Modifier.weight(1f))

        //온보딩 페이지 인디케이터
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPaddingText)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorSize),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            //온보딩 페이지 이동 버튼
            Row(verticalAlignment = Alignment.CenterVertically) {

                val scope = rememberCoroutineScope()

                //Back 버튼
                //첫 페이지라면 생성하지 않는다
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch { pagerState.animateScrollToPage(page = pagerState.currentPage - 1) }
                        }
                    )
                }

                //Next or Start 버튼
                NewsButton(
                    text = buttonState.value[1],
                    onClick = {

                        scope.launch {
                            if (pagerState.currentPage == 2)
                                event(OnBoardingEvent.SaveAppEntry) // 실행 여부 저장하기
                            else
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))
    }
}