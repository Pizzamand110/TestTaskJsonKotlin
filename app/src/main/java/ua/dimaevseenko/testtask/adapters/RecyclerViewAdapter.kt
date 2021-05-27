package ua.dimaevseenko.testtask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.dimaevseenko.testtask.Photo
import ua.dimaevseenko.testtask.R

public class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    var photos: ArrayList<Photo> = ArrayList<Photo>()
    var context: Context
    var inflater: LayoutInflater

    constructor(context: Context, photos: ArrayList<Photo>){
        this.inflater = LayoutInflater.from(context)

        this.context = context
        this.photos = photos
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        var v: View = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return ViewHolder(v, context, photos)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        Picasso.get().load(photos.get(position).thumbnailUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class ViewHolder:RecyclerView.ViewHolder, View.OnClickListener{

        var imageView: ImageView

        var context: Context
        var photos: ArrayList<Photo>

        constructor(v: View, context: Context, photos: ArrayList<Photo>) : super(v) {
            imageView = v.findViewById(R.id.recyclerImageView)
            imageView.setOnClickListener(this)

            this.context = context
            this.photos = photos
        }

        public interface OnImageClickListener{
            fun onClick(photo: Photo)
        }

        override fun onClick(v: View?) {
            var listener: OnImageClickListener = context as OnImageClickListener
            listener.onClick(photos.get(adapterPosition))
        }
    }
}