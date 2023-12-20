package com.skymilk.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skymilk.news.domain.usecases.appEntry.AppEntryUseCases
import com.skymilk.news.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition = mutableStateOf(true)
        private set

    //기본 시작 화면 온보딩 네비게이션
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set // 외부의 setter를 제한한다

    init {
        //실행 여부 확인
        appEntryUseCases.readAppEntry().onEach { isAppEntry ->

            //실행 이력이 있다면 온보딩 화면 스킵
            if (isAppEntry)
                startDestination = Route.NewsNavigation.route
            else
                startDestination = Route.AppStartNavigation.route

            //스플래시 화면 출력 시간
            delay(500)
            splashCondition.value = false
        }.launchIn(viewModelScope) // viewModelScope 내에서 동작하도록 설정
    }
}