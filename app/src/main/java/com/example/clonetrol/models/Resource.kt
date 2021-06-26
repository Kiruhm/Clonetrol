package com.example.clonetrol.models

import androidx.annotation.DrawableRes
import com.example.clonetrol.models.enums.Status
import java.io.Serializable

abstract class Resource(var bwid: String, var name: String, @DrawableRes var imageRes: Int,  var status: Status) :Serializable
