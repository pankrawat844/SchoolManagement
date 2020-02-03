package com.app.schoolmanagementadmin.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.businfo.BusInfoViewmodel
import com.app.schoolmanagement.students.repositories.StudentRepository

class BusInfoViewmodelFactory(val repository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BusInfoViewmodel(repository) as T
    }
}