package com.app.schoolmanagementadmin.ui.leave

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.leave.LeaveViewmodel
import com.app.schoolmanagement.students.repositories.StudentRepository

class LeaveViewmodelFactory(val repository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LeaveViewmodel(repository) as T
    }
}