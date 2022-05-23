package com.example.softwareproject

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.softwareproject.databinding.ActivityCameraBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.File

class CameraActivity : AppCompatActivity() {

    lateinit var binding: ActivityCameraBinding

    private var tempImageUri: Uri? = null
    private var tempImageFilePath = ""
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){ success ->

        if (success){
            binding.cameraIv.setImageURI(tempImageUri)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPermission()

        binding.cameraShootTv.setOnClickListener {

            tempImageUri = FileProvider.getUriForFile(this,
                "com.example.softwareproject.provider", createImageFile().also {
                    tempImageFilePath = it.absolutePath
            })
            cameraLauncher.launch(tempImageUri)
        }
    }

    private fun setPermission() {
      val permission = object : PermissionListener {
          override fun onPermissionGranted() {
              Toast.makeText(this@CameraActivity, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
          }

          override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
              Toast.makeText(this@CameraActivity, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
          }
      }

      TedPermission.with(this)
          .setPermissionListener(permission)
          .setRationaleMessage("카메라를 사용하시려면 권한을 허용해주세요.")
          .setDeniedMessage("권한을 거부하였습니다. [앱 설정] -> [권한] 항목에서 허용해주새요.")
          .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
          .check()
    }

    private fun createImageFile(): File {

        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)

    }
}