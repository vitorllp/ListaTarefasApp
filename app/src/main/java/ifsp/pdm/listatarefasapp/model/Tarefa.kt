package ifsp.pdm.listatarefasapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tarefa(
    var titulo: String = "",
    var descricao: String = "",
    var usuario: String = "",
    var dataCriacao: String = "",
    var dataPrevista: String = "",
    var cumprido: Boolean = false,
) : Parcelable
