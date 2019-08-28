package namnh.clean.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import namnh.clean.domain.entity.User

@Entity(primaryKeys = ["login"], tableName = "user")
data class UserData(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("company")
    val company: String?,
    @field:SerializedName("repos_url")
    val reposUrl: String?,
    @field:SerializedName("blog")
    val blog: String?
) : BaseData(), MapAble<User> {
    override fun map(): User {
        return User(
            login = login,
            avatarUrl = avatarUrl,
            name = name,
            company = company,
            reposUrl = reposUrl,
            blog = blog
        )
    }
}
