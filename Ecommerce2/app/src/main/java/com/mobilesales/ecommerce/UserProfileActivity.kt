package com.mobilesales.ecommerce

import android.app.DownloadManager
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import androidx.room.Update
import com.google.android.material.textfield.TextInputEditText
import com.mobilesales.ecommerce.model.UserAddress
import com.mobilesales.ecommerce.model.UserWithAddress
import com.mobilesales.ecommerce.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_register.*
import org.w3c.dom.Text
import java.io.File
import java.io.IOException
import java.net.URI
import java.nio.file.Files
import java.util.*


class UserProfileActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var imageProfile: ImageView
    lateinit var photoURI: Uri
    lateinit var userProfileName: TextInputEditText
    lateinit var userProfileSurname : TextInputEditText
    lateinit var userProfileEmail : TextInputEditText
    lateinit var userAddress1 : TextInputEditText
    lateinit var userAddress2 : TextInputEditText
    lateinit var userAddressNumber : TextInputEditText
    lateinit var userAddressCity : TextInputEditText
    lateinit var userAddressCep : TextInputEditText
    lateinit var userAddressState : Spinner
    lateinit var userWithAddress: UserWithAddress
    lateinit var btnUserUpdate : Button
    private val userViewModel by viewModels <UserViewModel>()

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

        userProfileName = findViewById(R.id.txt_edit_name)
        userProfileSurname = findViewById(R.id.txt_edit_surname)
        userProfileEmail = findViewById(R.id.txt_edit_email)
        userAddress1 = findViewById(R.id.txt_edit_adress)
        userAddress2 = findViewById(R.id.txt_edit_adress2)
        userAddressNumber = findViewById(R.id.txt_edit_number)
        userAddressCity = findViewById(R.id.txt_edit_city)
        userAddressCep = findViewById(R.id.txt_edit_cep)
        userAddressState = findViewById(R.id.sp_state)
        btnUserUpdate = findViewById(R.id.btn_user_att)
        btnUserUpdate.setOnClickListener {update()}

        imageProfile = findViewById(R.id.iv_profile_image)
        imageProfile.setOnClickListener { takePicture() }


        val profileImage = PreferenceManager.getDefaultSharedPreferences(this)
            .getString(MediaStore.EXTRA_OUTPUT, null)

        if (profileImage != null) {
            photoURI = Uri.parse(profileImage)
            imageProfile.setImageURI(photoURI)
        } else {
            photoURI = Uri.parse("/")
            imageProfile.setImageResource(R.drawable.profile_image)
        }

        userViewModel.isLogged().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                userWithAddress = it
                userProfileName.setText(it.user.name)
                userProfileSurname.setText(it.user.surname)
                userProfileEmail.setText(it.user.email)
                if (it.addresses.isNotEmpty()) {
                    it.addresses.first().let { address ->
                        userAddress1.setText(address.addressLine1)
                        userAddress2.setText(address.addressLine2)
                        userAddressNumber.setText(address.number)
                        userAddressCity.setText(address.city)
                        userAddressCep.setText(address.zipCode)
                        resources.getStringArray(R.array.states).asList().indexOf(address.state)
                            .let {
                                userAddressState.setSelection(it)
                            }
                    }
                }
            } else {
                startActivity(Intent(this, UserLoginActivity::class.java))
                finish()
            }
        })

    }

    fun update(){

        if (!validate())
            return

        userWithAddress.apply {
            user.name = userProfileName.text.toString()
            user.surname = userProfileSurname.text.toString()
            user.email = userProfileEmail.text.toString()
            user.image = photoURI.toString()
            userViewModel.updateUser(user)

            if (addresses.isEmpty()){
                val userAddress = UserAddress(
                    addressLine1 = userAddress1.text.toString(),
                    addressLine2 = userAddress2.text.toString(),
                    number = userAddressNumber.text.toString(),
                    city = userAddressCity.text.toString(),
                    zipCode = userAddressCep.text.toString(),
                    state = resources.getStringArray(R.array.states)[userAddressState.selectedItemPosition],
                    userId = user.id)
                userViewModel.createAdress(userAddress)
            }else{
                addresses.first().apply {
                    addressLine1 = userAddress1.text.toString()
                    addressLine2 = userAddress2.text.toString()
                    number = userAddressNumber.text.toString()
                    city = userAddressCity.text.toString()
                    zipCode = userAddressCep.text.toString()
                    state = resources.getStringArray(R.array.states)[userAddressState.selectedItemPosition]
                }
                userViewModel.updateAdress(addresses.first())
            }
        }
        Toast.makeText(this, "Dados atualizados com sucesso", Toast.LENGTH_SHORT).show()
    }

  private fun validate() : Boolean{
        var isValid = true

        userProfileName.apply {
            if (text.isNullOrEmpty()){
                error = "Preencha o campo nome."
                isValid = false
            }else{
                error = null
            }
        }
        userProfileSurname.apply {
            if (text.isNullOrEmpty()){
                error = "Preencha o campo sobrenome."
                isValid = false
            }else{
                error = null
            }
        }
        userProfileEmail.apply {
            if (text.isNullOrEmpty()){
                error = "Preencha o campo email."
                isValid = false
            }else{
                error = null
            }
        }
        userAddress1.apply {
            if (text.isNullOrEmpty()){
                error = "Preencha o campo rua/avenida."
                isValid = false
            }else{
                error = null
            }
        }
        userAddressNumber.apply {
            if (text.isNullOrEmpty()){
                error = "Preencha o campo numero."
                isValid = false
            }else{
                error = null
            }
        }
        userAddressCity.apply {
            if (text.isNullOrEmpty()){
                error = "Preencha o campo cidade."
                isValid = false
            }else{
                error = null
            }
        }
        userAddressCep.apply {
            if (text.isNullOrEmpty()){
                error = "Preencha o campo cep."
                isValid = false
            }else{
                error = null
            }
        }
        return  isValid
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {

        val timesTamp: String =
            java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "PROFILE_${timesTamp}",
            ".jpg",
            storageDir
        )
    }

    private fun takePicture() {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {

                val photoFile: File? = try {

                    createImageFile()

                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        this, "com.mobilesales.ecommerce.fileprovider", it
                    )

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