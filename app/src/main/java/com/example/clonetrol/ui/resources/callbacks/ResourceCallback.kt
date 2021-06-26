package com.example.clonetrol.ui.resources.callbacks

import com.example.clonetrol.models.Resource

interface ResourceCallback {
    fun onResourcePressed(resource : Resource)
}