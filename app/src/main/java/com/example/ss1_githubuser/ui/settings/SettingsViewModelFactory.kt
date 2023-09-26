//package com.example.ss1_githubuser.ui.settings
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.ss1_githubuser.ui.viewmodel.MyViewModel
//import com.example.ss1_githubuser.ui.viewmodel.ThemeSettingsViewModel
//
//class SettingsViewModelFactory(private val pref: SettingsPreferences) : ViewModelProvider.NewInstanceFactory() {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ThemeSettingsViewModel::class.java)) {
//            return ThemeSettingsViewModel(pref) as T
//        } else if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
//            return MyViewModel(pref) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class:" + modelClass.name)
//    }
//}