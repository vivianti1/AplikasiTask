package com.d121211056.task.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d121211056.task.domain.GameItem
import com.d121211056.task.domain.GetGamesFromApiUseCase
import com.d121211056.task.domain.GetGamesFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGamesFromApiUseCase: GetGamesFromApiUseCase,
    private val getGamesFromDatabaseUseCase: GetGamesFromDatabaseUseCase
) : ViewModel() {

    private val _games = MutableLiveData<List<GameItem>>()
    val games:LiveData<List<GameItem>> get() = _games

    private val _message = MutableLiveData<String>()
    val  message: LiveData<String> get() = _message

    private val _recyclerViewVisibility = MutableLiveData<Boolean>()
    val  recyclerViewVisibility: LiveData<Boolean> get() = _recyclerViewVisibility

    private val _progressBarVivibility = MutableLiveData<Boolean>()
    val  progressBarVivibility: LiveData<Boolean> get() = _progressBarVivibility

    private val _textViewVivibility = MutableLiveData<Boolean>()
    val  textViewVivibility: LiveData<Boolean> get() = _textViewVivibility

    fun getGamesFromApi(){
        showProgressBar()

        viewModelScope.launch {
            try {
                val games = getGamesFromApiUseCase()

                if (games.isNotEmpty()){
                    _games.value =games
                    showRecyclerView()
                }
            }catch (e:Exception){ getGamesFromDatabaseUseCase()}
        }
    }

    private fun getGamesFromDatabase(){
        viewModelScope.launch {
            val games = getGamesFromDatabaseUseCase()

            if (games.isNotEmpty()){
                _games.value = games
                showRecyclerView()
            }else{
                _message.value ="Error"
                showTextView()
            }
        }
    }

    private fun showRecyclerView(){
        _recyclerViewVisibility.value =true
        _textViewVivibility.value =false
        _progressBarVivibility.value =false
    }

    private fun showProgressBar(){
        _recyclerViewVisibility.value =true
        _textViewVivibility.value =false
        _progressBarVivibility.value
    }

    private fun showTextView(){
        _recyclerViewVisibility.value =true
        _textViewVivibility.value =false
        _progressBarVivibility.value
    }
}