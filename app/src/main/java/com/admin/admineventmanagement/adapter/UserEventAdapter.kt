package com.admin.admineventmanagement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.admin.admineventmanagement.databinding.UserEventListBinding
import com.admin.admineventmanagement.model.UserEvent

class UserEventAdapter : ListAdapter<UserEvent,
        UserEventAdapter.UserEventViewHolder>(DiffCallback)  {
    class UserEventViewHolder (
        private var binding: UserEventListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userEvent: UserEvent) {
            binding.model = userEvent
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [UserEvent] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<UserEvent>() {
        override fun areItemsTheSame(oldItem: UserEvent, newItem: UserEvent): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: UserEvent, newItem: UserEvent): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserEventViewHolder {
        return UserEventViewHolder(
            UserEventListBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: UserEventViewHolder, position: Int) {
        val userEvent = getItem(position)
        holder.bind(userEvent)
    }
}