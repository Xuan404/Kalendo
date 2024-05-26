package com.example.kalendo.data.mapper

import com.example.kalendo.data.local.entity.CourseEntity
import com.example.kalendo.domain.model.CourseModel

fun CourseEntity.toDomainModel(): CourseModel {
    return CourseModel(
        id = this.id,
        title = this.title,
        color = this.color
    )
}

fun CourseModel.toEntityModel(): CourseEntity {
    return CourseEntity(
        id = this.id,
        title = this.title,
        color = this.color
    )
}