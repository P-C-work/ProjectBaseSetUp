package com.example.projectbasesetup.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbasesetup.models.CatsDataModel
import com.example.projectbasesetup.repo.CatsRepository
import com.example.projectbasesetup.utils.ErrorsMessage
import com.example.projectbasesetup.utils.Event
import com.example.projectbasesetup.data.NetworkResult
import com.pddstudio.preferences.encrypted.EncryptedPreferences
import kotlinx.coroutines.launch

class MainViewModel(
    val application: Application,
    val encryptedPreferences: EncryptedPreferences,
    private val catsRepository: CatsRepository
) : ViewModel() {


    companion object {
        val TAG: String = MainViewModel::class.java.canonicalName
    }

    private val _catsData = MutableLiveData<List<CatsDataModel>>()
    val catsData: LiveData<List<CatsDataModel>>
        get() = _catsData

    //UI
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getCatsData() {
        viewModelScope.launch {
            catsRepository.fetchCats().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        _isLoading.postValue(false)
                        _catsData.postValue(it.data!!)
                    }

                    is NetworkResult.Error -> {
                        _isLoading.postValue(false)
                        _error.postValue(it.message ?: ErrorsMessage.gotApiCallError)
                    }

                    is NetworkResult.Loading -> {
                        _isLoading.postValue(true)
                    }

                }
            }

        }
    }


}