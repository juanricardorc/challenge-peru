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
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse

class MonetaryUnitFragment : Fragment(), MonetaryUnitListener {

    private lateinit var binding: FragmentMonetaryUnitBinding
    private val exchangeRateViewModel: ExchangeRateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentMonetaryUnitBinding
            .inflate(layoutInflater, container, false)
        setupRecyclerView()
        setupListeners()
        return binding.root
    }

    private fun setupRecyclerView() {
        context?.let { exchangeRateViewModel.getListMonetaryUnit(it) }
        exchangeRateViewModel.getListMonetaryUnitResponse()
            .observe(viewLifecycleOwner, Observer { model ->
                run {
                    var monetaryAdapter = context?.let { MonetaryUnitAdapter(model, it) }
                    monetaryAdapter?.setMonetaryUnitListener(this)
                    binding.countriesRecyclerView.layoutManager = LinearLayoutManager(context)
                    binding.countriesRecyclerView.adapter = monetaryAdapter
                }
            })
    }

    private fun setupListeners() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_MonetaryUnitFragment_to_ExchangeRateFragment)
        }
    }

    override fun onClickMonetaryUnit(value: Any) {
        if (value is MonetaryUnitResponse) {
            if (exchangeRateViewModel.getAboveFlag()) {
                this.exchangeRateViewModel.setAboveValue(value)
            } else if (exchangeRateViewModel.getBelowFlag()) {
                this.exchangeRateViewModel.setBelowValue(value)
            }
        }
        findNavController().navigate(R.id.action_MonetaryUnitFragment_to_ExchangeRateFragment)
    }
}