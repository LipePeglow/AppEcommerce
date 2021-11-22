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

    fun createUser (user: User) = userRepository.createUser(user)

    fun update (userWithAddress: UserWithAddress) = userRepository.update(userWithAddress)

    fun login (email : String, password : String) : LiveData<User> = userRepository.login(email, password)

    fun logout() = PreferenceManager.getDefaultSharedPreferences(getApplication()).let{
        it.edit().remove(USER_ID).apply()

    }

    fun isLogged():LiveData<UserWithAddress> = PreferenceManager.getDefaultSharedPreferences(getApplication()).let {

        val id = it.getString(USER_ID,  null)
        if (id.isNullOrEmpty())
            return MutableLiveData(null)

        return userRepository.load(id)
    }

    fun resetPassword(email : String) = userRepository.resetPassword(email)

    companion object{

        val USER_ID = "USER_ID"
    }
}