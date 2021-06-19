package com.movie.moviecatalog.upcoming

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.movie.moviecatalog.core.data.Resource
import com.movie.moviecatalog.core.ui.MovieAdapter
import com.movie.moviecatalog.databinding.FragmentUpcomingBinding
import com.movie.moviecatalog.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private val upcomingViewModel: UpcomingViewModel by viewModel()
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { upcoming ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE, upcoming)
                startActivity(intent)
            }

            upcomingViewModel.upcomings.observe(viewLifecycleOwner) { movie ->
                when (movie) {
                    is Resource.Loading -> binding.progresUbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progresUbar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progresUbar.visibility = View.GONE
                        Log.i("ADAPTER", "onViewCreated: ")
                    }
                }
            }

            with(binding.rvUpcoming) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
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