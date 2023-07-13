package com.example.neocafe.viewModel

import androidx.lifecycle.ViewModel
import com.example.neocafe.api.RetrofitInstance
import com.example.neocafe.model.ConfirmRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCodeViewModel : ViewModel() {
    fun loginCode(
        code: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val apiInterface = RetrofitInstance.api
        val call = apiInterface.loginCode(ConfirmRequest(code))

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