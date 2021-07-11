package ifsp.pdm.listatarefasapp.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ifsp.pdm.listatarefasapp.databinding.ActivityRecuperarSenhaBinding

class RecuperarSenhaActivity: AppCompatActivity() {
    private lateinit var activityRecuperarSenhaBinding: ActivityRecuperarSenhaBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        activityRecuperarSenhaBinding = ActivityRecuperarSenhaBinding.inflate(layoutInflater)
        setContentView(activityRecuperarSenhaBinding.root)
    }
}