package com.mobilesales.ecommerce.repository

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.FirebaseFirestore
import com.mobilesales.ecommerce.database.AppDataBase
import com.mobilesales.ecommerce.model.User
import com.mobilesales.ecommerce.model.UserAddress
import com.mobilesales.ecommerce.viewModel.UserViewModel
import org.json.JSONObject

class UsersRepository (application: Application) {

    private val preference = PreferenceManager.getDefaultSharedPreferences(application)
    private val userDao = AppDataBase.getDataBase(application).userDao()
    private val fireStore = FirebaseFirestore.getInstance()
    private val queue = Volley.newRequestQueue(application)

    fun login(email : String, password : String) : LiveData<User>{

        val liveData = MutableLiveData<User>()
        val params = JSONObject().also {
            it.put("email", email)
            it.put("password", password)
            it.put("returnSecureToken", true)
        }
        val request = JsonObjectRequest(Request.Method.POST
            ,BASE_URL + SIGNIN + KEY
            ,params
            , { response ->
                val localId = response.getString("localId")
               val idToken = response.getString("idToken")

                fireStore.collection("users")
                    .document(localId).get().addOnSuccessListener{
                        val user = it.toObject(User::class.java)
                        user?.id = localId
                        user?.password = idToken
                        liveData.value = user!!

                        preference.edit().putString(UserViewModel.USER_ID, localId).apply()
                        fireStore.collection("users").document(localId).set(user)
                    }
            }
            , { error ->
                Log.e(this.toString(),error.message ?: "Error")
            }
        )
        queue.add(request)
        return  liveData
    }

    fun createUser (user : User){

        val params = JSONObject().also {
            it.put("email", user.email)
            it.put("password", user.password)
            it.put("returnSecureToken", true)
        }

        val request = JsonObjectRequest(Request.Method.POST
        ,BASE_URL + SIGNUP + KEY
        ,params
        , { response ->
                user.id = response.getString("localId")
                user.password = response.getString("idToken")

                fireStore.collection("users")
                    .document(user.id).set(user).addOnSuccessListener {
                        Log.d(this.toString(),"Usuário ${user.email} cadastrado com sucesso.")
                    }
            }
        , { error ->
                Log.e(this.toString(),error.message ?: "Error")
            }
        )

        queue.add(request)
    }

    fun loadWithAdresses(userId : String) = userDao.loadUserById(userId)

    fun insert(user : User) = userDao.insert(user)

    fun insert(userAddress : UserAddress) = userDao.insert(userAddress)

    fun updateUser(user : User) = userDao.update(user)

    fun updateUser(userAddress : UserAddress) = userDao.update(userAddress)

    companion object{
        const val BASE_URL =  "https://identitytoolkit.googleapis.com/v1/"
        const val SIGNUP = "accounts:signUp"
        const val SIGNIN = "accounts:signInWithPassword"
        const val PASSWORD_RESET = "accounts:sendOobCode"
        const val KEY = "?key=AIzaSyDWdaQRdhYtq0fG8zvGLhnIDq-B9koFcDk"
    }
}