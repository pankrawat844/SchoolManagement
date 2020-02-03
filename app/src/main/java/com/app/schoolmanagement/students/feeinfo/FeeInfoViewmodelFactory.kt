package com.app.schoolmanagementadmin.ui.feeinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.feeinfo.FeeInfoViewmodel
import com.app.schoolmanagement.students.repositories.StudentRepository

class FeeInfoViewmodelFactory(val repository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeeInfoViewmodel(repository) as T
    }
}