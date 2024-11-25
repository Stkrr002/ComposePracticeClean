package com.alpharays.composeclean.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpharays.composeclean.domain.UserRepository
import com.alpharays.composeclean.domain.model.Student
import com.alpharays.composeclean.domain.model.StudentListResponse
import com.alpharays.composeclean.utils.APIResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userListFlow =
        MutableStateFlow<APIResponse<StudentListResponse?>>(APIResponse.Empty())
    val userListFlow: StateFlow<APIResponse<StudentListResponse?>> = _userListFlow

    private val userStaticList = mutableListOf<String>()
    private var counter = 1

    fun getUserList() {
        viewModelScope.launch {
            userRepository.getUserList().collect {
                _userListFlow.value = it
            }
        }
    }

    fun addItem() {
        viewModelScope.launch(Dispatchers.IO) {
            counter++

            userRepository.addUserToList(
                Student(
                    name = "Sumit $counter", roll_number = counter
                )
            ).collect {
                _userListFlow.value = it
            }
        }

    }
}