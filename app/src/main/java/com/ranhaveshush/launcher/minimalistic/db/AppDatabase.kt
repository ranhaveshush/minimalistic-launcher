package com.ranhaveshush.launcher.minimalistic.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ranhaveshush.launcher.minimalistic.db.notification.NotificationDao
import com.ranhaveshush.launcher.minimalistic.db.notification.NotificationEntity

@Database(
    entities = [NotificationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}