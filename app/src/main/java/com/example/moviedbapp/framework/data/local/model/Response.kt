package com.example.moviedbapp.framework.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "response", indices = [Index(value = ["id"], unique = true)])
data class Response(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int?,
    @ColumnInfo("page") val page: Int,
    @ColumnInfo("total_pages") val total_pages: Int,
    @ColumnInfo("total_results") val total_results: Int
)
