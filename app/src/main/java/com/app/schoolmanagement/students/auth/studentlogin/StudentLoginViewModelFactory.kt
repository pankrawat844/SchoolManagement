package com.app.schoolmanagement.students.auth.studentlogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.students.repositories.StudentLoginRepository

class StudentLoginViewModelFactory(val schoolLoginRepository: StudentLoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentLoginViewModel(
            schoolLoginRepository
        ) as T
    }
}