package com.example.investment_instrument_app.di

import com.example.investment_instrument_app.data.repository.Repository
import com.example.investment_instrument_app.domain.use_case.UseCase
import com.example.investment_instrument_app.domain.use_case.get_all_instrument.GetAllInstrumentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCase {
        return UseCase(
            getAllInstrumentsUseCase = GetAllInstrumentsUseCase(repository = repository)
        )
    }
}