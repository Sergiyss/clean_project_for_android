package ua.dev.webnauts.cleanproject.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.dev.webnauts.cleanproject.network.network_monitor.ConnectivityManagerNetworkMonitor
import ua.dev.webnauts.cleanproject.network.network_monitor.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
