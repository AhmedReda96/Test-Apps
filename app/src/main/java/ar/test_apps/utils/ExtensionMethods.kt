package ar.test_apps.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ar.test_apps.R
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import java.util.*


fun Context.showLog(methodName: String, value: String?) {
    Log.d(this::class.java.simpleName, "TagAR $methodName: $value ")
}

fun imageRequestOptions(): RequestOptions {
    return RequestOptions()
        .centerCrop()
        .placeholder(android.R.color.darker_gray)
        .error(android.R.color.darker_gray)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
}

fun <T> Activity.makeActivitiesNavigate(
    to: Class<T>,
    destroyScreen: Boolean,
    extras: Bundle.() -> Unit = {}
) {
    val intent = Intent(this, to)
    intent.putExtras(Bundle().apply(extras))
    if (destroyScreen) {
        startActivity(intent)
        this.finish()
    } else {
        startActivity(intent)
    }
}

fun Fragment.makeFragmentNavigate(action: Int, vararg args: Pair<String, Any?>) {
    val bundle = bundleOf(*args)
    val navOptions = NavOptions.Builder()
        .setEnterAnim(androidx.appcompat.R.anim.abc_slide_in_top)
        .setExitAnim(androidx.appcompat.R.anim.abc_slide_out_top)
        .setPopEnterAnim(androidx.appcompat.R.anim.abc_slide_in_top)
        .setPopExitAnim(androidx.appcompat.R.anim.abc_slide_out_top)
        .build()

    this.findNavController().navigate(
        action, bundle, navOptions
    )
}


@RequiresApi(Build.VERSION_CODES.M)
fun Activity.checkNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

