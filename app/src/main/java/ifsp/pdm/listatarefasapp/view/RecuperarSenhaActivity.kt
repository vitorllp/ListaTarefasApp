package ifsp.pdm.listatarefasapp.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ifsp.pdm.listatarefasapp.AutenticacaoFirebase
import ifsp.pdm.listatarefasapp.databinding.ActivityRecuperarSenhaBinding

class RecuperarSenhaActivity : AppCompatActivity() {
    private lateinit var activityRecuperarSenhaBinding: ActivityRecuperarSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRecuperarSenhaBinding = ActivityRecuperarSenhaBinding.inflate(layoutInflater)
        setContentView(activityRecuperarSenhaBinding.root)
    }

    fun onClick(view: View) {
        if (view == activityRecuperarSenhaBinding.enviarEmailBt) {
            val email = activityRecuperarSenhaBinding.emailRecuperacaoSenhaEt.text.toString()
            AutenticacaoFirebase.firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                Toast.makeText(this, "Email de recuperação enviado para $email", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Falha no envio do e-mail de recuperação", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}