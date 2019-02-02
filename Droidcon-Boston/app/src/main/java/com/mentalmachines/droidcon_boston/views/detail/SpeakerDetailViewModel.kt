package com.mentalmachines.droidcon_boston.views.detail

import android.text.Spanned
import androidx.lifecycle.ViewModel
import com.mentalmachines.droidcon_boston.data.FirebaseDatabase
import com.mentalmachines.droidcon_boston.utils.SocialUtils
import com.mentalmachines.droidcon_boston.utils.getHtmlFormattedSpanned

class SpeakerDetailViewModel(private val eventSpeaker: FirebaseDatabase.EventSpeaker) : ViewModel() {

    val speakerName: String
        get() = eventSpeaker.name

    val speakerPicture: String
        get() = eventSpeaker.pictureUrl

    val speakerPositionDetails: String
        get() = "${eventSpeaker.title} \n@ ${eventSpeaker.org}"

    val speakerBio: Spanned
        get() = eventSpeaker.bio.getHtmlFormattedSpanned()

    private val twitterHandle: String?
        get() = eventSpeaker.socialProfiles?.get("twitter")

    val showTwitterHandle: Boolean
        get() = !twitterHandle.isNullOrEmpty()

    val twitterUri: String
        get() = SocialUtils.getTwitterUriForHandle(twitterHandle.orEmpty())

    private val linkedInHandle: String?
        get() = eventSpeaker.socialProfiles?.get("linkedIn")

    val showLinkedInHandle: Boolean
        get() = !linkedInHandle.isNullOrEmpty()

    val linkedInUri: String
        get() = SocialUtils.getLinkedInUriForHandle(linkedInHandle.orEmpty())
}