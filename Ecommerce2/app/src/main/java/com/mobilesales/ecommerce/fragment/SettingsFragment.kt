package com.mobilesales.ecommerce.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.mobilesales.ecommerce.R


class SettingsFragment : PreferenceFragmentCompat(){


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}