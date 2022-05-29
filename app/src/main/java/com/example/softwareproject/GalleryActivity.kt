package com.example.softwareproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityGalleryBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class GalleryActivity : AppCompatActivity() {

    lateinit var binding: ActivityGalleryBinding

    private val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.galleryIv.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPermission()

        binding.galleryShootTv.setOnClickListener {
            selectPictureLauncher.launch("image/")
        }


    }

    private fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() {
                Toast.makeText(this@GalleryActivity, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@GalleryActivity, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라를 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하였습니다. [앱 설정] -> [권한] 항목에서 허용해주새요.")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()
    }
}