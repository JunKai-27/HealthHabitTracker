package com.example.healthhabittracker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.healthhabittracker.databinding.FragmentAddEditBinding
import java.util.Calendar

class AddEditFragment : Fragment() {

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Populate categories
        val categories = listOf("Meal", "Exercise", "Sleep", "Mood", "Other")
        binding.spinnerCategory.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )

        // FIX: Make DateTime field clickable
        binding.etDateTime.setOnClickListener {
            showDateTimePicker()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSave.setOnClickListener {
            if (validate()) {
                Toast.makeText(requireContext(), "Saved (placeholder)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Date + Time Picker combined
    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()

        // First show DatePicker
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                // After date chosen, show TimePicker
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        val formatted = String.format(
                            "%02d/%02d/%04d %02d:%02d",
                            day, month + 1, year, hour, minute
                        )
                        binding.etDateTime.setText(formatted)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // Validation logic
    private fun validate(): Boolean {
        var ok = true

        // Name required
        if (TextUtils.isEmpty(binding.etName.text)) {
            binding.tilName.error = "Name is required"
            ok = false
        } else binding.tilName.error = null

        // Category required (spinner always has default but keep check)
        if (binding.spinnerCategory.selectedItem == null) {
            binding.tilCategory.error = "Category required"
            ok = false
        } else binding.tilCategory.error = null

        // Date/time required
        if (TextUtils.isEmpty(binding.etDateTime.text)) {
            binding.tilDateTime.error = "Date & time required"
            ok = false
        } else binding.tilDateTime.error = null

        // Rating 1–5
        val ratingText = binding.etRating.text.toString()
        val rating = ratingText.toIntOrNull()
        if (rating == null || rating !in 1..5) {
            binding.tilRating.error = "Rating must be between 1–5"
            ok = false
        } else binding.tilRating.error = null

        return ok
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
