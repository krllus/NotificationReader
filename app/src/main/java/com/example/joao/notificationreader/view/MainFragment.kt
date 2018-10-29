package com.example.joao.notificationreader.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.joao.notificationreader.R
import com.example.joao.notificationreader.adapter.NotificationRecyclerViewAdapter
import com.example.joao.notificationreader.model.NotificationData
import com.example.joao.notificationreader.model.NotificationDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*


/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationRecyclerViewAdapter

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = NotificationRecyclerViewAdapter()
        recyclerView.adapter = adapter

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
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

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val database = NotificationDatabase.getInstance(context!!)
        val dao = database!!.notificationDao()

        val compositeDisposable = CompositeDisposable()

        val disposable: Disposable =

            dao.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result -> displayResult(result) }
        compositeDisposable.add(disposable)

        compositeDisposable.dispose()
    }

    private fun displayResult(result: List<NotificationData>?) {
        adapter.updateElements(result!!)
    }
}