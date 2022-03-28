package com.chetangani.library1.interfaces

interface PermissionCallInterface {
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
}