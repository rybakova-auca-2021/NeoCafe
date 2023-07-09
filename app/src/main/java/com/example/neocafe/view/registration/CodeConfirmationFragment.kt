package com.example.neocafe.view.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.Utils.TimerUtil
import com.example.neocafe.databinding.FragmentCodeConfirmationBinding

class CodeConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentCodeConfirmationBinding
    private lateinit var timerUtil: TimerUtil
    private lateinit var button: Button

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
            findNavController().navigate(R.id.action_codeConfirmationFragment_to_addBirthdayFragment)
        }
    }
}