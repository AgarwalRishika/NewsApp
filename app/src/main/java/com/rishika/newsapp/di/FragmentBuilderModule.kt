package com.rishika.newsapp.di

import com.rishika.newsapp.ui.fragment.NewsDetailFragment
import com.rishika.newsapp.ui.fragment.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Rishika on 23/05/20.
 */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsDetailFragment(): NewsDetailFragment

}
