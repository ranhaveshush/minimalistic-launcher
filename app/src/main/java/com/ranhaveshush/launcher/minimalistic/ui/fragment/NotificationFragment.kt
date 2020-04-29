package com.ranhaveshush.launcher.minimalistic.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.ranhaveshush.launcher.minimalistic.R
import com.ranhaveshush.launcher.minimalistic.databinding.FragmentNotificationsBinding
import com.ranhaveshush.launcher.minimalistic.ui.adapter.NotificationsAdapter
import com.ranhaveshush.launcher.minimalistic.ui.listener.NotificationItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.recyclerview.OnSwipedItemListener
import com.ranhaveshush.launcher.minimalistic.ui.recyclerview.SwipedItemTouchCallback
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import com.ranhaveshush.launcher.minimalistic.viewmodel.NotificationViewModel
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource

class NotificationFragment : Fragment(R.layout.fragment_notifications), NotificationItemClickListener, OnSwipedItemListener {
    private val viewModel: NotificationViewModel by viewModels {
        InjectorUtils.provideNotificationViewModelFactory(requireContext().applicationContext)
    }

    private val notificationsAdapter = NotificationsAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNotificationsBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        binding.buttonNotify.setOnClickListener {
            val context = requireContext()
            val notification = NotificationCompat.Builder(context, "test")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Content title.")
                .setContentText("Content text.")
                .setSubText("Sub text.")
                .setColor(Color.RED)
                .setColorized(true)
                .setWhen(System.currentTimeMillis())
                .setTimeoutAfter(System.currentTimeMillis() + 10000)
                .build()

            NotificationManagerCompat.from(context).notify(12345, notification)
        }

        binding.recyclerViewNotifications.adapter = notificationsAdapter

        val itemTouchHelper = ItemTouchHelper(SwipedItemTouchCallback(ItemTouchHelper.END, this))
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewNotifications)

        viewModel.notifications.observe(viewLifecycleOwner, Observer {
            if (it.state.status == Resource.Status.SUCCESS) {
                notificationsAdapter.submitList(it.data)
            }
        })

        return binding.root
    }

    override fun onNotificationClick(notificationItem: NotificationItem) = viewModel.launch(notificationItem)

    override fun onSwiped(position: Int) {
        val notificationItem = notificationsAdapter.getNotificationItem(position)
        notificationItem?.let {
            viewModel.remove(notificationItem)
        }
    }
}