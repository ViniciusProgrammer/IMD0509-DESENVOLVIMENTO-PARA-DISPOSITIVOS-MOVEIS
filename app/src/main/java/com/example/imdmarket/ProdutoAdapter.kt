package com.example.imdmarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdutoAdapter(private val produtos: List<Produto>) :
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.tvItemNome)
        val descricao: TextView = itemView.findViewById(R.id.tvItemDescricao)
        val estoque: TextView = itemView.findViewById(R.id.tvItemEstoque)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.nome.text = produto.nome
        holder.descricao.text = produto.descricao
        holder.estoque.text = "Estoque: ${produto.estoque}"
    }

    override fun getItemCount(): Int = produtos.size
}
