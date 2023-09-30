package com.example.ss1_githubuser.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.ui.settings.SettingsPreferences
import com.example.ss1_githubuser.ui.settings.dataStore
import com.example.ss1_githubuser.ui.viewmodel.ThemeSettingsViewModel
import com.example.ss1_githubuser.ui.viewmodel.ViewModelFactory
import com.google.android.material.switchmaterial.SwitchMaterial


class ThemeSettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_theme_setting, container, false)

        val switchTheme: SwitchMaterial = view.findViewById(R.id.switch_theme)

        val pref = SettingsPreferences.getInstance(requireActivity().dataStore)
        val myViewModel = ViewModelProvider(this, ViewModelFactory(pref))[ThemeSettingsViewModel::class.java]
        myViewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            myViewModel.saveThemeSetting(isChecked)
        }
        return view
    }
}
