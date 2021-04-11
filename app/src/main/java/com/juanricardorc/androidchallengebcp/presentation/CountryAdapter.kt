package com.juanricardorc.androidchallengebcp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juanricardorc.androidchallengebcp.R
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse

class CountryAdapter(var countries: List<MonetaryUnitResponse>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val icon = view.findViewById(R.id.icon_image_view) as ImageView
        val name = view.findViewById(R.id.name_text_view) as TextView

        fun bind(monetaryUnitResponse: MonetaryUnitResponse) {
            name.text = monetaryUnitResponse.country + " - " + monetaryUnitResponse.monetary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(layoutInflater.inflate(R.layout.country_item, parent, false))
    }

    override fun getItemCount(): Int {
        return this.countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val get = this.countries[position]
        holder.bind(get)
    }

}