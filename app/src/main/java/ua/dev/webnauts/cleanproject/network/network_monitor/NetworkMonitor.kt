package ua.dev.webnauts.cleanproject.network.network_monitor

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}