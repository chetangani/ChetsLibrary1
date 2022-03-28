package com.chetangani.library1.interfaces

interface PermissionReceiver {
    fun permissionGranted(result: Boolean)
    fun storagePermissionGranted(result: Boolean)
    fun openNotificationPermission()
    fun notificationCallResult()
    fun storageCallResult()
}