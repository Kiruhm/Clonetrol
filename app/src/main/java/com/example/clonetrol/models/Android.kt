package com.example.clonetrol.models

import androidx.annotation.DrawableRes
import com.example.clonetrol.models.enums.Status

class Android( bwid: String, name: String, @DrawableRes imageRes: Int, status: Status) : Resource(bwid, name, imageRes, status)