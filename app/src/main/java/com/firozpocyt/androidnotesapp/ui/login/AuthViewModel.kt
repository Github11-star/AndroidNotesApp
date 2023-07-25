package com.firozpocyt.androidnotesapp.ui.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firozpocyt.androidnotesapp.models.UserRequest
import com.firozpocyt.androidnotesapp.models.UserResponse
import com.firozpocyt.androidnotesapp.repository.UserRepository
import com.firozpocyt.androidnotesapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userResponseLiveData

    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.registerUser(userRequest)
        }
    }

    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }

    fun validateCredentials(username : String, emailAddress : String, password : String, isLogin: Boolean) : Pair<Boolean, String>{
        var result = Pair(true, "")
        if (!isLogin && TextUtils.isEmpty(username) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)){
            result = Pair(false, "Please provides the credentials")
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            result = Pair(false, "Please provides valid email")
        }
        else if (password.length <=5 ){
            result = Pair(false, "Password should be greater than five ")
        }
        return result
    }
}