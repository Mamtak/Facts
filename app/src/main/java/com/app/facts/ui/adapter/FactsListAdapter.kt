package com.app.facts.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.facts.databinding.FactsItemBinding

class FactsListAdapter(
    private val factslList: ArrayList<String>
) : RecyclerView.Adapter<FactsListAdapter.FactsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateChannelList(newFactsList: ArrayList<String>) {
        factslList.clear()
        factslList.addAll(newFactsList)
        notifyDataSetChanged()
    }

    inner class FactsViewHolder(private val binding: FactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: String
        ) {
            binding.factDescription.text = data

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FactsItemBinding.inflate(inflater, parent, false)
        return FactsViewHolder(binding)
    }

    override fun getItemCount() = factslList.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val data = factslList[position]
        holder.bind(data)
    }


}