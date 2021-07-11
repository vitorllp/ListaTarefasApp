package ifsp.pdm.listatarefasapp.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ifsp.pdm.listatarefasapp.AutenticacaoFirebase
import ifsp.pdm.listatarefasapp.databinding.ActivityCadastrarBinding

class AutenticacaoActivity: AppCompatActivity() {
    private lateinit var activityAutenticacaoBinding: ActivityAutenticacaoBinding

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        activityAutenticacaoBinding = ActivityAutenticacaoBinding.inflate(layoutInflater)
        setContentView(activityAutenticacaoBinding.root)
    }

    fun onClick(view: View) {
        when (view) {
            activityAutenticacaoBinding.cadastrarBt -> {
                startActivity(Intent(this, CadastrarActivity::class.java))
            }

            activityAutenticacaoBinding.entrarBt -> {
                val email: String = activityAutenticacaoBinding.emailEt.text.toString()
                val senha: String = activityAutenticacaoBinding.senhaEt.text.toString()
                AutenticacaoFirebase.firebaseAuth.signInWithEmailAndPassword(email, senha)
                    .addOnSuccessListener {
                        entrar()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT)
                            .show()
                    }
            }

            activityAutenticacaoBinding.recuperarSenhaBt -> {
                startActivity(Intent(this, RecuperarSenhaActivity::class.java))

            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (AutenticacaoFirebase.firebaseAuth.currentUser != null) {
            Toast.makeText(this, "Usuário já logado", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun entrar() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}