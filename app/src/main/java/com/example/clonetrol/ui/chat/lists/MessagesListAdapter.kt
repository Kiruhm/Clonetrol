package com.example.clonetrol.ui.chat.lists

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.clonetrol.R
import com.example.clonetrol.models.Message
import com.example.clonetrol.models.enums.OwnerEnum


class MessagesListAdapter (private val messages: List<Message?>, private val context: Context) : RecyclerView.Adapter<MessagesListAdapter.MessagesListViewHolder>(){

    class MessagesListViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)

        return MessagesListViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MessagesListViewHolder, position: Int) {
        val current = messages[position]

        val value = TypedValue()
        context.theme.resolveAttribute(R.attr.colorPrimary, value, true)

        current?.let {
            val item = holder.itemView.findViewById<TextView>(R.id.message_item)
            if (current.owner == OwnerEnum.SENDER){
                holder.itemView.apply {
                    (this as LinearLayout).gravity = Gravity.END
                    item.backgroundTintList = ContextCompat.getColorStateList(context, value.resourceId)
                    (item as TextView).text = current.message
                }
            } else {
                holder.itemView.apply {
                    (this as LinearLayout).gravity = Gravity.START
                    item.backgroundTintList = ContextCompat.getColorStateList(context, R.color.appGrey)
                    item.text = current.message
                }
            }
        }
    }

    override fun getItemCount(): Int = messages.size
}