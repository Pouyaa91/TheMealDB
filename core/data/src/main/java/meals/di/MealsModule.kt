package meals.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import meals.repository.MealsByCategoryRepository
import meals.repository.MealsByCategoryRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface MealsModule {
    @Binds
    fun provideMealsRepository(impl: MealsByCategoryRepositoryImpl): MealsByCategoryRepository
}