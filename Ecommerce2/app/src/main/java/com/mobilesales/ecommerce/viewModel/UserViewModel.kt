package com.mobilesales.ecommerce.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilesales.ecommerce.model.User
import com.mobilesales.ecommerce.repository.UsersRepository

class UserViewModel (application: Application) : AndroidViewModel(application) {

    private val userRepository = UsersRepository(getApplication())

    fun createUser (user: User) = userRepository.insert(user)
}