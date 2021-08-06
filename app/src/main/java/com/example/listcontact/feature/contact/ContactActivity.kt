package com.example.listcontact.feature.contact

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.listcontact.R
import com.example.listcontact.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : BaseActivity() {

    private var idContact: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        setupToolBar(toolBar, "Contato",true)
        setupContact()
        btnSaveContact.setOnClickListener { onClickSaveContact() }
        btnDeleteContact.setOnClickListener { onClickSalvarContato() }
    }

    private fun onClickSalvarContato() {
        TODO("Not yet implemented")
    }

    private fun onClickSaveContact() {
        TODO("Not yet implemented")
    }

    private fun setupContact() {
    }

}