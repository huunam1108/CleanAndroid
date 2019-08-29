package namnh.clean.data.repository.source.local.api.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import namnh.clean.data.model.RepoData

@Dao
abstract class RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(repo: RepoData): Long

    @Query("SELECT * FROM repo WHERE name like :query")
    abstract fun findById(query: String): Maybe<List<RepoData>>
}
