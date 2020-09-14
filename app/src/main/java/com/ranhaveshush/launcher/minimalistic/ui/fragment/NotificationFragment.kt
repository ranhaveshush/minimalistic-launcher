package com.ranhaveshush.launcher.minimalistic.ui.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
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
import com.ranhaveshush.launcher.minimalistic.ui.listener.ClearAllNotificationsClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.NotificationItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.recyclerview.OnSwipedItemListener
import com.ranhaveshush.launcher.minimalistic.ui.recyclerview.SwipedItemTouchCallback
import com.ranhaveshush.launcher.minimalistic.viewmodel.NotificationViewModel
import com.ranhaveshush.launcher.minimalistic.vo.Notification
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

private const val MAX_RANDOM_NUMBER = 100

@AndroidEntryPoint
class NotificationFragment : Fragment(R.layout.fragment_notifications), NotificationItemClickListener,
    OnSwipedItemListener,
    ClearAllNotificationsClickListener {
    private val viewModel: NotificationViewModel by viewModels()

    private val notificationsAdapter = NotificationsAdapter(this, this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNotificationsBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        binding.buttonNotify.setOnClickListener {
            val context = requireContext()
            val notificationManager = NotificationManagerCompat.from(context)
            val channelId = "test"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel =
                    NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(notificationChannel)
            }

            val secondInMillis = TimeUnit.SECONDS.toMillis(1)

            val notification = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Content title.")
                .setContentText("Content text.")
                .setSubText("Sub text.")
                .setColor(Color.RED)
                .setColorized(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setWhen(System.currentTimeMillis())
                .setTimeoutAfter(System.currentTimeMillis() + secondInMillis)
                .build()

            // TODO: change random id logic to consistent id logic.
            val randomId = (Math.random() * MAX_RANDOM_NUMBER).toInt()
            notificationManager.notify(randomId, notification)
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

    override fun onNotificationClick(notification: Notification) = viewModel.launch(notification)

    override fun onSwiped(position: Int) {
        val notificationItem = notificationsAdapter.getNotificationItem(position)
        notificationItem?.let {
            viewModel.remove(notificationItem)
        }
    }

    override fun onClearAllClick() {
        viewModel.clearAll()
    }
}
