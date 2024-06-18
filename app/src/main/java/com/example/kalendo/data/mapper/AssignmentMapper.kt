package com.example.kalendo.data.mapper

import com.example.kalendo.data.local.entity.AssignmentEntity
import com.example.kalendo.domain.model.AssignmentModel

fun AssignmentEntity.toDomainModel(): AssignmentModel {
    return AssignmentModel(
        id = this.id,
        courseId = this.courseId,
        title = this.title,
        date = this.date,
        time = this.time,
        isDeadline = this.isDeadline,
        isCompleted = this.isCompleted

    )
}

fun AssignmentModel.toEntityModel(): AssignmentEntity {
    return AssignmentEntity(
        id = this.id,
        courseId = this.courseId,
        title = this.title,
        date = this.date,
        time = this.time,
        isDeadline = this.isDeadline,
        isCompleted = this.isCompleted
    )
}