package com.mobilesales.ecommerce.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobilesales.ecommerce.model.*

@Database(
    entities = [Order::class,
        User::class,
        UserAddress::class,
        ProductSize::class,
        ProductImage::class,
        ProductColor::class,
        ProductCategory::class,
        Product::class,
        OrderedProduct::class], version = 1)

@TypeConverters(Converters ::class)
abstract class AppDataBase : RoomDatabase() {

abstract fun userDao() : UserDao
abstract fun productDao() : ProductDao
abstract fun orderDao() : OrderDao
abstract fun productCategoryDao() : ProductCategoryDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Application): AppDataBase {

            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "appcommerce_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .createFromAsset("appcommerce_database.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}