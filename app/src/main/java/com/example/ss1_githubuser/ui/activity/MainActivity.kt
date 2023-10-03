package com.example.ss1_githubuser.ui.activity

import StartFragment
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.ui.fragment.DetailFragment
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
		val username = intent.getStringExtra("username")
		if (username != null) {
			val fragmentManager = supportFragmentManager
			val detailFragment = DetailFragment()
			val bundle = Bundle()
			bundle.putString("username", username)
			detailFragment.arguments = bundle

			val fragment = fragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName)
			if (fragment !is DetailFragment) {
				Log.d(
					"MyFlexibleFragment",
					"Fragment Name :" + DetailFragment::class.java.simpleName
				)
				fragmentManager
					.beginTransaction()
					.add(
						R.id.frame_container,
						detailFragment,
						DetailFragment::class.java.simpleName
					)
					.commit()
			}
		}else {
			val fragmentManager = supportFragmentManager
			val startFragment = StartFragment()
			val fragment = fragmentManager.findFragmentByTag(StartFragment::class.java.simpleName)
			if (fragment !is StartFragment) {
				Log.d("MyFlexibleFragment", "Fragment Name :" + StartFragment::class.java.simpleName)
				fragmentManager
					.beginTransaction()
					.add(R.id.frame_container,
						startFragment,
						StartFragment::class.java.simpleName)
					.commit()
			}
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		return super.onCreateOptionsMenu(menu)
	}
}