package com.example.moksh.academichelplerdemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView


class WeekActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week)

        setupUIViews()
        initToolbar()
        setUpListView()
    }

    private fun setupUIViews() {
        toolbar = findViewById(R.id.toolbarWeek)
        listView = findViewById(R.id.lv_week)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Days")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun setUpListView() {
        var week: Array<String> = resources.getStringArray(R.array.Week)
        listView?.adapter = WeekAdapter(this, R.layout.activity_week_single_item, week)

        listView?.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                }
                1 -> {
                }
                2 -> {
                }
                3 -> {
                }
                4 -> {
                }
                else -> {
                }

            }
        }
    }

    class WeekAdapter(context: Context, resource: Int, objects: Array<String>)
        : BaseAdapter() {

        private var resource: Int? = null
        private var layoutInflater: LayoutInflater? = null
        private var week: Array<String>? = null


        init {
            this.resource = resource
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            week = objects
        }

        internal class ViewHolder {
            var letterImageView: LetterImageView? = null
            var tvWeekDay: TextView? = null


        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var holder: ViewHolder

            var retView = convertView
            if (retView == null) {
                holder = ViewHolder()
                retView = layoutInflater?.inflate(resource!!, null)
                holder.letterImageView = retView?.findViewById(R.id.livWeek)
                holder.tvWeekDay = retView?.findViewById(R.id.week_text_view)

                retView?.tag = holder
            } else {
                holder = retView?.getTag() as ViewHolder
            }
            holder.letterImageView?.isOval = true
            holder.letterImageView?.letter = week!![position].get(0)
            holder.tvWeekDay?.text = week!![position]



            return retView!!
        }

        override fun getItem(position: Int): Any {
            return week!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()

        }

        override fun getCount(): Int {
            return week!!.size
        }


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
