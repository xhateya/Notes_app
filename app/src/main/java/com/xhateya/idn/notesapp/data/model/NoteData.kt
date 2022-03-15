package com.xhateya.idn.notesapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName= "todo_table")
@Parcelize
class NoteData (
    @PrimaryKey(autoGenerate = true)

    val id: Int,
    val title: String,
    val priority: Priority,
    val description: String

): Parcelable