package com.example.neocafe.view.registration

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
import com.example.neocafe.databinding.FragmentRegistrationBinding
import com.example.neocafe.viewModel.RegistrationViewModel

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val registerViewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        changeButtonColor()
    }

    private fun setupNavigation() {
        binding.buttonNext.setOnClickListener {
            setupRegistration()
        }
    }

    private fun setupRegistration() {
        val username = binding.etName.text.toString()
        val phoneNumber = binding.phone.text.toString()
        registerViewModel.register(
            username,
            phoneNumber,
            onSuccess = {
                findNavController().navigate(R.id.action_registrationFragment_to_codeConfirmationFragment)
            },
            onError = {
                Toast.makeText(requireContext(), getString(R.string.try_again_msg), Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun changeButtonColor() {
        binding.etName.addTextChangedListener { text ->
            val name = text?.toString() ?: ""
            val phone = binding.phone.text.toString()
            binding.buttonNext.isEnabled = name.isNotEmpty() && phone.isNotEmpty()
        }
        binding.phone.addTextChangedListener { text ->
            val phone = text?.toString() ?: ""
            val name = binding.etName.text.toString()
            binding.buttonNext.isEnabled = name.isNotEmpty() && phone.isNotEmpty()
        }
    }
}