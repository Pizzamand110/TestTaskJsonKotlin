package ua.dimaevseenko.testtask.framents

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import ua.dimaevseenko.testtask.Photo
import ua.dimaevseenko.testtask.R
import ua.dimaevseenko.testtask.adapters.RecyclerViewAdapter
import java.io.IOException
import java.util.*
import java.util.concurrent.ExecutionException

class MainFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    var photos = ArrayList<Photo>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.main_fragment, container, false)

        val photos = getPhotos("https://jsonplaceholder.typicode.com/albums/1/photos")

        try {
            photos.execute().get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        this.photos = photos.array

        recyclerView = fragmentView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = context?.let { RecyclerViewAdapter(it, this.photos) }

        return fragmentView
        }

    public class getPhotos(var url: String) : AsyncTask<Void?, Void?, Void?>() {

        var array = ArrayList<Photo>()

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val client = OkHttpClient()
                val request: Request = Request.Builder().url(url).build()
                val responses = client.newCall(request).execute()

                val jsonData = responses.body!!.string()
                val jsonArray = JSONArray(jsonData)

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    array.add(
                        Photo(
                            jsonObject.getInt("albumId"),
                            jsonObject.getInt("id"),
                            jsonObject.getString("title"),
                            jsonObject.getString("url"),
                            jsonObject.getString("thumbnailUrl")
                        )
                    )
                }

            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return null
        }


    }

    override fun toString(): String {
        return "MainFragment"
    }
}