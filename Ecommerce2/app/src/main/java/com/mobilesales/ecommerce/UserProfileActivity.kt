package com.mobilesales.ecommerce

import android.app.DownloadManager
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.preference.PreferenceManager
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.*


class UserProfileActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var imageProfile : ImageView
    lateinit var photoURI : Uri

    val REQUEST_TAKE_PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.Perfil)

        imageProfile = findViewById(R.id.iv_profile_image)
        imageProfile.setOnClickListener{takePicture() }

        val profileImage = PreferenceManager.getDefaultSharedPreferences(this).getString(MediaStore.EXTRA_OUTPUT, null)

        if (profileImage != null){
            photoURI = Uri.parse(profileImage)
            imageProfile.setImageURI(photoURI)
        }else{
            imageProfile.setImageResource(R.drawable.profile_image)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @Throws(IOException::class)
    private fun createImageFile(): File{

        val timesTamp:String = java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "PROFILE_${timesTamp}",
            ".jpg",
            storageDir
        )
    }

    private fun takePicture(){

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {

                val photoFile: File? = try {

                    createImageFile()

                }catch (ex: IOException){
                    null
                }

                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        this, "com.mobilesales.ecommerce.fileprovider", it)

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(intent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        PreferenceManager.getDefaultSharedPreferences(this).apply {
            edit().putString(MediaStore.EXTRA_OUTPUT, photoURI.toString()).apply()
        }

        imageProfile.setImageURI(photoURI)
    }
}