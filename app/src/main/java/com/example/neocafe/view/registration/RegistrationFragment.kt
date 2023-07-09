package com.example.neocafe.view.registration

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding

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
            findNavController().navigate(R.id.action_registrationFragment_to_codeConfirmationFragment)
        }
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