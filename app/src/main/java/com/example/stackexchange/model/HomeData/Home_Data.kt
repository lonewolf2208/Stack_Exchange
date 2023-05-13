package com.example.stackexchange.model.HomeData

import androidx.room.*
import com.example.stackexchange.RoomConvertors


@Entity(tableName = "questions")
data class Home_Data(
    var has_more: Boolean,
    var items:ArrayList<Item>,
    @PrimaryKey var quota_max: Int,
    var quota_remaining: Int
)
