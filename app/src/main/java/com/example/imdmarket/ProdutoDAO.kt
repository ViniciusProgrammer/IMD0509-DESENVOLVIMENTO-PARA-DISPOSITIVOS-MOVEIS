package com.example.imdmarket

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProdutoDAO(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "IMDMarket.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "produtos"
        private const val COL_CODIGO = "codigo"
        private const val COL_NOME = "nome"
        private const val COL_DESCRICAO = "descricao"
        private const val COL_ESTOQUE = "estoque"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COL_CODIGO TEXT PRIMARY KEY,
                $COL_NOME TEXT,
                $COL_DESCRICAO TEXT,
                $COL_ESTOQUE INTEGER
        )"""
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun adicionarProduto(produto: Produto): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_CODIGO, produto.codigo)
            put(COL_NOME, produto.nome)
            put(COL_DESCRICAO, produto.descricao)
            put(COL_ESTOQUE, produto.estoque)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun listarProdutos(): List<Produto> {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        val produtos = mutableListOf<Produto>()

        while (cursor.moveToNext()) {
            val codigo = cursor.getString(cursor.getColumnIndexOrThrow(COL_CODIGO))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOME))
            val descricao = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRICAO))
            val estoque = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ESTOQUE))
            produtos.add(Produto(codigo, nome, descricao, estoque))
        }
        cursor.close()
        db.close()
        return produtos
    }

    fun atualizarProduto(produto: Produto): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NOME, produto.nome)
            put(COL_DESCRICAO, produto.descricao)
            put(COL_ESTOQUE, produto.estoque)
        }
        val result = db.update(TABLE_NAME, values, "$COL_CODIGO = ?", arrayOf(produto.codigo))
        db.close()
        return result > 0
    }

    fun deletarProduto(codigo: String): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, "$COL_CODIGO = ?", arrayOf(codigo))
        db.close()
        return result > 0
    }
}