package ifsp.pdm.listatarefasapp.controller

import ifsp.pdm.listatarefasapp.model.Tarefa
import ifsp.pdm.listatarefasapp.model.TarefaFirebase
import ifsp.pdm.listatarefasapp.view.MainActivity

class TarefaController(mainActivity: MainActivity) {
    fun insereOuAtualizaTarefa(tarefa: Tarefa) = TarefaFirebase().criaOuAtualizaTarefa(tarefa)

    fun buscaTarefa(tarefa:Tarefa) = TarefaFirebase().readTarefa(tarefa.titulo)

    fun buscaTarefas(tarefa:Tarefa) = TarefaFirebase().readTarefas()

    fun removeTarefa(tarefa:Tarefa) = TarefaFirebase().deletaTarefa(tarefa.titulo)
}