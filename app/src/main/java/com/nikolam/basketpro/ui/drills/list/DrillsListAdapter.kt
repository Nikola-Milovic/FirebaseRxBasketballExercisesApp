package com.nikolam.basketpro.ui.drills.list

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
import com.nikolam.basketpro.R
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillsType
import com.nikolam.basketpro.util.DrillsClickListener


class DrillsListAdapter(
    private val context: Context,
    private val onclickListener: DrillsClickListener

) :
    RecyclerView.Adapter<DrillsListAdapter.DrillListViewHolder>() {

    var drillsList = arrayListOf<Drill>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrillListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DrillListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DrillListViewHolder, position: Int) {


        val drillType: Drill = drillsList[position]
        holder.bind(drillType)
    }

    override fun getItemCount(): Int = drillsList.size

    fun getDrillAt(position: Int): Drill {
        return drillsList[position]
    }

    fun update(newList: ArrayList<Drill>) {

        DiffUtil.calculateDiff(DrillDIffCallBack(drillsList, newList), false)
            .dispatchUpdatesTo(this)
        drillsList = newList
    }


    inner class DrillListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.drills_item, parent, false)) {

        private var imageView: ImageView
        private var title: TextView
        private var description: TextView

        init {
            imageView = itemView.findViewById(R.id.imageView_drill_Thumbnail)
            title = itemView.findViewById(R.id.textView_drill_title)
            description = itemView.findViewById(R.id.textView_drill_description)

        }


        fun bind(drill: Drill) {

            title.text = drill.drillList_title

            Glide.with(context)
                .load(drill.drillList_imageUrl)
                .listener(object : RequestListener<Drawable> {

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageView.setImageDrawable(resource)
                        return true
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("TAG", "FAIL " + e?.rootCauses)
                        return true
                    }

                })
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)


            itemView.setOnClickListener {
               onclickListener.recyclerViewListClicked(drill.drillList_drill_id,it)
            }
        }

    }
}


// DiffCallBack to help Recycle View handle updates and changes
class DrillDIffCallBack(
    private val newList: List<Drill>,
    private val oldList: List<Drill>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDrill = oldList[oldItemPosition]
        val newDrill = newList[newItemPosition]
        return oldDrill.drillList_title == newDrill.drillList_title
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDrill = oldList[oldItemPosition]
        val newDrill = newList[newItemPosition]
        return oldDrill == newDrill
    }
}
