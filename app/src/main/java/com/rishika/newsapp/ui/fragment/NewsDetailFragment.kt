package com.rishika.newsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rishika.newsapp.databinding.FragmentNewsDetailBinding

/**
 * Created by Rishika on 23/05/20.
 */
class NewsDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsDetailBinding.inflate(inflater)
        val arg = navArgs<NewsDetailFragmentArgs>().value.news


        binding.apply {
            news = arg
            onBackClick = finish()
            onReadMoreClick = getReadMore(news?.url)
        }

        return binding.root
    }

    private fun getReadMore(newsUrl: String?) = View.OnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, newsUrl?.toUri())
        startActivity(intent)
    }

    private fun finish() = View.OnClickListener {
        findNavController().popBackStack()
    }


}