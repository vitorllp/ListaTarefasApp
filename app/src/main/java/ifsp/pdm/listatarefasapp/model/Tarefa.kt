package ifsp.pdm.listatarefasapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tarefa(
    val titulo: String = "",
    val descricao: String = "",
    val usuario: String = "",
    val dataCriacao: String = "",
    val dataPrevista: String = "",
    val cumprido: Boolean = false,
) : Parcelable
