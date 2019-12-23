package com.app.schoolmanagement.students.auth.studentsignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.students.repositories.StudentSignupRepository

class StudentSignupViewModelFactory(val schoolSignupRepository: StudentSignupRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentSignupViewModel(
            schoolSignupRepository
        ) as T
    }
}