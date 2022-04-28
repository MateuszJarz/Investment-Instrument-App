package com.example.investment_instrument_app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.investment_instrument_app.data.local.InvestmentInstrumentDatabase
import com.example.investment_instrument_app.untill.Constants.INSTRUMENT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): InvestmentInstrumentDatabase {
        return Room.databaseBuilder(
            context,
            InvestmentInstrumentDatabase::class.java,
            INSTRUMENT_DATABASE
        ).build()
    }
}