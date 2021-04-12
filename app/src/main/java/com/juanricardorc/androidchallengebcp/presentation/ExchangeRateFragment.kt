package com.juanricardorc.androidchallengebcp.presentation

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.juanricardorc.androidchallengebcp.R
import com.juanricardorc.androidchallengebcp.databinding.FragmentExchangeRateBinding
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.juanricardorc.androidchallengebcp.datasource.response.RateResponse
import com.juanricardorc.uicomponents.exchangerate.ExchangeButtonListener
import com.juanricardorc.uicomponents.exchangerate.ExchangeRateAboveButtonListener
import com.juanricardorc.uicomponents.exchangerate.ExchangeRateBelowButtonListener

class ExchangeRateFragment : Fragment(), ExchangeRateBelowButtonListener,
    ExchangeRateAboveButtonListener, ExchangeButtonListener {

    private lateinit var binding: FragmentExchangeRateBinding
    private val exchangeRateViewModel: ExchangeRateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExchangeRateBinding.inflate(layoutInflater, container, false)
        setupObservers()
        setupToolbar()
        initialize()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                this.binding.exchangeRateView.setAboveEditText("3")
                return true
            }
        }
        return false
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        binding.appBarLayout.toolbar.title = "Exchange Rates"
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.appBarLayout.toolbar)
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

        exchangeRateViewModel.getRateResponse()
            .observe(viewLifecycleOwner, Observer {
                setBelowEditText(it)
            })
    }

    private fun setBelowEditText(rateResponse: RateResponse) {
        var aboveValue = this.binding.exchangeRateView.getAboveText()
        var result = aboveValue.toFloat() * rateResponse.value
        this.binding.exchangeRateView.setBelowEditText(result.toString())
    }

    private fun initialize() {
        this.binding.exchangeRateView.setExchangeRateAboveButtonListener(this)
        this.binding.exchangeRateView.setExchangeRateBelowButtonListener(this)
        this.binding.exchangeRateView.setExchangeButtonListener(this)
        this.binding.exchangeRateView.setAboveEditText("2")
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

    override fun onExchange(view: View, above: String, below: String) {
        val belowValue = this.exchangeRateViewModel.getBelowValue().value
        val aboveValue = this.exchangeRateViewModel.getAboveValue().value
        belowValue?.let { this.exchangeRateViewModel.setAboveValue(it) }
        aboveValue?.let { this.exchangeRateViewModel.setBelowValue(it) }
        this.binding.exchangeRateView.setAboveEditText(this.binding.exchangeRateView.getBelowText())
        this.binding.exchangeRateView.setBelowEditText(this.binding.exchangeRateView.getAboveText())
    }
}