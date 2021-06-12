package com.example.mvphomework.lesson4.ui

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.example.mvphomework.R
import com.example.mvphomework.databinding.ActivityMainHomework4Binding
import com.example.mvphomework.toast
import com.squareup.picasso.Picasso
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

@Suppress("DEPRECATION")
class MainActivity : MvpAppCompatActivity(), MainView, EasyPermissions.PermissionCallbacks {

    companion object {
        const val IMAGE_REQUEST_CODE = 1
        const val PERMISSION_WRITE_REQUEST_CODE = 2
    }

    private val mainPresenter by moxyPresenter {
        MainPresenter(BitmapConverter(context = this))
    }

    private lateinit var binding: ActivityMainHomework4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainHomework4Binding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.btnConvert.setOnClickListener {
            if (hasPermissions())
                mainPresenter.onConvertClicked()
            else requestPermissions()
        }

        binding.btnPick.setOnClickListener {
            mainPresenter.onPickClicked()
        }

        binding.dispose.setOnClickListener {
            mainPresenter.cancel()
        }
    }

    override fun loadImage() {
        Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }.also {
            startActivityForResult(
                Intent.createChooser(it, "Select Picture"),
                IMAGE_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK)
            if (requestCode == IMAGE_REQUEST_CODE) {
                mainPresenter.onImageReceived(data?.data.toString())
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )
    }

    override fun showError() = toast(getString(R.string.try_again_later))

    override fun showSuccess() = toast(getString(R.string.converted))

    override fun showCancel() = toast(getString(R.string.canceled))

    override fun setImage(imageUri: Uri) = Picasso.get()
        .load(imageUri)
        .noPlaceholder()
        .centerCrop()
        .fit()
        .into(binding.ivPicture)

    override fun saveImage(bitmap: Bitmap) {
        MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap,
            "New Image",
            "Description"
        )
    }

    override fun showLoading() = binding.progressBar.show()

    override fun hideLoading() = binding.progressBar.hide()

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms.first())) {
            SettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) =
        toast(getString(R.string.permissions_granted))

    private fun hasPermissions() = EasyPermissions.hasPermissions(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private fun requestPermissions() {
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.permissions_denied),
            PERMISSION_WRITE_REQUEST_CODE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}