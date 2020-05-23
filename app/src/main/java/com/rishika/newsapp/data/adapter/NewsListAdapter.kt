package com.rishika.newsapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rishika.newsapp.data.News
import com.rishika.newsapp.databinding.LayoutListItemNewsBinding
import com.rishika.newsapp.ui.fragment.NewsListFragmentDirections

/**
 * Created by Rishika on 23/05/20.
 */
class NewsListAdapter : PagedListAdapter<News, NewsListAdapter.NewsListHolder>(
    NewsDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListHolder {
        val binder =
            LayoutListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListHolder(binder)
    }

    override fun onBindViewHolder(holder: NewsListHolder, position: Int) {
        val news = getItem(position)
        news?.let {
            holder.apply {
                bind(it)
                itemView.tag = it
            }
        }
    }

    inner class NewsListHolder(private val binder: LayoutListItemNewsBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bind(news: News) {
            binder.clickListener = createItemClickListener(news)
            binder.news = news
            binder.executePendingBindings()
        }

        private fun createItemClickListener(news: News) = View.OnClickListener {
            val extras = FragmentNavigatorExtras(
                binder.ivNews to binder.ivNews.transitionName,
                binder.tvTitle to binder.tvTitle.transitionName
            )
            val direction =
                NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(news)
            it.findNavController().navigate(direction, extras)
        }
    }

}

object NewsDiffUtil : DiffUtil.ItemCallback<News>() {
    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean = oldItem == newItem

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean = oldItem.id == newItem.id
}