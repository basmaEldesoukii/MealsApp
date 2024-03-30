package com.basma.homepage.data.local

import androidx.room.TypeConverter
import com.basma.homepage.domain.entity.Data
import com.google.gson.Gson

class DataConverter {
    @TypeConverter
    fun fromJson(json: String?): Data {
        return Gson().fromJson(json, Data::class.java)
    }

    @TypeConverter
    fun toJson(data: Data): String {
        return Gson().toJson(data)
    }
}