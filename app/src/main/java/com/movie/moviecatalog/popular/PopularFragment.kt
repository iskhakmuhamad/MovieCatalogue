package com.movie.moviecatalog.popular

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.moviecatalog.core.data.Resource
import com.movie.moviecatalog.core.ui.MovieAdapter
import com.movie.moviecatalog.databinding.FragmentPopularBinding
import com.movie.moviecatalog.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {

    private val popularViewModel: PopularViewModel by viewModel()
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { movie ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE, movie)
                startActivity(intent)
            }

            popularViewModel.movies.observe(viewLifecycleOwner) { movie ->
                when (movie) {
                    is Resource.Loading -> binding.progresPbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progresPbar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progresPbar.visibility = View.GONE
                        Log.i("ADAPTER", "onViewCreated: ")
                    }
                }
            }

            with(binding.rvPopular) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}