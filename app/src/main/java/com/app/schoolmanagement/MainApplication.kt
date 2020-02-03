package com.app.schoolmanagement

import android.app.Application
import com.app.schoolmanagement.admin.ui.staff.StaffViewModel
import com.app.schoolmanagement.admin.ui.staff.StaffViewModelFactory
import com.app.schoolmanagement.photopicker.loader.GlideImageLoader
import com.app.schoolmanagement.students.auth.schoollogin.SchoolLoginVIewModelFactory
import com.app.schoolmanagement.students.auth.schoollogin.SchoolLoginViewModel
import com.app.schoolmanagement.students.auth.studentlogin.StudentLoginViewModel
import com.app.schoolmanagement.students.auth.studentlogin.StudentLoginViewModelFactory
import com.app.schoolmanagement.students.auth.studentsignup.StudentSignupViewModel
import com.app.schoolmanagement.students.auth.studentsignup.StudentSignupViewModelFactory
import com.app.schoolmanagement.students.home.ui.home.HomeViewModel
import com.app.schoolmanagement.students.home.ui.home.HomeViewModelFactory
import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.repositories.SchoolLoginRepository
import com.app.schoolmanagement.students.repositories.StudentRepository
import com.app.schoolmanagement.students.repositories.StudentSignupRepository
import com.app.schoolmanagement.students.utils.NetworkConnectionInterceptor
import com.app.schoolmanagementadmin.timetable.BusInfoViewmodelFactory
import com.app.schoolmanagement.students.timetable.TimeTableViewmodel
import com.app.schoolmanagementadmin.timetable.TimeTableViewmodelFactory
import com.app.schoolmanagement.students.businfo.BusInfoViewmodel
import com.app.schoolmanagement.students.feeinfo.FeeInfoViewmodel
import com.app.schoolmanagementadmin.ui.feeinfo.FeeInfoViewmodelFactory
import lv.chi.photopicker.ChiliPhotoPicker
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))
//        bind() from singleton { SchoolLoginActivity(instance(),instance()) }
        bind() from singleton {
            SchoolLoginViewModel(
                instance()
            )
        }
        bind() from singleton { NetworkConnectionInterceptor(instance()) }

        bind() from singleton { MyApi(instance()) }
        bind() from singleton { SchoolLoginRepository(instance()) }
        bind() from singleton { SchoolLoginVIewModelFactory(instance()) }
        bind() from singleton { StudentRepository(instance()) }
        bind() from singleton { StudentLoginViewModelFactory(instance()) }
        bind() from singleton { StudentLoginViewModel(instance()) }

        bind() from singleton { StudentSignupRepository(instance()) }
        bind() from singleton { StudentSignupViewModelFactory(instance()) }
        bind() from singleton { StudentSignupViewModel(instance()) }

        bind() from singleton { HomeViewModelFactory(instance()) }
        bind() from singleton { HomeViewModel(instance()) }

//        bind() from singleton { AdminApi(instance()) }
//        bind() from singleton { AdminRepository(instance()) }
//        bind() from singleton { AdminLoginViewModel(instance()) }
//        bind() from singleton { AdminLoginViewModelFactory(instance()) }
//        bind() from singleton { AdminHomeViewModel(instance()) }
//        bind() from singleton { AdminHomeViewModelFactory(instance()) }

        bind() from singleton { StaffViewModel(instance()) }
        bind() from singleton { StaffViewModelFactory(instance()) }

        bind() from singleton { TimeTableViewmodel(instance()) }
        bind() from singleton { TimeTableViewmodelFactory(instance()) }

        bind() from singleton { BusInfoViewmodel(instance()) }
        bind() from singleton { BusInfoViewmodelFactory(instance()) }


        bind() from singleton { FeeInfoViewmodel(instance()) }
        bind() from singleton { FeeInfoViewmodelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        ChiliPhotoPicker.init(
            loader = GlideImageLoader(),
            authority = "com.app.schoolmanagement.fileprovider"
        )
    }

}