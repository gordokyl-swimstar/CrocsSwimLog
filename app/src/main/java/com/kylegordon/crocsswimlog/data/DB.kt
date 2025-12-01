package com.kylegordon.crocsswimlog.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Entity(tableName = "SwimLogEntries")
data class SwimLogEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Int = 0,
    val dow: Date,
    val workoutLength: Int,
    val mainStroke: String,
    val totalYardage: Int,
    val photoLog: String? = null
)

@Dao
interface SwimLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: SwimLogEntry)

    @Query("SELECT * FROM SwimLogEntries ORDER BY dow DESC")
    fun getAllEntriesFlow(): Flow<List<SwimLogEntry>>


    @Query("SELECT * FROM SwimLogEntries WHERE entryId = :id LIMIT 1")
    suspend fun getEntryById(id: Int): SwimLogEntry?

    @Delete
    suspend fun deleteEntry(entry: SwimLogEntry)
}

@Database(
    entities = [SwimLogEntry::class],
    version = 2,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class SwimLogDatabase : RoomDatabase() {

    abstract fun swimLogDao(): SwimLogDao

    companion object {
        @Volatile
        private var INSTANCE: SwimLogDatabase? = null

        fun getDatabase(context: Context): SwimLogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SwimLogDatabase::class.java,
                    "swim_log_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}