package com.d3if4043.kalkulator_jodoh.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "input")
data class InputEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var nama: String,
    var namaPasangan: String
)