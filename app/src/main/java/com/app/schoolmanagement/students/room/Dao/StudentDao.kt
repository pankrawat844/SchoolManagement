package com.app.schoolmanagement.students.room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.schoolmanagement.students.network.response.Student
import com.app.schoolmanagement.students.room.entity.STUDENT_ID

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateInsert(student:Student):Long

    @Query("select * from Student where studentId=$STUDENT_ID")
    fun getStudent():LiveData<Student>

}