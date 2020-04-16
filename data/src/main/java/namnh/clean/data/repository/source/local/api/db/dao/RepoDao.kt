package namnh.clean.data.repository.source.local.api.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import namnh.clean.data.model.RepoData

@Dao
abstract class RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun save(repos: List<RepoData>)

    @Query("SELECT * FROM repo WHERE name like :query")
    abstract suspend fun findById(query: String): List<RepoData>
}
