package ifsp.pdm.listatarefasapp.model

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ifsp.pdm.listatarefasapp.model.TarefaFirebase.Constantes.LISTA_TAREFAS_DATABASE

class TarefaFirebase{
    object Constantes{
        val LISTA_TAREFAS_DATABASE = "listaTarefas"
    }
    private val tarefasListDataBase = Firebase.database.getReference(LISTA_TAREFAS_DATABASE)
    private val tarefasList: MutableList<Tarefa> = mutableListOf()
    init{
        tarefasListDataBase.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val novaTarefa: Tarefa = (snapshot.value?:Tarefa()) as Tarefa
                if (tarefasList.indexOfFirst { it.titulo == novaTarefa.titulo } == -1){
                    tarefasList.add(novaTarefa)
                }

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val tarefaEditada: Tarefa = (snapshot.value?:Tarefa()) as Tarefa
                val indice = tarefasList.indexOfFirst{ it.titulo == tarefaEditada.titulo }
                tarefasList[indice] = tarefaEditada
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val tarefaRemovida: Tarefa = (snapshot.value?:Tarefa()) as Tarefa
                tarefasList.remove(tarefaRemovida)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
               //
            }

            override fun onCancelled(error: DatabaseError) {
                //
            }

        })
    }

    fun criaOuAtualizaTarefa(tarefa: Tarefa){
        //tarefasListDataBase.push() -> nao coloca nome no nó
        tarefasListDataBase.child(tarefa.titulo).setValue(tarefa)
    }

    fun readTarefa(titulo: String): Tarefa = tarefasList[tarefasList.indexOfFirst {
        it.titulo.equals(titulo)
    }]

    fun readTarefas(): MutableList<Tarefa> = tarefasList

    fun deletaTarefa(titulo: String){
        //chega na chave pq titulo é a chave
        tarefasListDataBase.child(titulo)
    }
}