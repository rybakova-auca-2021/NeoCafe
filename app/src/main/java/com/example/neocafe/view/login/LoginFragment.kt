package com.example.neocafe.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

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
            findNavController().navigate(R.id.action_loginFragment_to_loginCodeConfirmFragment)
        }
    }

    private fun changeButtonColor() {
        binding.phone.addTextChangedListener { text ->
            val phone = text?.toString() ?: ""
            binding.buttonNext.isEnabled = phone.isNotEmpty()
        }
    }
}