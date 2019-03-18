package com.mentalmachines.droidcon_boston.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.mentalmachines.droidcon_boston.modal.Tweet

@Dao
abstract class TwitterDao : BaseDao<Tweet> {

    @Transaction
    open fun updateTweets(tweets: List<Tweet>) {
        deleteAll()
        insertAll(tweets)
    }

    @Query("SELECT * FROM Tweet ORDER BY createdAt DESC")
    abstract fun getTweets(): List<Tweet>

    @Query("DELETE FROM Tweet")
    abstract fun deleteAll()
}
