package com.example.neocafe.view.registration

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentAddBirthdayBinding
import java.text.SimpleDateFormat
import java.util.*

class AddBirthdayFragment : Fragment() {
    private lateinit var binding: FragmentAddBirthdayBinding

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
        binding.skip.setOnClickListener {
            // TODO
        }
        binding.buttonLogin.setOnClickListener {
            showArchiveConfirmationDialog()
            // TODO
        }
    }

    private fun changeButtonColor() {
        binding.etBirthday.addTextChangedListener { text ->
            val birthday = text?.toString() ?: ""
            binding.buttonLogin.isEnabled = birthday.isNotEmpty()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(Calendar.YEAR, year)
                selectedCalendar.set(Calendar.MONTH, month)
                selectedCalendar.set(Calendar.DAY_OF_MONTH, day)

                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedCalendar.time)

                binding.etBirthday.setText(formattedDate)
            },
            currentYear,
            currentMonth,
            currentDay
        )

        datePickerDialog.show()
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