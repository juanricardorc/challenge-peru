package com.juanricardorc.uicomponents.exchangerate

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.juanricardorc.uicomponents.R

class ExchangeRateView : CoordinatorLayout {

    private lateinit var typedArray: TypedArray
    private lateinit var aboveEditText: EditText
    private lateinit var belowEditText: EditText
    private lateinit var aboveMaterialButton: MaterialButton
    private lateinit var belowMaterialButton: MaterialButton
    private lateinit var exchangeMaterialCardView: MaterialCardView
    private var exchangeRateBelowButtonListener: ExchangeRateBelowButtonListener? = null
    private var exchangeRateAboveButtonListener: ExchangeRateAboveButtonListener? = null
    private var exchangeButtonListener: ExchangeButtonListener? = null

    constructor(context: Context) : super(context) {
        setupView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setupView(context)
        setupTypeArray(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        setupView(context)
        setupTypeArray(context, attributeSet)
    }

    private fun setupView(context: Context) {
        View.inflate(context, R.layout.exchange_rate_view, this)
        setupFindViewById()
    }

    private fun setupTypeArray(context: Context, attributeSet: AttributeSet) {
        typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ExchangeRateView)
    }

    private fun setupFindViewById() {
        aboveMaterialButton = findViewById(R.id.above_material_button)
        aboveMaterialButton.setOnClickListener { view ->
            exchangeRateAboveButtonListener?.let {
                exchangeRateAboveButtonListener!!.onAboveValue(view, getAboveText())
            }
        }

        belowMaterialButton = findViewById(R.id.below_material_button)
        belowMaterialButton.setOnClickListener { view ->
            exchangeRateBelowButtonListener?.let {
                exchangeRateBelowButtonListener!!.onBelowValue(view, getBelowText())
            }
        }

        exchangeMaterialCardView = findViewById(R.id.exchange_material_card_view)
        exchangeMaterialCardView.setOnClickListener { view ->
            exchangeButtonListener?.let {
                exchangeButtonListener!!.onExchange(view, getAboveText(), getBelowText())
            }
        }
        aboveEditText = findViewById(R.id.above_edit_text)
        belowEditText = findViewById(R.id.below_edit_text)
    }

    fun setExchangeRateBelowButtonListener(exchangeRateBelowButtonListener: ExchangeRateBelowButtonListener) {
        this.exchangeRateBelowButtonListener = exchangeRateBelowButtonListener
    }

    fun setExchangeRateAboveButtonListener(exchangeRateAboveButtonListener: ExchangeRateAboveButtonListener) {
        this.exchangeRateAboveButtonListener = exchangeRateAboveButtonListener
    }

    fun setExchangeButtonListener(exchangeButtonListener: ExchangeButtonListener) {
        this.exchangeButtonListener = exchangeButtonListener
    }

    fun getAboveText(): String {
        var value = EMPTY
        if (aboveEditText == null) {
            return value
        }
        return aboveEditText.text.toString()
    }

    fun setAboveEditText(text: String) {
        this.aboveEditText.setText(text)
    }

    fun setBelowEditText(text: String) {
        this.belowEditText.setText(text)
    }

    fun getBelowText(): String {
        var value = EMPTY
        if (belowEditText == null) {
            return value
        }
        return belowEditText.text.toString()
    }

    fun setAboveButtonText(text: String) {
        this.aboveMaterialButton.text = text
    }

    fun setBelowButtonText(text: String) {
        this.belowMaterialButton.text = text
    }
}

