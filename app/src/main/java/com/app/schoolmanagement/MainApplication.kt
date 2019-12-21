package com.app.schoolmanagement

import android.app.Application
import com.app.schoolmanagement.students.auth.schoollogin.SchoolLoginActivity
import com.app.schoolmanagement.students.auth.schoollogin.SchoolLoginVIewModelFactory
import com.app.schoolmanagement.students.auth.schoollogin.SchoolLoginViewModel
import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.repositories.SchoolLoginRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))
        bind() from singleton { SchoolLoginActivity() }
        bind() from singleton {
            SchoolLoginViewModel(
                instance()
            )
        }
        bind() from singleton { MyApi() }
        bind() from singleton { SchoolLoginRepository(instance()) }
        bind() from singleton {
            SchoolLoginVIewModelFactory(
                instance()
            )
        }
    }


}