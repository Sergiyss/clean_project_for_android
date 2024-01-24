package ua.dev.webnauts.cleanproject.repository

import kotlinx.coroutines.flow.Flow

interface Repository<T, V> {

    fun adds(t : T)

    fun observe() : Flow<V>
}