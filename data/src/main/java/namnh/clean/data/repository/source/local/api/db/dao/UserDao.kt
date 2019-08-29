package namnh.clean.data.repository.source.local.api.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import namnh.clean.data.model.UserData

@Dao
abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(user: UserData): Long

    @Query("SELECT * FROM user WHERE login = :id")
    abstract fun findById(id: String): Maybe<UserData>
}
