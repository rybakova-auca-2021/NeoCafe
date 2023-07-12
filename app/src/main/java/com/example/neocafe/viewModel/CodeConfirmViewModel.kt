package com.example.neocafe.viewModel

import androidx.lifecycle.ViewModel
import com.example.neocafe.api.RetrofitInstance
import com.example.neocafe.model.ConfirmRequest
import com.example.neocafe.model.ConfirmResponse
import com.example.neocafe.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodeConfirmViewModel : ViewModel() {
    fun confirmCode(
        code: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val request = ConfirmRequest(code)
        val apiInterface = RetrofitInstance.api

        val call = apiInterface.verify(request)

        call.enqueue(object : Callback<ConfirmResponse> {
            override fun onResponse(call: Call<ConfirmResponse>, response: Response<ConfirmResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val accessToken = loginResponse?.access
                    if (accessToken != null) {
                        Utils.access_token = accessToken
                    }
                    onSuccess.invoke()
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<ConfirmResponse>, t: Throwable) {
                onError.invoke()
            }
        })
    }
}