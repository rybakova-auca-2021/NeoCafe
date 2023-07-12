package com.example.neocafe.viewModel

import androidx.lifecycle.ViewModel
import com.example.neocafe.api.RetrofitInstance
import com.example.neocafe.model.RegistrationRequest
import com.example.neocafe.model.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel : ViewModel() {
    fun register(
        username: String,
        phone_number: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val request = RegistrationRequest(username, phone_number)
        val apiInterface = RetrofitInstance.api

        val call = apiInterface.register(request)

        call.enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                onError.invoke()
            }
        })
    }
}