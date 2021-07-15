package ifsp.pdm.listatarefasapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ifsp.pdm.listatarefasapp.R
import ifsp.pdm.listatarefasapp.adapter.OnTarefaClickListener
import ifsp.pdm.listatarefasapp.adapter.TarefasAdapter
import ifsp.pdm.listatarefasapp.controller.TarefaController
import ifsp.pdm.listatarefasapp.databinding.ActivityMainBinding
import ifsp.pdm.listatarefasapp.model.Tarefa


class MainActivity : AppCompatActivity(), OnTarefaClickListener {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var tarefasAdapter: TarefasAdapter
    private lateinit var tarefasList: MutableList<Tarefa>
    private lateinit var tarefaController: TarefaController
    private lateinit var tarefasLayoutManager: LinearLayoutManager
    private lateinit var tarefaLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        tarefaController = TarefaController(this)
        tarefasList = tarefaController.buscaTarefas()

        tarefasAdapter = TarefasAdapter(tarefasList, this)
        activityMainBinding.tarefasRV.adapter = tarefasAdapter

        tarefasLayoutManager = LinearLayoutManager(this)
        activityMainBinding.tarefasRV.layoutManager = tarefasLayoutManager

        tarefaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                if (activityResult.resultCode == RESULT_OK) {
                    val tarefa: Tarefa? =
                        activityResult.data?.getParcelableExtra("tarefa")
                    if (tarefa != null) {
                        val index =
                            tarefasList.indexOf(tarefasList.find { it.titulo == tarefa.titulo })

                        if(activityResult.data?.getStringExtra("acao") == "excluir") {
                            tarefaController.removeTarefa(tarefa)
                            tarefasList.remove(tarefa)
                            tarefasAdapter.notifyDataSetChanged()
                        } else {
                            tarefasList[index] = tarefa
                            tarefaController.insereOuAtualizaTarefa(tarefa)
                            tarefasAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
    }

    override fun onTarefaClick(posicao : Int){
        val tarefa: Tarefa = tarefasList[posicao]
        if(!tarefa.cumprido){
            val tarefaIntent = Intent(this, TarefaActivity::class.java)
            tarefaIntent.putExtra("tarefa", tarefa)
            tarefaLauncher.launch(tarefaIntent)
        } else {
            Toast.makeText(this, "Não pode editar porque a tarefa está cumprida", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sairMI){
            Firebase.auth.signOut()
            startActivity(Intent(this, AutenticacaoActivity::class.java))
        }
        if(item.itemId == R.id.addTarefaTI){
            startActivity(Intent(this, AdicionarTarefaActivity::class.java))
        }
        if(item.itemId == R.id.refreshMI){
            startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


}
