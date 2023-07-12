package com.example.neocafe.viewModel

import androidx.lifecycle.ViewModel
import com.example.neocafe.api.RetrofitInstance
import com.example.neocafe.model.BirthdayRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BirthdayViewModel : ViewModel() {
    fun addBirthday(
        birthday: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val apiInterface = RetrofitInstance.api
        val request = BirthdayRequest(birthday)
        val call = apiInterface.addBirthday(request)

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