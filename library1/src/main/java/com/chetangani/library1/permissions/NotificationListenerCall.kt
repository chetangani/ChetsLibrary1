package com.chetangani.library1.permissions

import android.content.ComponentName
import android.content.Context
import android.content.DialogInterface
import android.provider.Settings
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.chetangani.library1.constants.ConstantValues.ENABLED_NOTIFICATION_LISTENERS
import com.chetangani.library1.constants.ConstantValues.FALSE
import com.chetangani.library1.constants.ConstantValues.NO
import com.chetangani.library1.constants.ConstantValues.NOTIFICATION_ALERT_MSG_1
import com.chetangani.library1.constants.ConstantValues.NOTIFICATION_ALERT_MSG_2
import com.chetangani.library1.constants.ConstantValues.NOTIFICATION_ALERT_TITLE
import com.chetangani.library1.constants.ConstantValues.YES
import com.chetangani.library1.interfaces.PermissionReceiver

class NotificationListenerCall(private val context: Context,
                               private val permissionReceiver: PermissionReceiver
) {

    val isNotificationServiceEnabled: Boolean
        get() {
            val pkgName = context.packageName
            val flat = Settings.Secure.getString(context.contentResolver, ENABLED_NOTIFICATION_LISTENERS)
            if (!TextUtils.isEmpty(flat)) {
                val names = flat.split(":".toRegex()).toTypedArray()
                for (name in names) {
                    val cn = ComponentName.unflattenFromString(name)
                    if (cn != null) if (TextUtils.equals(pkgName, cn.packageName)) return true
                }
            }
            return false
        }

    fun buildNotificationServiceAlertDialog(appName: String) = context.let {
        AlertDialog.Builder(it)
            .setTitle(NOTIFICATION_ALERT_TITLE)
            .setCancelable(FALSE)
            .setMessage(String.format("%s %s %s", NOTIFICATION_ALERT_MSG_1, appName, NOTIFICATION_ALERT_MSG_2))
            .setPositiveButton(YES, DialogInterface.OnClickListener { dialogInterface, i ->
                permissionReceiver.openNotificationPermission() })
            .setNegativeButton(NO, DialogInterface.OnClickListener { dialogInterface, i ->  })
            .create()
    }
}