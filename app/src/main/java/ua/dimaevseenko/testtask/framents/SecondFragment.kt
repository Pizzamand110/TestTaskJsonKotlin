package ua.dimaevseenko.testtask.framents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ua.dimaevseenko.testtask.Photo
import ua.dimaevseenko.testtask.R

class SecondFragment(var photo: Photo) : Fragment() {
    lateinit var imageView: ImageView

    lateinit var textAlbumId: TextView
    lateinit var textId: TextView
    lateinit var textTitle: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.second_fragment, container, false)

        imageView = fragmentView.findViewById(R.id.imageView2)

        textAlbumId = fragmentView.findViewById(R.id.textAlbumId)
        textId = fragmentView.findViewById(R.id.textId)
        textTitle = fragmentView.findViewById(R.id.textTitle)

        Picasso.get().load(photo.url).into(imageView)

        textAlbumId.text = photo.albumId.toString()
        textId.text = photo.id.toString()
        textTitle.text = photo.title

        return fragmentView
    }
}