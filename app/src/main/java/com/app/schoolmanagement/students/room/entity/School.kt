package com.app.schoolmanagement.students.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val SCHOOL_ID = 0

@Entity
data class School(
    var id: Int? = null,
    var name: String? = null,
    var password: String? = null,
    var logo: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var school_id = SCHOOL_ID
}
