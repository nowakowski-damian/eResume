package com.dnowakowski.eresume.view.highlights

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnowakowski.eresume.databinding.ListItemHighlightBinding
import com.dnowakowski.eresume.util.binding.BindableAdapter

/**
 * Created by Damian Nowakowski on 27 January 2020
 */
class HighlightsAdapter(
    private var highlights: List<String> = emptyList()
): RecyclerView.Adapter<HighlightsViewHolder>(), BindableAdapter<String> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemHighlightBinding.inflate(inflater, parent, false)
        return HighlightsViewHolder(binding)
    }

    override fun getItemCount() = highlights.size

    override fun onBindViewHolder(holder: HighlightsViewHolder, position: Int) =
        holder.bind(highlights[position])

    override fun setData(data: List<String>) {
        highlights = data
        notifyDataSetChanged()
    }
}

class HighlightsViewHolder(
    private val binding: ListItemHighlightBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: String) {
        binding.highlightsItemContent.text = data
    }
}