package com.example.projectbasesetup.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.projectbasesetup.network.NetworkStatus
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun intercept(chain: Interceptor.Chain): Response {
        when (getCurrentNetworkStatus()) {
            NetworkStatus.Disconnected -> {
                Log.d("NETWORK", "Network status: Disconnected")
                throw NoConnectivityException()
            }
            else -> {
                Log.d("NETWORK", "Network status: Connected")
                val builder = chain.request().newBuilder()
                return chain.proceed(builder.build())
            }
        }
    }

    private fun getCurrentNetworkStatus(): NetworkStatus {
        return try {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).let { connected ->
                    when (connected) {
                        true -> NetworkStatus.Connected
                        else -> NetworkStatus.Disconnected
                    }
                }
        } catch (e: Exception) {
            Log.d("NETWORK", "exception thrown: ${e.localizedMessage}")
            return NetworkStatus.Disconnected
        }
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No internet connection"

    override fun getLocalizedMessage(): String? {
        return "No internet connection"
    }
}
