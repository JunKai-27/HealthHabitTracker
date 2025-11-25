package com.example.healthhabittracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.healthhabittracker.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())

        binding.cbSkipWelcome.text = getString(R.string.skip_welcome_screen)
        binding.btnResetWelcome.text = getString(R.string.show_welcome_again)

        binding.cbSkipWelcome.isChecked = prefs.skipWelcome

        binding.cbSkipWelcome.setOnCheckedChangeListener { _, isChecked ->
            prefs.skipWelcome = isChecked
        }

        binding.btnResetWelcome.setOnClickListener {
            prefs.skipWelcome = false
            binding.cbSkipWelcome.isChecked = false

            Toast.makeText(
                requireContext(),
                getString(R.string.welcome_reset_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
