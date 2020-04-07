package com.example.timelinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timelinedemo.TimeLineView.MilestoneCompareItem
import com.example.timelineview.MilestoneCompareStatus
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import kotlinx.android.synthetic.main.activity_time_line.*
import java.util.ArrayList

class TimeLineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        val adapter: FlexibleAdapter<IFlexible<*>> = FlexibleAdapter(getItems(), this, true)
        milestones_list.layoutManager = SmoothScrollLinearLayoutManager(this)
        milestones_list.adapter = adapter
        milestones_list.setHasFixedSize(true)

    }

    private fun getItems(): List<AbstractFlexibleItem<*>> {
        val items = ArrayList<AbstractFlexibleItem<*>>()
        items.add(MilestoneCompareItem("1", "UI design", MilestoneCompareStatus.COMPLETED))
        items.add(
                MilestoneCompareItem(
                        "2",
                        "Android development",
                        MilestoneCompareStatus.REQUEST_TO_MODIFY_THE_CONTRACT
                )
        )
        items.add(MilestoneCompareItem("3", "Server development", MilestoneCompareStatus.INACTIVE))
        return items
    }
}
