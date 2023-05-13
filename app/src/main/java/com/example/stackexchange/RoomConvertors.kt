package com.example.stackexchange

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.stackexchange.model.HomeData.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class RoomConvertors {

    @TypeConverter
    fun fromString(value: String?): ArrayList<Item> {
        val listType = object :
            TypeToken<ArrayList<Item?>?>() {}.type
        return Gson()
            .fromJson<ArrayList<Item>>(value, listType)
    }

    @TypeConverter
    fun listToString(list: ArrayList<Item?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}