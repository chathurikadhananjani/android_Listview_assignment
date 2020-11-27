package com.c2c20.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit= Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).build()

        val api=retrofit.create(AppService::class.java)
        api.fetchAllUsers().enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                d("daniel", "onResponse: ${response.body()!![0].phone}")
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

                d("daniel","onFailure")

            }

        }
                recycler_view.adapter = ExampleAdapter(exampleList)
                recycler_view.LayoutManager = LinearLayoutManager(this)
                recycler_view.setHasFixedSize(true)


    }
    val exampleList = generateDummyList(500)



    private fun generateDummyList(size: Int):List<ExampleItem>{
        val list = ArrayList<ExampleItem>()

        for (i in 0 until size){
            val drawable = when (i % 3){
                0 -> R.drawable.ic_android
                1-> R.drawable.ic_audio
                else-> R.drawable.ic_sun
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }

}