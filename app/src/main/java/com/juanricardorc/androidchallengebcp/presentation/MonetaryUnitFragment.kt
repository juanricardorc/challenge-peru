package com.juanricardorc.androidchallengebcp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanricardorc.androidchallengebcp.R
import com.juanricardorc.androidchallengebcp.databinding.FragmentMonetaryUnitBinding

class MonetaryUnitFragment : Fragment() {

    private lateinit var binding: FragmentMonetaryUnitBinding
    private val exchangeRateViewModel: ExchangeRateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentMonetaryUnitBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        context?.let { exchangeRateViewModel.getListMonetaryUnit(it) }
        exchangeRateViewModel.getListMonetaryUnitResponse().observe(viewLifecycleOwner, Observer {
            var monetaryAdapter: MonetaryAdapter = MonetaryAdapter(it)
            binding.countriesRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.countriesRecyclerView.adapter = monetaryAdapter
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}