package com.example.listcontact.singleton

import com.example.listcontact.feature.listcontact.model.ContactsVO

class ContactSingleton {
    companion object {
        var list: MutableList<ContactsVO> = mutableListOf()
    }
}