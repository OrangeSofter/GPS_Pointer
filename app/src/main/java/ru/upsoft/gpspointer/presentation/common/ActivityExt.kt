package ru.upsoft.gpspointer.presentation.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.upsoft.gpspointer.R

fun Activity.showAlertMessageWithoutNegativeButton(
    title: String,
    message: String,
    cancelable: Boolean = false,
    action: () -> Unit = {}
): AlertDialog =
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(cancelable)
        .setPositiveButton(this.getString(R.string.ok)) { _, _ -> action() }
        .show()

fun Activity.showErrorPermissionMessage(title: String, message: String): AlertDialog =
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setNegativeButton(this.getString(R.string.cancel)) { dialog, _ -> dialog.cancel() }
        .setPositiveButton(this.getString(R.string.settings)) { _, _ -> goToSettings() }
        .show()

fun Activity.goToSettings() {
    val appSettingsIntent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:$packageName")
    )
    startActivityForResult(appSettingsIntent, 1)
}