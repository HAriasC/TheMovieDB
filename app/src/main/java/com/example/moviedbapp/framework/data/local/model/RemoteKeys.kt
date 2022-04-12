package com.example.moviedbapp.framework.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys", indices = [Index(value = ["id"], unique = true)])
data class RemoteKeys(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("prevKey") val prevKey: Int?,
    @ColumnInfo("nextKey") val nextKey: Int?
)