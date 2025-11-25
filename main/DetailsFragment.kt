package com.example.healthhabittracker

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.healthhabittracker.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    // TODO: load real data from args Database
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvName.text = "Sample habit name"
        binding.tvCategory.text = "Exercise"
        binding.tvRating.text = "4"
        binding.tvDateTime.text = "21 Nov 2025, 08:00"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}