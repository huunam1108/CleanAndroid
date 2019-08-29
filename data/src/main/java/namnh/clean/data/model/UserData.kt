package namnh.clean.data.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import namnh.clean.domain.entity.User

@Entity(primaryKeys = ["login"], tableName = "user")
data class UserData(
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("company")
    val company: String?,
    @Expose
    @SerializedName("repos_url")
    val reposUrl: String?,
    @Expose
    @SerializedName("blog")
    val blog: String?
) : BaseData(), MapAbleData<User> {
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
