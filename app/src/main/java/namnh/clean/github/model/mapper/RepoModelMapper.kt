package namnh.clean.github.model.mapper

import namnh.clean.domain.entity.Repo
import namnh.clean.github.model.RepoModel
import javax.inject.Inject

class RepoModelMapper @Inject constructor(private val ownerMapper: OwnerModelMapper) :
    PresentationMapper<Repo, RepoModel>() {

    override fun map(entity: Repo): RepoModel {
        return RepoModel(
            id = entity.id,
            name = entity.name,
            fullName = entity.fullName,
            description = entity.description,
            owner = ownerMapper.map(entity.owner),
            stars = entity.stars
        )
    }
}
