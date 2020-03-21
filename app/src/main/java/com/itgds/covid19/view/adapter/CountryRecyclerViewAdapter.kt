package com.itgds.covid19.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itgds.covid19.R
import com.itgds.covid19.services.response.CountriesStat
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountryRecyclerViewAdapter(private var countries: List<CountriesStat> = listOf(),
                                 private val onItemClick: ((item: CountriesStat) -> Unit)? = null)
    : RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View, private val onItemClick: ((item: CountriesStat) -> Unit)? = null): RecyclerView.ViewHolder(itemView) {
        fun bind(countriesStat: CountriesStat) {
            itemView.setOnClickListener {
                onItemClick?.invoke(countriesStat)
            }
            itemView.tv_country_name.text = countriesStat.countryName
            itemView.tv_confirmed.text = countriesStat.cases
            itemView.tv_deceased.text = countriesStat.deaths
        }

    }

    fun updateCountries(list: List<CountriesStat>){
        countries = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = layoutInflater.inflate(R.layout.country_list_item, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}