package ifsp.pdm.listatarefasapp.model

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ifsp.pdm.listatarefasapp.model.TarefaFirebase.Constantes.LISTA_TAREFAS_DATABASE
import ifsp.pdm.listatarefasapp.view.MainActivity

class TarefaFirebase(mainActivity: MainActivity?){
    object Constantes{
        val LISTA_TAREFAS_DATABASE = "listaTarefas"
    }
    private val tarefasListDataBase = Firebase.database.getReference(LISTA_TAREFAS_DATABASE)
    private val tarefasList: MutableList<Tarefa> = mutableListOf()
    init{
        tarefasListDataBase.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val novaTarefa: Tarefa = snapshot.getValue<Tarefa>()?:Tarefa()
                if (tarefasList.indexOfFirst { it.titulo == novaTarefa.titulo } == -1){
                    tarefasList.add(novaTarefa)
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val tarefaEditada: Tarefa = snapshot.getValue<Tarefa>()?:Tarefa()
                val indice = tarefasList.indexOfFirst{ it.titulo == tarefaEditada.titulo }
                tarefasList[indice] = tarefaEditada
            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
                val tarefaRemovida: Tarefa = snapshot.getValue<Tarefa>()?:Tarefa()
                tarefasList.remove(tarefaRemovida)
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
               //to do
            }
            override fun onCancelled(error: DatabaseError) {
                //to do
            }

        })
    }

    fun criaOuAtualizaTarefa(tarefa: Tarefa){
        tarefasListDataBase.child(tarefa.titulo).setValue(tarefa)
    }

    fun readTarefa(titulo: String): Tarefa? {
        val tarefa:Tarefa
        return if(tarefasList.indexOfFirst { it.titulo == titulo } != -1){
            tarefa = tarefasList[tarefasList.indexOfFirst { it.titulo == titulo }]
            tarefa
        } else { null }
    }

    fun readTarefas(): MutableList<Tarefa> = tarefasList

    fun deletaTarefa(titulo: String){
        tarefasListDataBase.child(titulo).removeValue()
    }
}