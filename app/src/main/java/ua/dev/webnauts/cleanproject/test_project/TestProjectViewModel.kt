package ua.dev.webnauts.cleanproject.test_project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ua.dev.webnauts.cleanproject.repository.Download
import ua.dev.webnauts.cleanproject.repository.DownloadRepository
import ua.dev.webnauts.cleanproject.repository.Downloadable
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TestProjectViewModel @Inject constructor(
    val downloadRepository : DownloadRepository
) : ViewModel() {

    val downloads: StateFlow<List<Download>> by lazy {
        downloadRepository.observe()
            .stateIn(scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList())
    }

    fun onAddDownload(downloadUrl: String) {
        downloadRepository.adds(
            t = Downloadable(
                urlString = downloadUrl,
                outputFilename = UUID.randomUUID().toString())
        )
    }

}