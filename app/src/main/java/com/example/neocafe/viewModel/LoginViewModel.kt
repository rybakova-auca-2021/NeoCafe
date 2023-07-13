package com.example.neocafe.viewModel

import androidx.lifecycle.ViewModel
import com.example.neocafe.api.RetrofitInstance
import com.example.neocafe.model.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    fun login(
        phone_number: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val request = LoginRequest(phone_number)
        val apiInterface = RetrofitInstance.api

        val call = apiInterface.login(request)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                onError.invoke()
            }
        })
    }
}