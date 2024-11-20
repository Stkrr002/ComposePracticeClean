package com.alpharays.composeclean.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpharays.composeclean.domain.UserRepository
import com.alpharays.composeclean.utils.APIResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userListFlow = MutableStateFlow<APIResponse<List<String>>>(APIResponse.Empty())
    val userListFlow: StateFlow<APIResponse<List<String>>> = _userListFlow

    fun getUserList() {
        viewModelScope.launch {
            userRepository.getUserList().collect {
                _userListFlow.value = it
            }
        }
    }

}