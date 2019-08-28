package namnh.clean.data.model

import namnh.clean.domain.entity.BaseEntity

interface MapAble<R : BaseEntity> {
    fun map(): R
}

@Suppress("unused")
internal fun <R : BaseEntity> MapAble<R>?.nullableMap(): R? {
    return this?.let { map() }
}

@Suppress("unused")
internal fun <R : BaseEntity> Collection<MapAble<R>>.collectionMap(): List<R> {
    return this@collectionMap.map {
        it.map()
    }
}

@Suppress("unused")
internal fun <R : BaseEntity> Collection<MapAble<R>>?.nullableCollectionMap(): List<R>? {
    return this@nullableCollectionMap?.map {
        it.map()
    }
}
