package com.example.clonetrol.ui.chat.callbacks

import com.example.clonetrol.models.Resource

interface ContactCallback {
    fun onContactSelected(contact : Resource)
}