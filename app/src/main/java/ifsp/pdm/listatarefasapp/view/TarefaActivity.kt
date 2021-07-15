package ifsp.pdm.listatarefasapp.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ifsp.pdm.listatarefasapp.controller.TarefaController
import ifsp.pdm.listatarefasapp.databinding.ActivityTarefaBinding
import ifsp.pdm.listatarefasapp.model.Tarefa

class TarefaActivity : AppCompatActivity() {
    private lateinit var activityTarefaBinding: ActivityTarefaBinding
    private lateinit var tarefaController: TarefaController
    private lateinit var tarefa: Tarefa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTarefaBinding = ActivityTarefaBinding.inflate(layoutInflater)
        setContentView(activityTarefaBinding.root)
        tarefaController = TarefaController(null)

        activityTarefaBinding.tituloEditarEt.isEnabled = false
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            tarefa = bundle.getParcelable("tarefa")!!
            with(activityTarefaBinding) {
                tituloEditarEt.setText(tarefa.titulo)
                descricaoEditarEt.setText(tarefa.descricao)
                dataEditarPrevistaET.setText(tarefa.dataPrevista)
                concluidaEditarCheckBox.isChecked = tarefa.cumprido
            }
        }
    }

    fun onClick(view: View) {
        when (view) {
            activityTarefaBinding.atualizarTarefaBT -> {
                if(!activityTarefaBinding.descricaoEditarEt.equals("")
                            && !activityTarefaBinding.dataEditarPrevistaET.equals("") ){
                    tarefa.descricao = activityTarefaBinding.descricaoEditarEt.text.toString()
                    tarefa.dataPrevista = activityTarefaBinding.dataEditarPrevistaET.text.toString()
                    tarefa.cumprido = activityTarefaBinding.concluidaEditarCheckBox.isChecked
                    tarefaController.insereOuAtualizaTarefa(tarefa)
                }
                startActivity(Intent(this, MainActivity::class.java))
            }

            activityTarefaBinding.deletarTarefaBT -> {
                tarefaController.removeTarefa(tarefa)
                startActivity(Intent(this, MainActivity::class.java))
            }

            activityTarefaBinding.cancelarTarefaBT -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}