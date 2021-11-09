package com.mobilesales.ecommerce.viewModel

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.mobilesales.ecommerce.model.User
import com.mobilesales.ecommerce.model.UserAddress
import com.mobilesales.ecommerce.model.UserWithAddress
import com.mobilesales.ecommerce.repository.UsersRepository

class UserViewModel (application: Application) : AndroidViewModel(application) {

    private val userRepository = UsersRepository(getApplication())

    fun createUser (user: User) = userRepository.insert(user)

    fun createAdress (userAdress: UserAddress) = userRepository.insert(userAdress)

    fun updateUser (user: User) = userRepository.updateUser(user)

    fun updateAdress (userAddress : UserAddress) = userRepository.updateUser(userAddress)

    fun login (email : String, password : String) : MutableLiveData<User> {
        return MutableLiveData(
            userRepository.login(email, password).also { user ->
                PreferenceManager.getDefaultSharedPreferences(getApplication()).let {

                    if (user != null)
                         it.edit().putString(USER_ID, user.id).apply()
                }
            }
        )
    }
    fun logout() = PreferenceManager.getDefaultSharedPreferences(getApplication()).let{
        it.edit().remove(USER_ID).apply()

    }

    fun isLogged():LiveData<UserWithAddress> = PreferenceManager.getDefaultSharedPreferences(getApplication()).let {
        return userRepository.loadWithAdresses(it.getString(USER_ID, "")!!)
    }

    companion object{

        val USER_ID = "USER_ID"
    }
}