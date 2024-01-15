package com.skymilk.news.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skymilk.news.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    val news = newsUseCases.getNews(
        sources = listOf("bbc-news", "abc-news") // 뉴스 출처 필터
    ).cachedIn(viewModelScope) // paging3 캐싱


    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.UpdateScrollValue -> updateScrollValue(event.value)
            is HomeEvent.UpdateMaxScrollValue -> updateMaxScrollValue(event.value)
        }
    }

    private fun updateScrollValue(value: Int) {
        _state.value = state.value.copy(scrollValue =  value)
    }

    private fun updateMaxScrollValue(value: Int) {
        _state.value = state.value.copy(maxScrollingValue =  value)
    }
}