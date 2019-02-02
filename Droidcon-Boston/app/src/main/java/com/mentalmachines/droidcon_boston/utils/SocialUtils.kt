package com.mentalmachines.droidcon_boston.utils

/**
 * A utility class used for building URIs relevant to social media.
 */
object SocialUtils {
    private const val BASE_TWITTER_URL = "https://twitter.com"
    private const val BASE_LINKEDIN_URL = "https://www.linkedin.com/in"

    fun getTwitterUriForHandle(twitterHandle: String): String {
        return "$BASE_TWITTER_URL/$twitterHandle"
    }

    fun getLinkedInUriForHandle(linkedInHandle: String): String {
        return "$BASE_LINKEDIN_URL/$linkedInHandle"
    }
}