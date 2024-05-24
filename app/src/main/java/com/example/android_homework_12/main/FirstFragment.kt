package com.example.android_homework_12.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.android_homework_12.R
import com.example.android_homework_12.databinding.FragmentFirstBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var minCountSymbol: Int = 3
    private var searchQuery: String = ""
    private var searchStatus: String = "Запрос обрабатывается"
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSearch.isEnabled = false
        binding.editText.addTextChangedListener {
            checkOnCountSymbol()
            searchQuery = binding.editText.text.toString()
        }
        binding.buttonSearch.setOnClickListener {
            viewModel.search(searchQuery)
        }
        viewLifecycleOwner.lifecycleScope
            .launch {
                viewModel.state
                    .collect {state ->
                        when(state) {
                            State.Search -> {
                                onSearch()
                            }
                            State.Succes -> {
                                resultSearch(binding.editText.text.toString())
                            }
                        }
                    }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun checkOnCountSymbol() {
        if (binding.editText.text.length >= minCountSymbol) {
            binding.buttonSearch.isEnabled = true
        }
        else {
            binding.buttonSearch.isEnabled = false
        }
    }
    private fun onSearch() {
        binding.progress.isVisible = true
        binding.text.text = searchStatus
    }
    private fun resultSearch(text: String) {
        binding.progress.isVisible = false
        binding.text.text = "По запросу $text ничего не найдено"
        binding.editText.setText("")
    }
}