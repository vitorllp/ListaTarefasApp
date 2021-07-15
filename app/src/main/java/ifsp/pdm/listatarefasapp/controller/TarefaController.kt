package ifsp.pdm.listatarefasapp.controller

import ifsp.pdm.listatarefasapp.model.Tarefa
import ifsp.pdm.listatarefasapp.model.TarefaFirebase
import ifsp.pdm.listatarefasapp.view.MainActivity

class TarefaController(mainActivity: MainActivity?) {
    private var tarefaFireBase: TarefaFirebase  = TarefaFirebase(null)

    fun insereOuAtualizaTarefa(tarefa: Tarefa) = tarefaFireBase.criaOuAtualizaTarefa(tarefa)

    fun buscaTarefa(tarefa:Tarefa) = tarefaFireBase.readTarefa(tarefa.titulo)

    fun buscaTarefas() = tarefaFireBase.readTarefas()

    fun removeTarefa(tarefa:Tarefa) = tarefaFireBase.deletaTarefa(tarefa.titulo)
}