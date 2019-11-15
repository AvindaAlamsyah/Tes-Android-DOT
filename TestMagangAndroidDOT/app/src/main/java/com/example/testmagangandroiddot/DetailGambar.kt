package com.example.testmagangandroiddot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.testmagangandroiddot.databinding.ActivityDetailGambarBinding

class DetailGambar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailGambarBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail_gambar)

        binding.gambar = intent.getParcelableExtra("detail") as Gambar

        /*
        setContentView(R.layout.activity_detail_gambar)

        val parcel = intent.getParcelableExtra("detail") as Gambar

        deskripsi.text = parcel.caption

        Glide.with(this)
            .load(parcel.image)
            .error(R.drawable.error)
            .placeholder(R.drawable.placeholder)
            .into(detail_gmbr)

         */
    }

}
