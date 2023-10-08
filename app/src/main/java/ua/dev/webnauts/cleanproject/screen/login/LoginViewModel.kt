package ua.dev.webnauts.cleanproject.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(){

    private val _saveCount = mutableStateOf<Int?>(null)
    val saveCount: State<Int?> get() = _saveCount

    fun setRoundCorners(i: Int) {
        _saveCount.value = i
    }

}