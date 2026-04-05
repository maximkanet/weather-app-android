package cz.cvut.zan.zavadmak.weatherapp.core.presentation.utils

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

inline fun <K, V> LazyListScope.itemGroups(
    map: Map<K, V>,
    crossinline itemContent: @Composable LazyItemScope.(key: K, items: V) -> Unit
) {
    map.map { (key, value) ->
        item(
            key = key
        ) {
            itemContent(key, value)
        }
    }
}