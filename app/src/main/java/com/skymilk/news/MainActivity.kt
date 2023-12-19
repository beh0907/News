package com.skymilk.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.skymilk.news.domain.usecases.AppEntryUseCases
import com.skymilk.news.presentation.onBoarding.OnBoardingScreen
import com.skymilk.news.presentation.onBoarding.OnBoardingViewModel
import com.skymilk.news.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //시스템 UI를 무시하고 뷰 채우기
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //로딩 화면 출력
        installSplashScreen()

        //실행 여부 확인
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                println("appEntry : $it")
            }
        }

        setContent {
            NewsTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background))

                val viewModel: OnBoardingViewModel = hiltViewModel()

                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
    }
}