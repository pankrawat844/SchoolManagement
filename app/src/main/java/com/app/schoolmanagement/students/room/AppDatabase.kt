package com.app.schoolmanagement.students.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.schoolmanagement.students.room.Dao.SchoolDao
import com.app.schoolmanagement.students.room.entity.School

@Database(
    entities = [School::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSchoolDao(): SchoolDao

    companion object {
        private var instance: AppDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock)
        {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Appdatabase.db"
            )
                .build()
    }
}