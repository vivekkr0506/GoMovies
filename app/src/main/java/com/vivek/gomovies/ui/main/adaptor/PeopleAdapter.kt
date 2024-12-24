package com.vivek.gomovies.ui.main.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vivek.gomovies.databinding.ItemPersonBinding
import com.vivek.gomovies.model.Person

class PeopleAdapter(
    private val onPersonClicked: (Int) -> Unit
) : PagingDataAdapter<Person, PeopleAdapter.PersonViewHolder>(PersonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding, onPersonClicked)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        person?.let { holder.bind(it) }
    }

    class PersonViewHolder(
        private val binding: ItemPersonBinding,
        private val onPersonClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.apply {
                profileImage.load(person.real_path)
                personName.text = person.name

                root.setOnClickListener {
                    person.id?.let { it1 -> onPersonClicked(it1) }
                }
            }
        }
    }

    companion object {
        private val PersonDiffCallback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }
        }
    }
}
