package ifsp.pdm.listatarefasapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ifsp.pdm.listatarefasapp.R
import ifsp.pdm.listatarefasapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onStart() {
        super.onStart()
        val user = Firebase.auth.currentUser
        val textoUsuario: String = "Bem-vindo," + user?.email
        activityMainBinding.textUsuario.text = textoUsuario
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
        return super.onOptionsItemSelected(item)
    }


}