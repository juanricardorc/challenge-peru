package com.juanricardorc.androidchallengebcp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.juanricardorc.androidchallengebcp.R
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.squareup.picasso.Picasso

class MonetaryUnitAdapter(
    var monetaryUnits: List<MonetaryUnitResponse>,
    var context: Context
) :
    RecyclerView.Adapter<MonetaryUnitAdapter.CountryViewHolder>() {
    private var monetaryUnitListener: MonetaryUnitListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(layoutInflater.inflate(R.layout.country_item, parent, false))
    }

    override fun getItemCount(): Int {
        return this.monetaryUnits.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val monetaryUnit = this.monetaryUnits[position]
        holder.bind(monetaryUnit, context, monetaryUnitListener)
    }

    fun setMonetaryUnitListener(monetaryUnitListener: MonetaryUnitListener) {
        this.monetaryUnitListener = monetaryUnitListener
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val monetaryUnitConstraintLayout =
            view.findViewById(R.id.monetary_unit_constraint_layout) as ConstraintLayout
        private val icon = view.findViewById(R.id.icon_image_view) as ImageView
        private val name = view.findViewById(R.id.name_text_view) as TextView

        fun bind(
            monetaryUnitResponse: MonetaryUnitResponse, context: Context,
            monetaryUnitListener: MonetaryUnitListener?
        ) {
            name.text = monetaryUnitResponse.country + " - " + monetaryUnitResponse.monetary
            Picasso.with(context).load(monetaryUnitResponse.flag)
                .placeholder(R.drawable.ic_image)
                .into(icon)
            monetaryUnitConstraintLayout.setOnClickListener {
                monetaryUnitListener?.onClickMonetaryUnit(monetaryUnitResponse)
            }
        }
    }
}