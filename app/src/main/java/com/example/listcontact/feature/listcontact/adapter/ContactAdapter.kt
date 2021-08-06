package com.example.listcontact.feature.listcontact.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listcontact.R
import com.example.listcontact.feature.listcontact.model.ContactsVO
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(
    private val context: Context,
    private val list: List<ContactsVO>,
    private val onClick: ((Int) -> Unit)
) : RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = list[position]
        with(holder.itemView){
            tvName.text = contact.name
            tvTelephone.text = contact.telephone
            linear_item_contact.setOnClickListener { onClick(contact.id) }
        }
    }

    override fun getItemCount(): Int = list.size
}

class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)