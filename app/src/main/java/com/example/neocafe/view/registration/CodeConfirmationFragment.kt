package com.example.neocafe.view.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentCodeConfirmationBinding
import com.example.neocafe.util.TimerUtil
import com.example.neocafe.viewModel.CodeConfirmViewModel

class CodeConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentCodeConfirmationBinding
    private lateinit var timerUtil: TimerUtil
    private lateinit var button: Button
    private val viewModel: CodeConfirmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodeConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        button = binding.buttonSendAgain
        timerUtil = TimerUtil(requireContext(), button, findNavController())
        timerUtil.startTimer(60000, 1000)
        changeButtonColor()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timerUtil.cancelTimer()
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_codeConfirmationFragment_to_registrationFragment)
        }
        binding.buttonConfirm.setOnClickListener {
            setupCodeConfirm()
        }
    }

    private fun setupCodeConfirm() {
        val code = binding.etCode.text.toString()
        viewModel.confirmCode(
            code.toInt(),
            onSuccess = {
                findNavController().navigate(R.id.action_codeConfirmationFragment_to_addBirthdayFragment)
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