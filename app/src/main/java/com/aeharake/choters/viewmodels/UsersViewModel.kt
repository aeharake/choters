package com.aeharake.choters.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aeharake.choters.repos.UserRepository
import com.aeharake.choters.room.entities.User
import com.aeharake.choters.room.entities.UserMessage

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private var usersRepository: UserRepository = UserRepository(application)
    val users: LiveData<List<UserMessage>> = usersRepository.getAllUsers()

    fun populate(){
        usersRepository.populate()
    }
}