package com.example.neocafe.view.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentAddBirthdayBinding

class AddBirthdayFragment : Fragment() {
    private lateinit var binding: FragmentAddBirthdayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddBirthdayBinding.inflate(inflater, container, false)
        return binding.root
    }
}