package com.example.acordianmenu

data class BigGoalItem(
    var color: String, // 색상
    var title: String, // 대표목표
    var icons: ArrayList<String>, // 아이콘 리스트
    var isExpanded: Boolean = false // 확장상태 여부
)
