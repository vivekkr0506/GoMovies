package com.vivek.gomovies.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vivek.gomovies.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val repository: PeopleRepository) : ViewModel() {
    val popularPeopleFlow = repository.getPopularPeoplePaging().cachedIn(viewModelScope)
}