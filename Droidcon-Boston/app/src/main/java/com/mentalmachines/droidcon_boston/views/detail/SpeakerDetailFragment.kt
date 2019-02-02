package com.mentalmachines.droidcon_boston.views.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mentalmachines.droidcon_boston.R
import com.mentalmachines.droidcon_boston.data.FirebaseDatabase.EventSpeaker
import com.mentalmachines.droidcon_boston.utils.ServiceLocator.Companion.gson
import com.mentalmachines.droidcon_boston.utils.loadUriInCustomTab
import com.mentalmachines.droidcon_boston.views.MainActivity
import com.mentalmachines.droidcon_boston.views.transform.CircleTransform
import kotlinx.android.synthetic.main.speaker_detail_fragment.*

class SpeakerDetailFragment : Fragment() {

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val eventSpeaker = gson.fromJson(
                arguments?.getString(EventSpeaker.SPEAKER_ITEM_ROW),
                EventSpeaker::class.java
            )

            @Suppress("UNCHECKED_CAST")
            return SpeakerDetailViewModel(eventSpeaker) as T
        }
    }

    private lateinit var viewModel: SpeakerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.speaker_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
        populateView()

        (activity as? MainActivity)?.uncheckAllMenuItems()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SpeakerDetailViewModel::class.java)
    }

    private fun populateView() {
        tv_speaker_detail_name.text = viewModel.speakerName
        tv_speaker_detail_designation.text = viewModel.speakerPositionDetails
        tv_speaker_detail_description.text = viewModel.speakerBio

        tv_speaker_detail_description.movementMethod = LinkMovementMethod.getInstance()

        imgv_twitter.visibility = if (viewModel.showTwitterHandle) View.VISIBLE else View.GONE
        if (viewModel.showTwitterHandle) {
            imgv_twitter.setOnClickListener {
                activity?.loadUriInCustomTab(viewModel.twitterUri)
            }
        }

        imgv_linkedin.visibility = if (viewModel.showLinkedInHandle) View.VISIBLE else View.GONE
        if (viewModel.showLinkedInHandle) {
            imgv_linkedin.setOnClickListener {
                activity?.loadUriInCustomTab(viewModel.linkedInUri)
            }
        }

        Glide.with(activity)
            .load(viewModel.speakerPicture)
            .transform(CircleTransform(imgv_speaker_detail_avatar.context))
            .placeholder(R.drawable.emo_im_cool)
            .crossFade()
            .into(imgv_speaker_detail_avatar)
    }
}
