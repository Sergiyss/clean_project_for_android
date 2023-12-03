package ua.dev.webnauts.cleanproject.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock



/**
 * UiStateDelegate - это интерфейс, который позволяет вам управлять UI-состоянием в вашей ViewModel.
 * С его помощью вы можете легко обновлять UI-состояние и отправлять события, связанные с UI.
 * Ваш код позволяет вам декларативно описывать состояние UI и асинхронно обновлять его.
 * */
@Composable
fun <R> UiStateDelegate<R, *>.collectUiState() = this.uiStateFlow.collectAsState()

/**
 * UiStateDelegate - это интерфейс, который позволяет вам управлять UI-состоянием в вашей ViewModel.
 * С его помощью вы можете легко обновлять UI-состояние и отправлять события, связанные с UI.
 * Ваш код позволяет вам декларативно описывать состояние UI и асинхронно обновлять его.
 * */
@Composable
fun <R> UiStateDelegate<R, *>.collectWithLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
) = this.uiStateFlow.collectAsStateWithLifecycle(
    minActiveState = minActiveState,
)


/**
 * UiState — должен быть класс данных, неизменяемый
 */
interface UiStateDelegate<UiState, Event> {

    /**
     * Декларативное описание пользовательского интерфейса на основе текущего состояния.
     */
    val uiStateFlow: StateFlow<UiState>

    val singleEvents: Flow<Event>

    /**
     * Состояние доступно только для чтения
     * Единственный способ изменить состояние — это выполнить[уменьшить] действие,
     * объект, описывающий произошедшее.
     */
    val UiStateDelegate<UiState, Event>.uiState: UiState

    /**
     * Преобразует состояние пользовательского интерфейса, используя указанное преобразование.
     *
     * @param Transform — функция преобразования состояния пользовательского интерфейса.
     */
    suspend fun UiStateDelegate<UiState, Event>.updateUiState(
        transform: (uiState: UiState) -> UiState,
    )

    /**
     * Изменение состояния без блокировки сопрограммы.
     */
    fun UiStateDelegate<UiState, Event>.asyncUpdateUiState(
        coroutineScope: CoroutineScope,
        transform: (state: UiState) -> UiState,
    ): Job

    suspend fun UiStateDelegate<UiState, Event>.sendEvent(event: Event)
}

/**
 * Реализация делегата для управления состоянием.
 * Этот делегат хранит состояние пользовательского интерфейса и управляет им.
 *
 * @param mutexState Мьютекс для синхронизации доступа к состоянию.
 * @param InitialUiState Начальное состояние пользовательского интерфейса.
 * @param SingleLiveEventCapacity Пропускная способность канала для SingleLiveEvent.
 */
class UiStateDelegateImpl<UiState, Event>(
    initialUiState: UiState,
    singleLiveEventCapacity: Int = Channel.BUFFERED,
    private val mutexState: Mutex = Mutex()
) : UiStateDelegate<UiState, Event> {

    /**
     * The source of truth that drives our app.
     */
    private val uiMutableStateFlow = MutableStateFlow(initialUiState)

    override val uiStateFlow: StateFlow<UiState>
        get() = uiMutableStateFlow.asStateFlow()

    override val UiStateDelegate<UiState, Event>.uiState: UiState
        get() = uiMutableStateFlow.value

    private val singleEventsChannel = Channel<Event>(singleLiveEventCapacity)

    override val singleEvents: Flow<Event>
        get() = singleEventsChannel.receiveAsFlow()

    override suspend fun UiStateDelegate<UiState, Event>.updateUiState(
        transform: (uiState: UiState) -> UiState,
    ) {
        mutexState.withLock {
            uiMutableStateFlow.emit(transform(uiState))
        }
    }

    override suspend fun UiStateDelegate<UiState, Event>.sendEvent(event: Event) {
        singleEventsChannel.send(event)
    }

    override fun UiStateDelegate<UiState, Event>.asyncUpdateUiState(
        coroutineScope: CoroutineScope,
        transform: (state: UiState) -> UiState,
    ): Job {
        return coroutineScope.launch {
            updateUiState { state -> transform(state) }
        }
    }
}