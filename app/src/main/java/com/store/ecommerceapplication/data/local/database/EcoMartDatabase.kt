package com.store.ecommerceapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.store.ecommerceapplication.data.local.converter.StringListConverter
import com.store.ecommerceapplication.data.local.dao.WishlistDao
import com.store.ecommerceapplication.domain.model.Product

@Database(
    entities = [Product::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class EcoMartDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao
}
