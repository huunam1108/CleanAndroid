package namnh.clean.github.model.mapper

import namnh.clean.domain.entity.User
import namnh.clean.github.model.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor() : PresentationMapper<User, UserModel>() {

    override fun map(entity: User): UserModel {
        return UserModel(
            login = entity.login,
            avatarUrl = entity.avatarUrl,
            name = entity.name,
            company = entity.company,
            reposUrl = entity.reposUrl,
            blog = entity.blog
        )
    }
}
