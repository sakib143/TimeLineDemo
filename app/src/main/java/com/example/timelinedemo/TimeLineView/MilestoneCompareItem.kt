package com.example.timelinedemo.TimeLineView

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timelinedemo.R
import com.example.timelineview.MilestoneCompareStatus
import com.example.timelineview.ThemeUtils
import com.example.timelineview.VectorDrawableUtils
import com.tcqq.timelineview.TimelineView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder

data class MilestoneCompareItem(
        val id: String,
        val title: String,
        val status: MilestoneCompareStatus
) : AbstractFlexibleItem<MilestoneCompareItem.ViewHolder>() {

    override fun getLayoutRes(): Int {
        return R.layout.adapter_timeline_view
    }

    override fun createViewHolder(
            view: View,
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun bindViewHolder(
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
            holder: ViewHolder,
            position: Int,
            payloads: MutableList<Any>
    ) {
        holder.title.text = title
        holder.timeline.initLine(TimelineView.getTimeLineViewType(position, adapter.itemCount))
        when (status) {
            MilestoneCompareStatus.COMPLETED -> setStatus(
                    holder.timeline,
                    R.drawable.ic_check_circle_black_24dp,
                    holder.status,
                    R.string.completed
            )
            MilestoneCompareStatus.REQUEST_TO_MODIFY_THE_CONTRACT -> setStatus(
                    holder.timeline,
                    R.drawable.ic_radio_button_checked_black_24dp,
                    holder.status,
                    R.string.request_to_modify_the_contract
            )
            MilestoneCompareStatus.INACTIVE -> setStatus(
                    holder.timeline,
                    R.drawable.ic_radio_button_unchecked_black_24dp,
                    holder.status,
                    R.string.inactive
            )
        }
    }

    private fun setStatus(
            timeline: TimelineView, @DrawableRes iconRes: Int,
            statusTextView: AppCompatTextView, @StringRes statusTextRes: Int
    ) {
        val context = timeline.context
        statusTextView.text = context.getString(statusTextRes)
        timeline.marker = VectorDrawableUtils.getDrawable(
                context,
                iconRes,
                ThemeUtils.getThemeValue(R.attr.colorSecondary, context)
        )
    }

    class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {
        var timeline: TimelineView = view.findViewById(R.id.timeline)
        var title: AppCompatTextView = view.findViewById(R.id.title)
        var status: AppCompatTextView = view.findViewById(R.id.status)
    }
}