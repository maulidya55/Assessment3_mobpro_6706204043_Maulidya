package com.d3if4043.kalkulator_jodoh.model

import com.d3if4043.kalkulator_jodoh.db.InputEntity

fun InputEntity.generateHasil(): Output {
    // TODO
    val nama = nama
    val namaPasangan = namaPasangan
    val randomNumber = (0..100).random()
    fun hasil(): String {
        return nama + " dan " + namaPasangan + " : " + randomNumber + "% Kalian adalah Jodoh!"
    }

    val hasilText = hasil()

    return Output(nama, namaPasangan, hasilText)
}