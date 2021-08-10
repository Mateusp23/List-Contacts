package com.everis.listadecontatos.singleton

import com.everis.listadecontatos.feature.listacontatos.model.ContactsVO

object ContactSingleton {
    var lista: MutableList<ContactsVO> = mutableListOf()
}