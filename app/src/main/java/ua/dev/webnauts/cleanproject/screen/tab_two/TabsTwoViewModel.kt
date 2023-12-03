package ua.dev.webnauts.cleanproject.screen.tab_two

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.dev.webnauts.cleanproject.state.UiStateDelegate
import ua.dev.webnauts.cleanproject.state.UiStateDelegateImpl
import javax.inject.Inject


@HiltViewModel
class TabsTwoViewModel @Inject constructor()  : ViewModel(), UiStateDelegate<
        TabsTwoViewModel.UiState, TabsTwoViewModel.Event> by UiStateDelegateImpl(UiState()) {

    data class UiState(
        val isLoading: Boolean = false,
        val title: String = "4432",
        val login: String = "4324",
        val password: String = "234234",
    )

    sealed interface Event {
        object GoToHome : Event
    }

}