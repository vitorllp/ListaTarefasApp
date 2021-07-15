package ifsp.pdm.listatarefasapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ifsp.pdm.listatarefasapp.R
import ifsp.pdm.listatarefasapp.adapter.OnTarefaClickListener
import ifsp.pdm.listatarefasapp.adapter.TarefasAdapter
import ifsp.pdm.listatarefasapp.databinding.ActivityMainBinding
import ifsp.pdm.listatarefasapp.model.Tarefa


class MainActivity : AppCompatActivity(), OnTarefaClickListener {
    private lateinit var tarefasList: MutableList<Tarefa>
    private lateinit var tarefasAdapter: TarefasAdapter

    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tarefasAdapter = TarefasAdapter(tarefasList, this)
        activityMainBinding.tarefasRV.adapter = tarefasAdapter
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sairMI) {
            Firebase.auth.signOut()
            startActivity(Intent(this, AutenticacaoActivity::class.java))
        }
        if (item.itemId == R.id.addTarefaTI) {
            startActivity(Intent(this, AdicionarTarefaActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTarefaClick(posicao: Int) {
        TODO("Not yet implemented")
    }

}
