package com.example.acordianmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acordianmenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null // binding변수
    private val binding get() = mBinding!!

    private var bigGoalList = ArrayList<BigGoalItem>() // 대표목표 리스트
    private var detailGoalList = ArrayList<DetailGoalItem>() // 세부목표 리스트
    private lateinit var bigGoalAdapter: BigGoalItemAdapter // 대표목표 어댑터
    private lateinit var goalBig_RecyclerView: RecyclerView // 아코디언 메뉴 리사이클러뷰

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goalBig_RecyclerView = binding.goalBigRecyclerView // 메인xml에 있는 대표목표 리사이클러뷰

        // 대표목표&세부목표 데이터 저장
        bigGoalList = loadBigGoalItems()
        detailGoalList = loadDetailGoalItems()

        // 대표목표 어댑터 연결
        goalBig_RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bigGoalAdapter = BigGoalItemAdapter(bigGoalList, detailGoalList)
        goalBig_RecyclerView.adapter = bigGoalAdapter
    }

    override fun onDestroy() {
        // binding class 인스턴트 참조 정리
        mBinding = null

        super.onDestroy()
    }

    // 세부목표 데이터 저장
    private fun loadDetailGoalItems(): ArrayList<DetailGoalItem> {

        detailGoalList.add(DetailGoalItem("ic_computer_black_24", "세부목표1", "Apricot"))
        detailGoalList.add(DetailGoalItem("ic_book_black_24", "세부목표2", "Apricot"))
        detailGoalList.add(DetailGoalItem("ic_book_black_24", "세부목표3", "Apricot"))
        detailGoalList.add(DetailGoalItem("ic_book_black_24", "세부목표4", "Apricot"))
        detailGoalList.add(DetailGoalItem("ic_book_black_24", "세부목표5", "Apricot"))
        return detailGoalList
    }

    // 대표 목표 데이터 저장
    private fun loadBigGoalItems(): ArrayList<BigGoalItem> {
        var iconList = ArrayList<String>() // 대표목표 아이콘 리스트
        iconList.add("ic_computer_black_24") // 아이콘 추가
        iconList.add("ic_book_black_24")
        iconList.add("ic_book_black_24")
        iconList.add("ic_book_black_24")
        iconList.add("ic_book_black_24")

        bigGoalList.add(BigGoalItem("Apricot", "임시예제1", iconList, false))
        bigGoalList.add(BigGoalItem("Apricot", "임시예제2", iconList, false))

        return bigGoalList
    }
}