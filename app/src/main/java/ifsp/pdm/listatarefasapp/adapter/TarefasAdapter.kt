package ifsp.pdm.listatarefasapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ifsp.pdm.listatarefasapp.R
import ifsp.pdm.listatarefasapp.databinding.ViewTarefaBinding
import ifsp.pdm.listatarefasapp.model.Tarefa
import ifsp.pdm.listatarefasapp.view.MainActivity

class TarefasAdapter(
    private val tarefasList: MutableList<Tarefa>,
    private val onTarefaClickListener: MainActivity
) : RecyclerView.Adapter<TarefasAdapter.TarefaViewHolder>() {
    inner class TarefaViewHolder(viewTarefa: View) : RecyclerView.ViewHolder(viewTarefa) {
        val titulo: TextView = viewTarefa.findViewById(R.id.tituloTarefaTV)
        val descricao: TextView = viewTarefa.findViewById(R.id.descricaoTarefaTV)

        //val dataPrevista: TextView = viewTarefa.findViewById(R.id.dataPrevistaTv)
        val usuario: TextView = viewTarefa.findViewById(R.id.usuarioTV)
        val dataCriacao: TextView = viewTarefa.findViewById(R.id.dataCriacaoTV)
        val concluido: CheckBox = viewTarefa.findViewById(R.id.concluidaCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val viewTarefaBinding: ViewTarefaBinding =
            ViewTarefaBinding.inflate(LayoutInflater.from(parent.context))
        return TarefaViewHolder(viewTarefaBinding.root)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa: Tarefa = tarefasList[position]
        holder.titulo.text = tarefa.titulo
        holder.descricao.text = tarefa.descricao
        //holder.dataPrevista.text = tarefa.dataPrevista
        holder.dataCriacao.text = tarefa.dataCriacao
        holder.usuario.text = tarefa.usuario
        holder.concluido.isChecked = tarefa.cumprido
        holder.itemView.setOnClickListener {
            onTarefaClickListener.onTarefaClick(position)
        }
    }

    override fun getItemCount(): Int {
        return tarefasList.size
    }
}