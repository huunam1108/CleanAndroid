package namnh.clean.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName
import namnh.clean.domain.entity.Owner
import namnh.clean.domain.entity.Repo

@Entity(
    indices = [
        Index("id"),
        Index("owner_login")],
    primaryKeys = ["name", "owner_login"]
)
data class RepoData(
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("full_name")
    val fullName: String,
    @field:SerializedName("description")
    val description: String?,
    @field:SerializedName("owner")
    @field:Embedded(prefix = "owner_")
    val owner: OwnerData,
    @field:SerializedName("stargazers_count")
    val stars: Int
) : BaseData(), MapAble<Repo> {

    data class OwnerData(
        @field:SerializedName("login")
        val login: String,
        @field:SerializedName("url")
        val url: String?
    ) : MapAble<Owner> {

        override fun map(): Owner {
            return Owner(login, url)
        }
    }

    override fun map(): Repo {
        return Repo(
            id = id,
            name = name,
            fullName = fullName,
            description = description,
            owner = owner.map(),
            stars = stars
        )
    }

    companion object {
        const val UNKNOWN_ID = -1
    }
}
