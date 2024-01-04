package com.admin.admineventmanagement.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.admin.admineventmanagement.model.UserEvent

@BindingAdapter("username")
fun bindUsername(textView: TextView, username: String?) {
    textView.text = username
}

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UserEvent>?) {
    val adapter = recyclerView.adapter as UserEventAdapter
    adapter.submitList(data)
}

