package github.aeharake.choters.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import github.aeharake.choters.repos.UserRepository
import github.aeharake.choters.room.entities.User
import github.aeharake.choters.room.entities.UserMessage

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private var usersRepository: UserRepository =
        UserRepository(application)
    val users: LiveData<List<UserMessage>> = usersRepository.getAllUsers()

    fun populate(){
        usersRepository.populate()
    }
}