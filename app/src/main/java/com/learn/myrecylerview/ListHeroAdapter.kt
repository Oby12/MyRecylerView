package com.learn.myrecylerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListHeroAdapter(private  val Listhero : ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero,parent, false)
        return  ListViewHolder(view)
    }

    override fun getItemCount(): Int  = Listhero.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photoo) = Listhero[position]

        /*circleCrop(), digunakan untuk membuat gambar menjadi lingkaran.
        transition, digunakan untuk menambahkan transisi ketika gambar selesai dimuat.
        thumbnail, digunakan untuk menambahkan thumbnail atau gambar sebelum gambar dimuat.
        error, digunakan untuk menggantikan gambar yang gagal ketika dimuat.*/
        Glide.with(holder.itemView.context)
            .load(photoo) // URL Gambar
            .into(holder.imgPhoto) // imageView mana yang akan diterapkan
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(Listhero[holder.adapterPosition]) }

        //Penggunaan Jika Mengunakan URL
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)

    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_item_description)
    }

}

