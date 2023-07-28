package com.ncs.heyquotes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quotes(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val author: String,
    val text: String,

    )