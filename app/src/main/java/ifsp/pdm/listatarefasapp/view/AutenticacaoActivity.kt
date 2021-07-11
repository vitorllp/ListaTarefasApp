package ifsp.pdm.listatarefasapp.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ifsp.pdm.listatarefasapp.databinding.ActivityCadastrarBinding

class AutenticacaoActivity: AppCompatActivity() {
    private lateinit var activityCadastrarBinding: ActivityCadastrarBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        activityCadastrarBinding = ActivityCadastrarBinding.inflate(layoutInflater)
        setContentView(activityCadastrarBinding.root)
    }
}