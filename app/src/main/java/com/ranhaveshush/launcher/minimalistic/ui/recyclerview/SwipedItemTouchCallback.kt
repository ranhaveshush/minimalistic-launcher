package com.ranhaveshush.launcher.minimalistic.ui.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * A [ItemTouchHelper.SimpleCallback] implementation to detect swiped items.
 */
class SwipedItemTouchCallback(
    swipedDirs: Int,
    private val listener: OnSwipedItemListener
) : ItemTouchHelper.SimpleCallback(0, swipedDirs) {
    override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return if ((viewHolder is SwipeableViewHolder) && viewHolder.isSwipeable) {
            return super.getSwipeDirs(recyclerView, viewHolder)
        } else {
            0
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // Do nothing.
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onSwiped(viewHolder.adapterPosition)
    }
}
