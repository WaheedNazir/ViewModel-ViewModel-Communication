package waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by Waheed on 26,November,2019
 */

object ToastUtil {

    fun showToast(context: Context?, message: String?) {
        if (context == null || message == null) return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}