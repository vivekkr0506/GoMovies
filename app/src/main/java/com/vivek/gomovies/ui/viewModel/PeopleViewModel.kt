package com.vivek.gomovies.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vivek.gomovies.model.PersonDetails
import com.vivek.gomovies.model.PersonImage
import com.vivek.gomovies.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val repository: PeopleRepository) : ViewModel() {
    val popularPeopleFlow = repository.getPopularPeoplePaging().cachedIn(viewModelScope)
    fun searchPeopleFlow(query: String) = repository.searchPeopleStream(query)

    fun getPersonDetails(personId: Int): Flow<PersonDetails> {
        return repository.getPersonDetails(personId)
    }
    private val _personImages = MutableStateFlow<List<PersonImage>>(emptyList())
    val personImages: StateFlow<List<PersonImage>> = _personImages

    fun fetchPersonImages(personId: Int) {
        viewModelScope.launch {
            try {
                val images = repository.getPersonImages(personId)
                _personImages.value = images
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}