package com.nikolam.basketpro.ui.drills.selection

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nikolam.basketpro.R
import com.nikolam.basketpro.model.DrillsType
import com.nikolam.basketpro.util.DrillsClickListener

class DrillSelectionAdapter(
    private val context: Context,
    private val clickListener: DrillsClickListener

) :
    RecyclerView.Adapter<DrillSelectionAdapter.DrillTypeViewHolder>() {

    var drillsTypeList = arrayListOf<DrillsType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrillTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DrillTypeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DrillTypeViewHolder, position: Int) {


        val drillType: DrillsType = drillsTypeList[position]
        holder.bind(drillType)
    }

    override fun getItemCount(): Int = drillsTypeList.size

    fun getDrillTypeAt(position: Int): DrillsType {
        return drillsTypeList[position]
    }

    fun update(newList : ArrayList<DrillsType>){

        DiffUtil.calculateDiff(DrillTypeDiffCallBack(drillsTypeList, newList), false).dispatchUpdatesTo(this)
        drillsTypeList = newList
    }


    inner class DrillTypeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.drills_type_item, parent, false)) {

        private var imageView: ImageView
        private var title: TextView

        init {
            imageView = itemView.findViewById(R.id.imageView_drilltype_Thumbnail)
            title = itemView.findViewById(R.id.textView_drillType_title)


        }


        fun bind(drillType: DrillsType) {

            title.text = drillType.drillType_title

            Glide.with(context)
                .load(drillType.drillType_imageUrl)
                .centerCrop()
                .listener(object : RequestListener<Drawable> {

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageView.setImageDrawable(resource)
                        Log.d("TAG", resource.toString())
                        return true
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("TAG", "FAIL " + e?.rootCauses)
                        return true
                    }

                })
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)


            itemView.setOnClickListener {
                clickListener.recyclerViewListClicked(drillType.drillType_title, it)
            }
        }

    }
}


// DiffCallBack to help Recycle View handle updates and changes
class DrillTypeDiffCallBack(
    private val newList: List<DrillsType>,
    private val oldList: List<DrillsType>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDrillType = oldList[oldItemPosition]
        val newDrillType= newList[newItemPosition]
        return oldDrillType.drillType_title == newDrillType.drillType_title
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDrillType = oldList[oldItemPosition]
        val newDrillType = newList[newItemPosition]
        return oldDrillType == newDrillType
    }
}