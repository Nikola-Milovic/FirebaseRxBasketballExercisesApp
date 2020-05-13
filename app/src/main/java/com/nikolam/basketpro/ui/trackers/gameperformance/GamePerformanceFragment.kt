package com.nikolam.basketpro.ui.trackers.gameperformance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nikolam.basketpro.R

class GamePerformanceFragment : Fragment() {

    companion object {
        fun newInstance() =
            GamePerformanceFragment()
    }

    private lateinit var viewModel: GamePerformanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_performance_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GamePerformanceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
