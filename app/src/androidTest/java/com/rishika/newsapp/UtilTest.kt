package com.rishika.newsapp

import com.rishika.newsapp.data.News
import java.time.Instant
import java.util.*

/**
 * Created by Rishika on 23/05/20.
 */
object UtilTest {

    val newsResponse = News(
        1,
        "Allen Kim, CNN",
        "NASA is looking for participants to isolate in a Russian lab for 8 months - CNN",
        "With people across the globe already in social isolation because of the coronavirus pandemic, why not get paid to do it?",
        "https://www.cnn.com/2020/05/23/us/nasa-isolation-scn-trnd/index.html",
        "https://cdn.cnn.com/cnnnext/dam/assets/200521153014-01-nasa-isolation-mission-super-tease.jpg",
        Date.from(Instant.parse("2020-02-16T02:45:00Z")), "(CNN)With people across the globe already in social isolation because of the coronavirus pandemic, why not get paid to do it?\\r\\nNASA is seeking US citizens for an eight-month study on social isolation… [+793 chars]")
}