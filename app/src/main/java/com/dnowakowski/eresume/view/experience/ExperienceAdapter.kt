package com.dnowakowski.eresume.view.experience

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnowakowski.eresume.databinding.ListItemExperienceBinding
import com.dnowakowski.eresume.model.Experience
import com.dnowakowski.eresume.util.binding.BindableAdapter
import com.dnowakowski.eresume.view.experience.ExperienceAdapter.Companion.FROM_TO_RANGE_FORMAT
import com.dnowakowski.eresume.view.experience.ExperienceAdapter.Companion.PRINTABLE_DATE_FORMAT
import com.dnowakowski.eresume.view.experience.ExperienceAdapter.Companion.RESPONSIBILITY_ROW_FORMAT
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Damian Nowakowski on 27 January 2020
 */
class ExperienceAdapter(
    private var experiences: List<Experience> = emptyList()
): RecyclerView.Adapter<ExperienceViewHolder>(), BindableAdapter<Experience> {

    companion object {
        const val PRINTABLE_DATE_FORMAT = "MMMM YYYY"
        const val FROM_TO_RANGE_FORMAT = "%s - %s"
        const val RESPONSIBILITY_ROW_FORMAT = "- %s\n\n"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemExperienceBinding.inflate(inflater, parent, false)
        return ExperienceViewHolder(binding)
    }

    override fun getItemCount() = experiences.size

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) =
        holder.bind(experiences[position])

    override fun setData(data: List<Experience>) {
        experiences = data
        notifyDataSetChanged()
    }
}

class ExperienceViewHolder(
    private val binding: ListItemExperienceBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(experience: Experience) {
        binding.experience = experience
    }
}

fun Experience.printableDates(): String {
    val parser = SimpleDateFormat(Experience.DATE_FORMAT, Locale.getDefault())
    val formatter = SimpleDateFormat(PRINTABLE_DATE_FORMAT, Locale.getDefault())
    val parsedStartDate = parser.parse(startDate)
    val parsedEndDate = parser.parse(endDate)
    return if(parsedStartDate!=null && parsedEndDate!=null) {
        String.format(FROM_TO_RANGE_FORMAT, formatter.format(parsedStartDate), formatter.format(parsedEndDate))
    } else ""
}

fun Experience.printableResponsibilities(): String {
    val builder = StringBuilder()
    responsibilities.forEach {
        val row = String.format(RESPONSIBILITY_ROW_FORMAT, it)
        builder.append(row)
    }
    return builder.toString().trim()
}