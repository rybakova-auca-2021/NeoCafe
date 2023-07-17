package com.example.neocafe.view.registration

import android.app.AlertDialog
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
import com.example.neocafe.databinding.FragmentAddBirthdayBinding
import com.example.neocafe.util.DatePickerUtil
import com.example.neocafe.viewModel.BirthdayViewModel

class AddBirthdayFragment : Fragment() {
    private lateinit var binding: FragmentAddBirthdayBinding
    private val viewModel: BirthdayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBirthdayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        showPicker()
        changeButtonColor()
    }

    private fun showPicker() {
        binding.etBirthday.setOnClickListener {
            showDatePicker()
        }
    }
    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_addBirthdayFragment_to_codeConfirmationFragment)
        }
        binding.buttonLogin.setOnClickListener {
            setupBirthday()
        }
    }

    private fun setupBirthday() {
        val birthday = binding.etBirthday.text.toString()
        viewModel.addBirthday(
            birthday,
            onSuccess = {
                showArchiveConfirmationDialog()
                findNavController().navigate(R.id.action_addBirthdayFragment_to_loginFragment)
            },
            onError = {
                Toast.makeText(requireContext(), getString(R.string.try_again_msg), Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun changeButtonColor() {
        binding.etBirthday.addTextChangedListener { text ->
            val birthday = text?.toString() ?: ""
            binding.buttonLogin.isEnabled = birthday.isNotEmpty()
        }
    }

    private fun showDatePicker() {
        binding.etBirthday.setOnClickListener {
            DatePickerUtil.showDatePicker(requireContext(), binding.etBirthday)
        }
    }

    private fun showArchiveConfirmationDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_finish_register, null)
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.LightDialogTheme)
            .setView(dialogView)
        val dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        dialog.window?.decorView?.rootView?.setOnClickListener {
            dialog.dismiss()
        }
    }
}