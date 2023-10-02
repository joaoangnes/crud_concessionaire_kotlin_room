package com.example.atividadekotlin02

import android.content.Context
import android.widget.Toast

// Classe UTIl do aplicativo
class Util private constructor() {

    companion object {
        // Função responsável por criar um TOAST no aplicativo
        fun exibirToast(context: Context, msg: String){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}