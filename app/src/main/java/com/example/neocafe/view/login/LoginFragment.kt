package com.example.neocafe.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentLoginBinding
import com.example.neocafe.viewModel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        changeButtonColor()
    }

    private fun setupNavigation() {
        binding.buttonNext.setOnClickListener {
            setupLogin()
        }
    }

    private fun setupLogin() {
        val phoneNumber = binding.phone.text.toString()
        loginViewModel.login(
            phoneNumber,
        onSuccess = {
            findNavController().navigate(R.id.action_loginFragment_to_loginCodeConfirmFragment)
        },
        onError = {
            Toast.makeText(requireContext(),"Try again", Toast.LENGTH_SHORT).show()
        })
    }

    private fun changeButtonColor() {
        binding.phone.addTextChangedListener { text ->
            val phone = text?.toString() ?: ""
            binding.buttonNext.isEnabled = phone.isNotEmpty()
        }
    }
}