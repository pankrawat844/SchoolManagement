package com.app.schoolmanagement.students.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.students.repositories.StudentRepository

class HomeViewModelFactory(val studentRepository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            studentRepository
        ) as T
    }
}