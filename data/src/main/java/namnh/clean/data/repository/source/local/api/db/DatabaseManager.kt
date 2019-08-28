package namnh.clean.data.repository.source.local.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import namnh.clean.data.model.RepoData
import namnh.clean.data.model.UserData
import namnh.clean.data.repository.source.local.api.db.dao.UserDao
import namnh.clean.shared.DatabaseConfig

@Database(
    entities = [UserData::class, RepoData::class],
    version = DatabaseConfig.DB_VERSION
)
@TypeConverters(/*Add converters here.*/)
abstract class DatabaseManager : RoomDatabase() {
    abstract fun userDao(): UserDao
}
