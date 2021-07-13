package ifsp.pdm.listatarefasapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ifsp.pdm.listatarefasapp.databinding.ActivityAdicionarTarefaBinding
import ifsp.pdm.listatarefasapp.databinding.ActivityAutenticacaoBinding

class AdicionarTarefaActivity: AppCompatActivity() {
    private lateinit var activityAdicionarTarefaBinding: ActivityAdicionarTarefaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdicionarTarefaBinding = ActivityAdicionarTarefaBinding.inflate(layoutInflater)
        setContentView(activityAdicionarTarefaBinding.root)
    }

}