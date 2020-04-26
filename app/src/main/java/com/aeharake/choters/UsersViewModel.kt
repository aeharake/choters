package com.aeharake.choters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aeharake.choters.repos.UserRepository
import com.aeharake.choters.room.entities.User

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    var usersRepository: UserRepository = UserRepository(application)
    val users: LiveData<List<User>> = usersRepository.getAllUsers()

    fun addUser(user: User) {
        usersRepository.insertUsers(user)
    }

    fun populate(){
        usersRepository.populate()
    }
}