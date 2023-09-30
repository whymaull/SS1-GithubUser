package com.example.ss1_githubuser.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.ui.settings.SettingsPreferences
import com.example.ss1_githubuser.ui.settings.dataStore
import com.example.ss1_githubuser.ui.viewmodel.ThemeSettingsViewModel
import com.example.ss1_githubuser.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

	@SuppressLint("MissingInflatedId")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		//val switchTheme: SwitchMaterial =findViewById(R.id.switch_theme)

		val pref = SettingsPreferences.getInstance(this.dataStore)
		val myViewModel = ViewModelProvider(this, ViewModelFactory(pref))[ThemeSettingsViewModel::class.java]
		myViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
			if (isDarkModeActive) {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

			} else {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

			}
		}
	}
}