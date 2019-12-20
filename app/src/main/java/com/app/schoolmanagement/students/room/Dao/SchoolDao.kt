package com.app.schoolmanagement.students.room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.schoolmanagement.students.room.entity.SCHOOL_ID
import com.app.schoolmanagement.students.room.entity.School

@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateninsert(school: School): Long

    @Query("select * from School where school_id=$SCHOOL_ID")
    fun getSchool(): LiveData<School>
}