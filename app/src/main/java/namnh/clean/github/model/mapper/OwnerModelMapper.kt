package namnh.clean.github.model.mapper

import namnh.clean.domain.entity.Owner
import namnh.clean.github.model.OwnerModel

class OwnerModelMapper : PresentationMapper<Owner, OwnerModel>() {

    override fun map(entity: Owner): OwnerModel {
        return OwnerModel(
            id = entity.id,
            login = entity.login,
            url = entity.url
        )
    }
}
