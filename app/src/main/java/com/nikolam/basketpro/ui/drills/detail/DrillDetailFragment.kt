package com.nikolam.basketpro.ui.drills.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.nikolam.basketpro.databinding.DrillDetailFragmentBinding
import com.nikolam.basketpro.ui.drills.list.DrillsListFragmentArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.koin.androidx.viewmodel.ext.android.viewModel


class DrillDetailFragment : Fragment() {

    private lateinit var viewDataBinding: DrillDetailFragmentBinding

    private val drillsDetailviewModel by viewModel<DrillDetailViewModel>()

    lateinit var youtubePlayer : YouTubePlayerView

    val args: DrillDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DrillDetailFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = drillsDetailviewModel
        }
        viewDataBinding.lifecycleOwner = this


        youtubePlayer = viewDataBinding.youtubePlayerView
        lifecycle.addObserver(youtubePlayer)

        observeLiveData()

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drillsDetailviewModel.getDrillDetails(args.DrillID)
    }

    fun observeLiveData(){

        drillsDetailviewModel.getPlayVideoEvent().observe(viewLifecycleOwner, Observer{

            playVideo(it)

        })

    }

    fun playVideo(video_id : String){
        youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = video_id
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}
