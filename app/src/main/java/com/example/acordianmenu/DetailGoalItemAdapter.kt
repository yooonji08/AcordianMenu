package com.example.acordianmenu

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.acordianmenu.databinding.ContainerDetailGoalItemRecyclerviewBinding

class DetailGoalItemAdapter(
    private val detailGoalList: ArrayList<DetailGoalItem>
) : RecyclerView.Adapter<DetailGoalItemAdapter.DetailGoalHolder>() {

    // context 변수
    val context = MyApplication.applicationContext()

    inner class DetailGoalHolder(
        val binding: ContainerDetailGoalItemRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind (detailGoalItem: DetailGoalItem) {
            val detailIcon = binding.detailGoalItemIcon
            val detailTitle = binding.detailGoalItemDetailGoalTextView

            // 세부목표 icon 색상 저장
            var iconColor: Int = 0
            when (detailGoalItem.bigColor) {
                "Orange" -> R.color.Orange
                "BrightYellow" -> R.color.BrightYellow
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

            // 세부목표 아이콘 이미지 및 색상 적용
            val iconId = context.resources.getIdentifier(detailGoalItem.detailIcon, "drawable", context.packageName)
            detailIcon.setImageResource(iconId)
            detailIcon.setColorFilter(ContextCompat.getColor(context, iconColor))

            // 세부목표 텍스트 적용
            detailTitle.text = detailGoalItem.detailTitle
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailGoalHolder {
        val binding: ContainerDetailGoalItemRecyclerviewBinding = ContainerDetailGoalItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DetailGoalHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailGoalHolder, position: Int) {
        holder.bind(detailGoalList[position])
    }

    // 세부목표 리스트 사이즈 반환
    override fun getItemCount(): Int = detailGoalList.size
}