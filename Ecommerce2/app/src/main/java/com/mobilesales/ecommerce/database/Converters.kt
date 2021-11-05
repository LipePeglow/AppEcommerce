package com.mobilesales.ecommerce.database

import androidx.room.TypeConverter
import com.mobilesales.ecommerce.model.Order
import javax.net.ssl.SSLEngineResult

class Converters {
    @TypeConverter
    fun fromStatus(status: Order.Status ) : String = status.toString()
    @TypeConverter
    fun toStatus(string: String) : Order.Status = Order.Status.valueOf(string)
    @TypeConverter
    fun fromMethod(method: Order.Method) : String = method.toString()
    @TypeConverter
    fun toMethod(string : String) : Order.Method = Order.Method.valueOf(string)
}