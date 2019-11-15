package com.example.testmagangandroiddot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapters: Adapters
    private lateinit var viewModels: ViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        val layoutManager = GridLayoutManager(this,3)
        recyclerView = findViewById(R.id.rv_images)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        imageGalleryAdapter = ImageGalleryAdapter(this, SunsetPhoto.getSunsetPhotos())
        */

        tampilRecycler()

        viewModels = ViewModelProviders.of(this).get(ViewModels::class.java)

        viewModels.getGambarku().observe(this, Observer { listData ->
            if (listData != null){
                Log.d("response", listData.toString())
                adapters.setData(listData)
            }
        })

        viewModels.setData()

    }

    private fun tampilRecycler(){
        adapters = Adapters()
        adapters.notifyDataSetChanged()

        rv_images.layoutManager = StaggeredGridLayoutManager(
            3,
            1
        )
        rv_images.adapter = adapters

        adapters.setOnItemClickCallback(object : Adapters.OnItemClickCallback{
            override fun onItemClicked(data: Gambar) {
                val parcel = Gambar(data.caption, data.thumbnail, data.image)
                val intent = Intent(this@MainActivity, DetailGambar::class.java)

                intent.putExtra("detail",parcel)
                startActivity(intent)
            }
        })
    }

}

