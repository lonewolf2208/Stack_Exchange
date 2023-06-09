package com.example.stackexchange.model.HomeData

data class Item(
    val accepted_answer_id: Int,
    val answer_count: Int,
    val bounty_amount: Int,
    val bounty_closes_date: Int,
    val closed_date: Int,
    val closed_reason: String,
    val content_license: String,
    val creation_date: Int,
    val is_answered: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: Owner,
    val protected_date: Int,
    val question_id: Int,
    val score: Int,
    val tags: ArrayList<String>,
    val title: String,
    val view_count: Int
)