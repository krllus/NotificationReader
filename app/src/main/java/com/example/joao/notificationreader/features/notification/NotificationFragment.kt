package com.example.joao.notificationreader.features.notification

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import com.example.joao.notificationreader.R
import com.example.joao.notificationreader.commons.extensions.inflate
import com.example.joao.notificationreader.features.notification.adapter.NotificationAdapter
import com.example.joao.notificationreader.features.notification.model.ContentManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_notification.*

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
class NotificationFragment : Fragment() {

    private val notificationManager by lazy { ContentManager() }

    companion object {
        fun newInstance(): NotificationFragment {
            return NotificationFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_notification)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if (savedInstanceState == null)
            loadData()
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.menu_main_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                loadData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initAdapter() {
        if (recycler_view.adapter == null)
            recycler_view.adapter = NotificationAdapter()
    }

    private fun loadData() {
        val subscriptionNormal = notificationManager.getNotifications(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ retrievedElements ->
                (recycler_view.adapter as NotificationAdapter).updateElements(retrievedElements)
            }, { error ->
                Toast.makeText(context, "error ${error.message}", Toast.LENGTH_SHORT)
                    .show()
            })
    }


}