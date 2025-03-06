package com.fionova.myfirstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PhonesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mPhonesList: ArrayList<PhoneModel> = ArrayList()

    fun setupPhones(phoneList: Array<PhoneModel>){
        mPhonesList.clear()
        mPhonesList.addAll(phoneList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)

        return PhonesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhonesViewHolder){
            holder.bind(mPhones = mPhonesList[position])
        }
    }

    override fun getItemCount(): Int {
        return mPhonesList.count()
    }

    class PhonesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val name: TextView = itemView.findViewById(R.id.name)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val score: TextView = itemView.findViewById(R.id.score)

        fun bind(mPhones: PhoneModel) {
            name.text = mPhones.product_name
            price.text = mPhones.price
            date.text = mPhones.date
            score.text = mPhones.score
        }
    }
}
