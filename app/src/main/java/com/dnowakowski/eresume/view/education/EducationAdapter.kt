package com.dnowakowski.eresume.view.education

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnowakowski.eresume.databinding.ListItemEducationBinding
import com.dnowakowski.eresume.model.Education
import com.dnowakowski.eresume.util.binding.BindableAdapter
import com.dnowakowski.eresume.view.education.EducationAdapter.Companion.FROM_TO_RANGE_FORMAT

/**
 * Created by Damian Nowakowski on 27 January 2020
 */
class EducationAdapter(
    private var educations: List<Education> = emptyList()
): RecyclerView.Adapter<EducationViewHolder>(), BindableAdapter<Education> {

    companion object {
        const val FROM_TO_RANGE_FORMAT = "%s - %s"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemEducationBinding.inflate(inflater, parent, false)
        return EducationViewHolder(binding)
    }

    override fun getItemCount() = educations.size

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) =
        holder.bind(educations[position])

    override fun setData(data: List<Education>) {
        educations = data
        notifyDataSetChanged()
    }
}

class EducationViewHolder(
    private val binding: ListItemEducationBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(education: Education) {
        binding.education = education
    }
}

fun Education.printableDates(): String =
    String.format(FROM_TO_RANGE_FORMAT, startYear, endYear)