package com.rishika.newsapp.di

import com.rishika.newsapp.ui.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Rishika on 23/05/20.
 */
@Suppress("unused")
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun newsActivity(): NewsActivity
}
