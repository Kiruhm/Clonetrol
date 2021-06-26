package com.example.clonetrol.ui.chat.lists

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.clonetrol.R
import com.example.clonetrol.models.Android
import com.example.clonetrol.models.Clone
import com.example.clonetrol.models.Resource
import com.example.clonetrol.ui.chat.callbacks.ContactCallback
import com.squareup.picasso.Picasso

class ContactsListAdapter(private val contacts: List<Resource?>, private val context: Context, private val callback: ContactCallback) : RecyclerView.Adapter<ContactsListAdapter.ContactsListViewHolder>(){

    class ContactsListViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact_list, parent, false)

        return ContactsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {

        val current = contacts[position]

        current?.let {
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.colorPrimary, value, true)
            holder.itemView.setOnClickListener {_ ->
                callback.onContactSelected(it)
            }
            holder.itemView.findViewById<TextView>(R.id.nameContactTextView).text = it.name
            Picasso.get().load(it.imageRes).into(holder.itemView.findViewById<ImageView>(R.id.circle_contact_image))
            when (it.javaClass) {
                Clone::class.java -> {
                    holder.itemView.findViewById<ImageView>(R.id.typeImageView)?.let { image ->
                        Picasso.get().load(R.drawable.outline_face_black_36).into(image)
                        image.imageTintList = ContextCompat.getColorStateList(context, value.resourceId)
                    }
                }
                Android::class.java -> {
                    holder.itemView.findViewById<ImageView>(R.id.typeImageView)?.let { image ->
                        Picasso.get().load(R.drawable.outline_smart_toy_black_36).into(image)
                        image.imageTintList = ContextCompat.getColorStateList(context, value.resourceId)
                    }
                }
                else -> {}
            }
        }

    }

    override fun getItemCount(): Int = contacts.size
}