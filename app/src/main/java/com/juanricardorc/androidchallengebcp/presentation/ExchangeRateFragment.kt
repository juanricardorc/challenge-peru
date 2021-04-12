package com.juanricardorc.androidchallengebcp.presentation

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
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

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        binding.appBarLayout.toolbar.title = getString(R.string.app_name)
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

        exchangeRateViewModel.getMonetaryUnitNotFound()
            .observe(viewLifecycleOwner, Observer {
                if (it) {
                    showMessageNotFound(getString((R.string.monetary_unit_not_found)))
                }
            })
    }

    private fun showMessageNotFound(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun setBelowEditText(rateResponse: RateResponse) {
        var aboveText = this.binding.exchangeRateView.getAboveText()
        Log.v("Logbcp: ", "aboveText -> $aboveText - rateResponse.value -> ${rateResponse.value}")
        if (!aboveText.isNullOrBlank() && rateResponse != null) {
            var result = aboveText.toFloat() * rateResponse.value
            this.binding.exchangeRateView.setBelowEditText(result.toString())
            setupInfo(rateResponse)
        }
    }

    private fun setupInfo(rateResponse: RateResponse) {
        var country = this.exchangeRateViewModel.getAboveValue().value?.country
        this.binding.infoTextView.text =
            "1 " + country + "  ==>>  " + rateResponse.monetary + " : " + rateResponse.value
    }

    private fun initialize() {
        this.binding.exchangeRateView.setExchangeRateAboveButtonListener(this)
        this.binding.exchangeRateView.setExchangeRateBelowButtonListener(this)
        this.binding.exchangeRateView.setExchangeButtonListener(this)
        this.binding.exchangeRateView.setAboveEditText("1")
    }

    private fun setupAboveButtonText(monetaryUnitResponse: MonetaryUnitResponse) {
        binding.exchangeRateView.setAboveButtonText(monetaryUnitResponse.country)
        getExchangeRate(monetaryUnitResponse.monetary)
    }

    private fun setupBelowButtonText(monetaryUnitResponse: MonetaryUnitResponse) {
        binding.exchangeRateView.setBelowButtonText(monetaryUnitResponse.country)
    }

    override fun onAboveValue(view: View, value: String) {
        this.exchangeRateViewModel.setOrigen(above = true, below = false)
        findNavController().navigate(R.id.action_ExchangeRateFragment_to_MonetaryUnitFragment)
    }

    override fun onBelowValue(view: View, value: String) {
        this.exchangeRateViewModel.setOrigen(above = false, below = true)
        findNavController().navigate(R.id.action_ExchangeRateFragment_to_MonetaryUnitFragment)
    }

    private fun getExchangeRate(monetary: String) {
        context?.let { exchangeRateViewModel.getExchangeRate(monetary, it) }
    }

    override fun onExchange(view: View, above: String, below: String) {
        val belowValue = this.exchangeRateViewModel.getBelowValue().value
        val aboveValue = this.exchangeRateViewModel.getAboveValue().value

        belowValue?.let {
            this.exchangeRateViewModel.setAboveValue(it)
        }
        aboveValue?.let {
            this.exchangeRateViewModel.setBelowValue(it)
        }

        val belowText = this.binding.exchangeRateView.getBelowText()
        this.binding.exchangeRateView.setAboveEditText(belowText)

        val newBelowValue = this.exchangeRateViewModel.getBelowValue().value
        //Log.v("Logbcp: ", "newBelowValue -> $newBelowValue")
        context?.let {
            this.exchangeRateViewModel.getExchangeRate(
                newBelowValue?.monetary, it
            )
        }
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
}