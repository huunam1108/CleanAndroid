package namnh.clean.data.model

import namnh.clean.domain.entity.BaseEntity

interface MapAbleData<R : BaseEntity> {
    fun map(): R
}
