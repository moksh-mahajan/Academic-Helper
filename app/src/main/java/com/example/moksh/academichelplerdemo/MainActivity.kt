package com.example.moksh.academichelplerdemo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var toolBar: Toolbar? = null
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUIViews()
        initToolbar()
        setUpListView()

    }

    /*To reference all the XML views*/
    private fun setupUIViews() {
        toolBar = findViewById(R.id.toolbarMain)
        listView = findViewById(R.id.lv_main)
    }

    /*To initialise Toolbar and set up its properties */
    private fun initToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.title = "Academic Helper"
    }

   private fun setUpListView() {
       var title: Array<String> = resources.getStringArray(R.array.Main)
       var description: Array<String> = resources.getStringArray(R.array.Description)

       listView?.adapter = MainAdapter(this, title, description)
       listView?.setOnItemClickListener { parent, view, position, id ->
           when(position){
               0 ->{
                   startActivity(Intent(this@MainActivity, WeekActivity::class.java))

               }
               1 ->{}
               2 ->{}
               3 ->{}
               else ->{}
           }

       }
   }
    class MainAdapter(context: Context, title: Array<String>, description: Array<String>) : BaseAdapter() {


        private var mContext: Context? = null
        private var layoutInflater: LayoutInflater? = null
        private var title: TextView? = null
        private var description: TextView? = null
        private var titleArray: Array<String>? = null
        private var descriptionArray: Array<String>? = null
        private var imageView: ImageView? = null

        init {
            mContext = context
            titleArray = title
            descriptionArray = description
            layoutInflater = LayoutInflater.from(context)
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var retView = convertView
            if (retView == null)
                retView = layoutInflater!!.inflate(R.layout.main_activity_single_item_layout, null)
            title = retView?.findViewById(R.id.main_text_view)
            description = retView?.findViewById(R.id.main_tv_description)
            imageView = retView?.findViewById(R.id.main_item_image)

            title?.text = titleArray!![position]
            description?.text = descriptionArray!![position]

            if (titleArray!![position].equals("Timetable", true))
                imageView?.setImageResource(R.drawable.timetable)
            else if (titleArray!![position].equals("Subject", true))
                imageView?.setImageResource(R.drawable.book)
            else if (titleArray!![position].equals("Faculty", true))
                imageView?.setImageResource(R.drawable.play)
            else
                imageView?.setImageResource(R.drawable.settings)

            return retView!!

        }

        override fun getItem(position: Int): Any {
            return titleArray!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return titleArray!!.size
        }

    }
}
