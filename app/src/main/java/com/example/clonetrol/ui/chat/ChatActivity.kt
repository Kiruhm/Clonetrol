package com.example.clonetrol.ui.chat

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clonetrol.R
import com.example.clonetrol.databinding.ActivityChatBinding
import com.example.clonetrol.models.Message
import com.example.clonetrol.models.Resource
import com.example.clonetrol.models.enums.OwnerEnum
import com.example.clonetrol.ui.chat.lists.MessagesListAdapter
import com.squareup.picasso.Picasso

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    private lateinit var contact : Resource
    private var messages : MutableList<Message> = mutableListOf(
        Message(OwnerEnum.SENDER, "Hola, ¿qué tal la compra?"),
        Message(OwnerEnum.RECEIVER, "Bien, aunque no he encontrado el tabasco"),
        Message(OwnerEnum.RECEIVER, "bueno, ni  el helado"),
        Message(OwnerEnum.SENDER, "No pasa nada")
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getSerializableExtra(ContactsFragment.CONTACT)?.let {
            try {
                contact = it as Resource
            } catch (exception: Exception){ null }
        } ?: finish()

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.hide()
        supportActionBar?.hide()

        binding.include.nameContactTextView.text = contact.name
        binding.include.typeImageView.visibility = View.GONE
        binding.include.contactLinearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.appBlueLighter))
        Picasso.get().load(contact.imageRes).into(binding.include.circleContactImage)

        binding.messagesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MessagesListAdapter(messages, context)
        }

        binding.sendMessageBtn.setOnClickListener { sendMessage(context = this@ChatActivity) }
    }

    private fun sendMessage(context: Context){
        if (binding.editTextMessage.text.isNullOrEmpty() || binding.editTextMessage.text.isBlank())
            return
        else {
            messages.add(Message(OwnerEnum.SENDER, binding.editTextMessage.text.toString()))
            binding.messagesList.adapter = MessagesListAdapter(messages, context)
            binding.messagesList.smoothScrollToPosition(messages.size-1)
            binding.editTextMessage.text = null
        }
    }

}