package pl.mbui.okeapp.ui.util

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.mbui.okeapp.R

fun showDialog(context: Context, @StringRes msg : Int) {
        MaterialAlertDialogBuilder(context)
            .setCancelable(false)
            .setMessage(msg)
            .setNegativeButton(R.string.close) { dialog, _ -> dialog.dismiss() }
            .show()
}