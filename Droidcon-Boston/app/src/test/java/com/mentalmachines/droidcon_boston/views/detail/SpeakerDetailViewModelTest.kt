package com.mentalmachines.droidcon_boston.views.detail

import com.mentalmachines.droidcon_boston.data.FirebaseDatabase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class SpeakerDetailViewModelTest {
    private val speakerName = "Speaker Name"
    private val speakerPicture = "Speaker Picture"
    private val speakerPosition = "Software Dev"
    private val speakerOrg = "Speaker Org"
    private val speakerBio = "Speaker Bio"
    private val twitterHandle = "Twitter Handle"
    private val linkedInHandle = "LinkedIn Handle"

    private val testSpeaker = FirebaseDatabase.EventSpeaker(
        name = speakerName,
        bio = speakerBio,
        title = speakerPosition,
        org = speakerOrg,
        pictureUrl = speakerPicture,
        socialProfiles = hashMapOf("twitter" to twitterHandle, "linkedIn" to linkedInHandle)
    )

    private val viewModel = SpeakerDetailViewModel(testSpeaker)

    @Test
    fun getSpeakerName() {
        assertEquals(speakerName, viewModel.speakerName)
    }

    @Test
    fun getSpeakerPicture() {
        assertEquals(speakerPicture, viewModel.speakerPicture)
    }

    @Test
    fun getSpeakerPositionDetails() {
        val expectedResult = "$speakerPosition \n@ $speakerOrg"
        assertEquals(expectedResult, viewModel.speakerPositionDetails)
    }

    @Test
    fun getTwitterUri() {
        val expected = "https://twitter.com/$twitterHandle"
        assertEquals(expected, viewModel.twitterUri)
    }

    @Test
    fun getShowTwitterHandle() {
        assertEquals(true, viewModel.showTwitterHandle)

        val viewModelWithoutHandle = SpeakerDetailViewModel(FirebaseDatabase.EventSpeaker())
        assertFalse(viewModelWithoutHandle.showTwitterHandle)
    }

    @Test
    fun getLinkedInUri() {
        val expected = "https://www.linkedin.com/in/$linkedInHandle"
        assertEquals(expected, viewModel.linkedInUri)
    }

    @Test
    fun getShowLinkedInHandle() {
        assertEquals(true, viewModel.showLinkedInHandle)

        val viewModelWithoutHandle = SpeakerDetailViewModel(FirebaseDatabase.EventSpeaker())
        assertFalse(viewModelWithoutHandle.showLinkedInHandle)
    }
}