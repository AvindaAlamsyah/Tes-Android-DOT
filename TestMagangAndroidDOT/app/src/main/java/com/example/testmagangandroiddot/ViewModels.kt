package com.example.testmagangandroiddot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject


class ViewModels : ViewModel() {

    val listGambarku = MutableLiveData<ArrayList<Gambar>>()

    internal fun setData(){

        val client = AsyncHttpClient()
        val listGambar = ArrayList<Gambar>()
        val url = "https://info-malang-batu.firebaseapp.com/Gallery_Malang_Batu.json"

        client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try{
                    Log.d("JSON", String(responseBody))

                    val jsonArray = JSONArray(String(responseBody))
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        Log.d("JSON OBJECT", jsonObject.getString("caption"))

                        val detailGambar = Gambar(jsonObject.getString("caption"),
                            jsonObject.getString("thumbnail"),
                            jsonObject.getString("image"))

                        listGambar.add(detailGambar)

                    }
                    listGambarku.postValue(listGambar)
                }catch (e:Exception){
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                if (error != null) {
                    Log.d("onFailure",error.message.toString())
                }
            }
        })
    }

    internal fun getGambarku() : LiveData<ArrayList<Gambar>>{
        return listGambarku
    }
}