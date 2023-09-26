package com.example.projectbasesetup.utils

import com.example.projectbasesetup.BuildConfig


class Constants {
    companion object {
        const val baseUrl: String = BuildConfig.baseUrl
        const val MY_RAIN_SHARED_PREFERENCES: String= BuildConfig.prefName
        const val  PREF_PASSWORD: String = BuildConfig.prefPassword
    }


}
