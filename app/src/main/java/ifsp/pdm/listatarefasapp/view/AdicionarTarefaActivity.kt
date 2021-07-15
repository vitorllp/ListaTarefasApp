package ifsp.pdm.listatarefasapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ifsp.pdm.listatarefasapp.controller.TarefaController
import ifsp.pdm.listatarefasapp.databinding.ActivityAdicionarTarefaBinding
import ifsp.pdm.listatarefasapp.model.Tarefa
import java.util.*


class AdicionarTarefaActivity: AppCompatActivity() {
    private lateinit var activityAdicionarTarefaBinding: ActivityAdicionarTarefaBinding
    private lateinit var tarefaController: TarefaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdicionarTarefaBinding = ActivityAdicionarTarefaBinding.inflate(layoutInflater)
        tarefaController = TarefaController(null)
        setContentView(activityAdicionarTarefaBinding.root)
    }


    fun onClick(view: View) {
        when (view) {
            activityAdicionarTarefaBinding.adicionarTarefaBt -> {
                val titulo = activityAdicionarTarefaBinding.tituloEt.text.toString()
                val descricao = activityAdicionarTarefaBinding.descricaoEt.text.toString()
                val dataPrevista = activityAdicionarTarefaBinding.dataPrevistaET.text.toString()
                val dataCriacao = Calendar.getInstance().time.toString()
                val usuario = Firebase.auth.currentUser?.email.toString()
                val tarefa = Tarefa(
                    titulo,
                    descricao,
                    usuario,
                    dataCriacao,
                    dataPrevista,
                    false )
                if (tarefaController.buscaTarefa(tarefa) == null &&
                                        tarefa.titulo.isNotEmpty() &&
                                            tarefa.dataPrevista.isNotEmpty() ) {
                    tarefaController.insereOuAtualizaTarefa(tarefa)
                    Toast.makeText(this, "Tarefa foi adicionada", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "Tarefa não foi adicionada", Toast.LENGTH_SHORT).show()
                }
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }


    }