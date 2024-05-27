package com.example.kalendo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String,
    var color: Int
) {
    constructor() : this(0, "", 0)
}


