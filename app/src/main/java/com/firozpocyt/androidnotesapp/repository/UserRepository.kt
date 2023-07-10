package com.firozpocyt.androidnotesapp.repository

import android.util.Log
import com.firozpocyt.androidnotesapp.api.UserAPI
import com.firozpocyt.androidnotesapp.models.UserRequest
import com.firozpocyt.androidnotesapp.utils.Constants.TAG
import javax.inject.Inject

class UserRepository @Inject constructor(private var userAPI: UserAPI) {

    suspend fun registerUser(userRequest: UserRequest){
        val response = userAPI.signup(userRequest)
        Log.d(TAG, response.body().toString())
    }

    suspend fun loginUser(userRequest: UserRequest){
        val response = userAPI.signin(userRequest)
        Log.d(TAG, response.body().toString())
    }
}