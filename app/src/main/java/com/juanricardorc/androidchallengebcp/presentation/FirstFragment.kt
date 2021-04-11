package com.juanricardorc.androidchallengebcp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.juanricardorc.androidchallengebcp.R
import com.juanricardorc.androidchallengebcp.databinding.FragmentFirstBinding
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.juanricardorc.uicomponents.exchangerate.ExchangeRateAboveButtonListener
import com.juanricardorc.uicomponents.exchangerate.ExchangeRateBelowButtonListener

class FirstFragment : Fragment(), ExchangeRateBelowButtonListener, ExchangeRateAboveButtonListener {

    private lateinit var binding: FragmentFirstBinding
    private val exchangeRateViewModel: ExchangeRateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        binding.exchangeRateView.setExchangeRateAboveButtonListener(this)
        binding.exchangeRateView.setExchangeRateBelowButtonListener(this)
        setupObservers()
        initData()
        return binding.root
    }

    private fun setupObservers() {
        context?.let { exchangeRateViewModel.getListMonetaryUnit(it) }
        exchangeRateViewModel.getAboveValue()
            .observe(viewLifecycleOwner, Observer {
                setupAboveButtonText(it)
            })

        exchangeRateViewModel.getBelowValue()
            .observe(viewLifecycleOwner, Observer {
                setupBelowButtonText(it)
            })

        exchangeRateViewModel.getResult()
            .observe(viewLifecycleOwner, Observer {
                setBelowEditText(it)

            })
    }

    private fun initData() {
        this.binding.exchangeRateView.setAboveEditText("2")
    }

    private fun setBelowEditText(value: Float) {
        var aboveValue = this.binding.exchangeRateView.getAboveText()
        var result = aboveValue.toFloat() * value
        this.binding.exchangeRateView.setBelowEditText(result.toString())
    }

    private fun setupAboveButtonText(monetaryUnitResponse: MonetaryUnitResponse) {
        binding.exchangeRateView.setAboveButtonText(monetaryUnitResponse.country)
        getExchangeRate(monetaryUnitResponse.monetary)
    }

    private fun setupBelowButtonText(monetaryUnitResponse: MonetaryUnitResponse) {
        binding.exchangeRateView.setBelowButtonText(monetaryUnitResponse.country)
    }

    override fun onBelowValue(view: View, value: String) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun onAboveValue(view: View, value: String) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun getExchangeRate(monetary: String) {
        context?.let { exchangeRateViewModel.getExchangeRate(monetary, it) }
    }

}