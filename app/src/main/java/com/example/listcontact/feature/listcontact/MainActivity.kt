package com.example.listcontact.feature.listcontact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listcontact.R
import com.example.listcontact.bases.BaseActivity
import com.example.listcontact.feature.contact.ContactActivity
import com.example.listcontact.feature.listcontact.adapter.ContactAdapter
import com.example.listcontact.feature.listcontact.model.ContactsVO
import com.example.listcontact.singleton.ContactSingleton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var adapter:ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateContactList()
        setupToolBar(toolBar, "Lista de contatos", false)
        setupRecyclerView()
        setupOnClicks()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun generateContactList() {
        ContactSingleton.list.add(ContactsVO(1,"Mateus", "(51) 99938-1964"))
        ContactSingleton.list.add(ContactsVO(2,"Ramon", "(51) 99999-1994"))
        ContactSingleton.list.add(ContactsVO(3,"Cassio", "(51) 99999-8888"))
    }

    private fun setupOnClicks() {
        fab.setOnClickListener { onClickAddFab() }
        ivSearch.setOnClickListener { onClickSearch() }
    }

    private fun onClickSearch() {
        val search = etSearch.text.toString()
        var listFilter: List<ContactsVO> = ContactSingleton.list
        if(!search.isNullOrEmpty()){
            listFilter = ContactSingleton.list.filter { contato ->
                if (contato.name.toLowerCase().contains(search.toLowerCase())){
                    return@filter true
                }
                return@filter false
            }
        }
        adapter = ContactAdapter(this,listFilter) { onClickItemRecyclerView(it) }
        recyclerView.adapter = adapter
        Toast.makeText(this,"Buscando por $search",Toast.LENGTH_SHORT).show()
    }

    private fun onClickItemRecyclerView(id: Int) {
        val intent = Intent(this, ContactActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun onClickAddFab() {
        startActivity(Intent(this, ContactActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        onClickSearch()
    }
}