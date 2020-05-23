package com.rishika.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rishika.newsapp.data.NewsViewModel
import com.rishika.newsapp.data.adapter.NewsListAdapter
import com.rishika.newsapp.databinding.FragmentNewsListBinding
import com.rishika.newsapp.di.injectViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by Rishika on 23/05/20.
 */
class NewsListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentNewsListBinding
    private var newsAdapter = NewsListAdapter()
    private lateinit var viewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    fun init() {
        binding.recyclerView.adapter = newsAdapter
        viewModel.list.observe(viewLifecycleOwner, Observer {
            newsAdapter.submitList(it)
        })

    }


}