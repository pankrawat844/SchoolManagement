package com.app.schoolmanagementadmin.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.repositories.StudentRepository
import com.app.schoolmanagement.students.timetable.TimeTableViewmodel

class TimeTableViewmodelFactory(val repository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TimeTableViewmodel(repository) as T
    }
}