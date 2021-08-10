package com.everis.listadecontatos.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.everis.listadecontatos.feature.listacontatos.model.ContactsVO

class HelperDB(
    context: Context
) : SQLiteOpenHelper(context, NOME_DB, null, CURRENT_VERSION) {

    companion object {
        private val NOME_DB = "contato.db"
        private val CURRENT_VERSION = 2
    }

    val TABLE_NAME = "contato"
    val COLUMNS_ID = "id"
    val COLUMNS_NAME = "nome"
    val COLUMNS_TELEPHONE = "telefone"
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMNS_ID INTEGER NOT NULL," +
            "$COLUMNS_NAME TEXT NOT NULL," +
            "$COLUMNS_TELEPHONE TEXT NOT NULL," +
            "" +
            "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT)" +
            ")"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion) {
            db?.execSQL(DROP_TABLE)
        }
        onCreate(db)
    }

    fun searchContacts(search: String, searchId: Boolean = false) : List<ContactsVO> {
        val db = readableDatabase ?: return mutableListOf()
        var list = mutableListOf<ContactsVO>()
        var where: String? = null
        var args: Array<String> = arrayOf()
        if(searchId){
            where = "$COLUMNS_ID = ?"
            args = arrayOf("$search")
        }else{
            where = "$COLUMNS_NAME LIKE ?"
            args = arrayOf("%$search%")
        }
        var cursor = db.query(TABLE_NAME,null,where,args,null,null,null)
        if (cursor == null){
            db.close()
            return mutableListOf()
        }
        while(cursor.moveToNext()){
            var contact = ContactsVO(
                cursor.getInt(cursor.getColumnIndex(COLUMNS_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_TELEPHONE))
            )
            list.add(contact)
        }
        db.close()
        return list
    }

    fun saveContact(contact: ContactsVO) {
        val db = writableDatabase ?: return
        var content = ContentValues()
        content.put(COLUMNS_NAME,contact.name)
        content.put(COLUMNS_TELEPHONE,contact.telephone)
        db.insert(TABLE_NAME,null,content)
        db.close()
    }

    fun deleteContact(id: Int) {
        val db = writableDatabase ?: return
        val sql = "DELETE FROM $TABLE_NAME WHERE $COLUMNS_ID = ?"
        val arg = arrayOf("$id")
        db.execSQL(sql,arg)
        db.close()
    }

    fun updateContact(contact: ContactsVO) {
        val db = writableDatabase ?: return
        val sql = "UPDATE $TABLE_NAME SET $COLUMNS_NAME = ?, $COLUMNS_TELEPHONE = ? WHERE $COLUMNS_ID = ?"
        val arg = arrayOf(contact.name,contact.telephone,contact.id)
        db.execSQL(sql,arg)
        db.close()
    }
}