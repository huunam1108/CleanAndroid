package namnh.clean.data.model

import namnh.clean.domain.entity.BaseEntity
import javax.inject.Inject

@Suppress("unused")
open class DataMapper @Inject constructor() {

    open fun <R : BaseEntity> map(): (MapAbleData<R>) -> R = {
        it.map()
    }

    open fun <R : BaseEntity> nullableMap(): (MapAbleData<R>?) -> R? = {
        it?.map()
    }

    open fun <R : BaseEntity> collectionMap(): (Collection<MapAbleData<R>>) -> List<R> = {
        it.map(map())
    }

    open fun <R : BaseEntity> nullableCollectionMap(): (Collection<MapAbleData<R>>?) -> List<R>? = {
        it?.map(map())
    }
}
