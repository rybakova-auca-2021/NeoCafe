package com.example.neocafe.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.util.TimerUtil
import com.example.neocafe.databinding.FragmentCodeConfirmationBinding
import com.example.neocafe.viewModel.LoginCodeViewModel

class LoginCodeConfirmFragment : Fragment() {
    private lateinit var binding: FragmentCodeConfirmationBinding
    private lateinit var timerUtil: TimerUtil
    private lateinit var button: Button
    private val viewModel: LoginCodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCodeConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        button = binding.buttonSendAgain
        timerUtil = TimerUtil(requireContext(), button, findNavController())
        timerUtil.startTimer(30000, 1000)
        changeButtonColor()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timerUtil.cancelTimer()
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_loginCodeConfirmFragment_to_loginFragment)
        }
        binding.buttonConfirm.setOnClickListener {
            setupCodeConfirm()
        }
    }

    private fun setupCodeConfirm() {
        val code = binding.etCode.text.toString()
        viewModel.loginCode(
            code.toInt(),
            onSuccess = {
                Toast.makeText(requireContext(), "Вход выполнен успешно", Toast.LENGTH_SHORT).show()
            },
            onError = {
                Toast.makeText(requireContext(), "Try again", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun changeButtonColor() {
        binding.etCode.addTextChangedListener { text ->
            val code = text?.toString() ?: ""
            binding.buttonConfirm.isEnabled = code.isNotEmpty()
        }
    }
}