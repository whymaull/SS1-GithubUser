package com.example.ss1_githubuser.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.ui.settings.SettingsPreferences
import com.example.ss1_githubuser.ui.viewmodel.ThemeSettingsViewModel
import com.google.android.material.switchmaterial.SwitchMaterial

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ThemeSettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_theme_setting, container, false)
        val switchTheme: SwitchMaterial = view.findViewById(R.id.switch_theme)

        val pref = SettingsPreferences.getInstance(requireActivity().dataStore)
//        //val themeSettingsViewModel = ViewModelProvider(this, SettingsViewModelFactory(pref))[ThemeSettingsViewModel::class.java]
//
//
//        themeSettingsViewModel.getThemeSettings().observe(viewLifecycleOwner) { isLightModeActive: Boolean ->
//            if (isLightModeActive) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                switchTheme.isChecked = true
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                switchTheme.isChecked = false
//            }
//        }
//
//        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
//            themeSettingsViewModel.saveThemeSetting(isChecked)
//        }

        return view
    }
}
