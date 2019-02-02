package com.mentalmachines.droidcon_boston.utils

import org.junit.Test

import org.junit.Assert.*

class SocialUtilsTest {

    @Test
    fun getTwitterUriForHandle() {
        val testHandle = "TestHandle"

        val expected = "https://twitter.com/$testHandle"
        val actual = SocialUtils.getTwitterUriForHandle(testHandle)
        assertEquals(expected, actual)
    }

    @Test
    fun getLinkedInUriForHandle() {
        val testHandle = "TestHandle"

        val expected = "https://www.linkedin.com/in/$testHandle"
        val actual = SocialUtils.getLinkedInUriForHandle(testHandle)
        assertEquals(expected, actual)
    }
}