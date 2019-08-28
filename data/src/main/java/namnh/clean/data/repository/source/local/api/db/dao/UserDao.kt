package namnh.clean.data.repository.source.local.api.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import namnh.clean.data.model.UserData

@Dao
abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(user: UserData)

    @Query("SELECT * FROM user WHERE login = :id")
    abstract fun findById(id: String): Single<UserData>
}
