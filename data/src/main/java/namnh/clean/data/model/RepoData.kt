package namnh.clean.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import namnh.clean.domain.entity.Repo

@Entity(
    indices = [
        Index("id"),
        Index("owner_login")],
    primaryKeys = ["name", "owner_login"],
    tableName = "repo"
)
data class RepoData(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("full_name")
    val fullName: String,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("owner")
    @Embedded(prefix = "owner_")
    val owner: OwnerData,
    @Expose
    @SerializedName("stargazers_count")
    val stars: Int
) : BaseData(), MapAbleData<Repo> {

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
}
