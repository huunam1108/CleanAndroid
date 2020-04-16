package namnh.clean.data.model

import namnh.clean.domain.entity.BaseEntity

@Suppress("unused")
open class DataMapper {

    open fun <R : BaseEntity> map(data: MapAbleData<R>): R {
        return data.map()
    }

    open fun <R : BaseEntity> nullableMap(data: MapAbleData<R>?): R? {
        return data?.map()
    }

    open fun <R : BaseEntity> collectionMap(collections: Collection<MapAbleData<R>>): List<R> {
        return collections.map {
            map(it)
        }
    }

    open fun <R : BaseEntity> nullableCollectionMap(collections: Collection<MapAbleData<R>>?): List<R>? {
        return collections?.map {
            map(it)
        }
    }
}
