package com.example.acordianmenu

import android.graphics.PorterDuff
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginRight
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acordianmenu.databinding.ContainerGoalItemRecyclerviewBinding

class BigGoalItemAdapter(
    private val bigGoalList: ArrayList<BigGoalItem>,
    private val detailGoalList: ArrayList<DetailGoalItem>
) : RecyclerView.Adapter<BigGoalItemAdapter.BigGoalHolder>() {

    // context 변수
    val context = MyApplication.applicationContext()

    // 세부목표 어댑터
    private lateinit var detailGoalAdapter: DetailGoalItemAdapter

    // 대표목표 박스 객체의 클릭 상태를 저장할 array객체
    private var selectedItems: SparseBooleanArray = SparseBooleanArray()

    // 직전에 클릭했던 대표목표 박스 객체의 position
    private var prePosition: Int = -1

    inner class BigGoalHolder(
        val binding: ContainerGoalItemRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bigGoalItem: BigGoalItem) {
            val color = binding.goalItemColorImageVIew
            val title = binding.goalItemBigGoalTextView
            val iconsLayout = binding.goalItemIconLayout
            val openBtn = binding.goalItemOpenButton
            val bigGoalLayout = binding.goalItemBigGoalLayout
            val detailGoalLayout = binding.goalItemDetailGoalLayout

            // 대표목표 색상값을 color값(int)으로 저장
            var iconColor: Int = R.color.Purple
            when (bigGoalItem.color) {
                "Orange" -> iconColor = R.color.Orange
                "BrightYellow" -> iconColor = R.color.BrightYellow
                "Yellow" -> iconColor = R.color.Yellow
                "Apricot" -> iconColor = R.color.Apricot
                "Orange" -> iconColor = R.color.Orange
                "DarkBrown" -> iconColor = R.color.DarkBrown
                "SeedBrown" -> iconColor = R.color.SeedBrown
                "NoteYellow" -> iconColor = R.color.NoteYellow
                "LightGreen" -> iconColor = R.color.LightGreen
                "Green" -> iconColor = R.color.Green
                "LightBlue" -> iconColor = R.color.LightBlue
                "Blue" -> iconColor = R.color.Blue
                "Purple" -> iconColor = R.color.Purple
                "Pink" -> iconColor = R.color.Pink
            }

            // 아이콘 리스트
            var iconList: ArrayList<String> = bigGoalItem.icons

            // 아이콘 레이아웃 만들기 (아이콘 사이즈, 아이콘의 마진값)
            val iconLayoutParams = LinearLayout.LayoutParams(
                48,
                48
            )
            iconLayoutParams.setMargins(0,0,32,0)

            // 아이콘 레이아웃에 아이콘&아이콘 색상&레이아웃 세팅 적용
            for (i in 0 until iconList.size) {
                // 아이콘이 6개 이상일 경우, ... 아이콘 보이기
                if (i == 6) {
                    val moreIcon = ImageView(context)
                    moreIcon.setImageResource(R.drawable.ic_more_goals)
                    moreIcon.setColorFilter(ContextCompat.getColor(context, iconColor))
                    val moreIconLayoutParams = LinearLayout.LayoutParams(
                        48,
                        48
                    )
                    moreIconLayoutParams.setMargins(-8, 0, 0, 0)
                    moreIcon.layoutParams = moreIconLayoutParams
                    iconsLayout.addView(moreIcon)
                    break
                }
                val iconId = context.resources.getIdentifier(iconList[i], "drawable", context.packageName)
                val icon = ImageView(context) // 동적 이미지 객체 생성
                icon.setImageResource(iconId)
                icon.layoutParams = iconLayoutParams
                icon.setColorFilter(ContextCompat.getColor(context, iconColor))

                iconsLayout.addView(icon)
            }

            color.setColorFilter(ContextCompat.getColor(context, iconColor)) // 대표목표 색상 적용
            title.text = bigGoalItem.title // 대표목표 텍스트 적용


            // 대표목표 박스 클릭 이벤트
            bigGoalLayout.setOnClickListener {
                val show = toggleLayout(!bigGoalItem.isExpanded, openBtn, detailGoalLayout)
                bigGoalItem.isExpanded = show
            }
        }

        // isExpanded의 여부에 따라서 화살표 회전
        private fun toggleLayout(isExpanded: Boolean, view: View, detailGoalLayout: LinearLayout): Boolean {
            ToggleAnimation.toggleArrow(view, isExpanded)

            if (isExpanded) {
                ToggleAnimation.expand(detailGoalLayout)
            }
            else {
                ToggleAnimation.collapse(detailGoalLayout)
            }

            return isExpanded
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BigGoalItemAdapter.BigGoalHolder {
        val binding: ContainerGoalItemRecyclerviewBinding  = ContainerGoalItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BigGoalHolder(binding)
    }

    override fun onBindViewHolder(holder: BigGoalHolder, position: Int) {

        // 세부목표 어댑터 연결 및 세부목표 데이터 전송
        holder.binding.goalItemDetailGoalRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        detailGoalAdapter = DetailGoalItemAdapter(detailGoalList)
        holder.binding.goalItemDetailGoalRecyclerView.adapter = detailGoalAdapter
        holder.bind(bigGoalList[position])
    }

    // 대표목표 리스트 사이즈 반환
    override fun getItemCount(): Int = bigGoalList.size
}