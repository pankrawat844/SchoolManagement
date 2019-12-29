package com.app.schoolmanagement.students.auth.studentlogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.students.repositories.StudentRepository

class StudentLoginViewModelFactory(val schoolRepository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentLoginViewModel(
            schoolRepository
        ) as T
    }
}