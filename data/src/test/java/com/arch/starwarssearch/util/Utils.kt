package com.arch.starwarssearch.util

object Utils {
    fun readFromFile(fileName: String): String{
        return javaClass.classLoader?.let {
            it.getResource(fileName)?.readText()
        }!!
    }
}