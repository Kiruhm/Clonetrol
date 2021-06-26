package com.example.clonetrol.ui.resources.list

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.clonetrol.R
import com.example.clonetrol.models.Resource
import com.example.clonetrol.models.enums.Status
import com.example.clonetrol.ui.resources.callbacks.ResourceCallback

class ResourceListAdapter (private val resources: List<Resource?>, private val context: Context, private val callback : ResourceCallback) : RecyclerView.Adapter<ResourceListAdapter.ResourceListViewHolder>(){

    class ResourceListViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resource, parent, false)

        return ResourceListViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ResourceListViewHolder, position: Int) {
        val current = resources[position]

        current?.let {
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.colorPrimaryVariant, value, true)

            when(it.status){
                Status.CORRECT -> holder.itemView.findViewById<ImageView>(R.id.statusImage).imageTintList = ContextCompat.getColorStateList(context, value.resourceId)
                Status.WARNING -> holder.itemView.findViewById<ImageView>(R.id.statusImage).imageTintList = ContextCompat.getColorStateList(context, R.color.warningColor)
                Status.ALERT -> holder.itemView.findViewById<ImageView>(R.id.statusImage).imageTintList = ContextCompat.getColorStateList(context, R.color.alertColor)
            }

            holder.itemView.findViewById<TextView>(R.id.nameResourceTextView).text = it.name
            holder.itemView.setOnClickListener { _ ->
                callback.onResourcePressed(it)
            }
        } ?: run {
            holder.itemView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = resources.size
}