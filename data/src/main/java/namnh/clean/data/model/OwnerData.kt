package namnh.clean.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import namnh.clean.domain.entity.Owner

data class OwnerData(
    @Expose
    @SerializedName("id")
    val id: Long,
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("url")
    val url: String?
) : MapAbleData<Owner> {

    override fun map(): Owner {
        return Owner(id, login, url)
    }
}
