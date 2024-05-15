package com.example.tpisoftwaretest.presentation.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tpisoftwaretest.data.repository.MainRepository
import com.example.tpisoftwaretest.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class MainViewModel constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun fetchPlaces(lang: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.getPlaces(lang = lang)))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }
}