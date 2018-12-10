package com.example.shreyash.diceroller

import android.app.Application
import com.example.shreyash.diceroller.Model.MyObjectBox
import io.objectbox.BoxStore

class BaseApp : Application() {

    lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()

        boxStore = MyObjectBox.builder().androidContext(this).build()


    }
/*
    fun getBox(): Box<Product> = boxStore.boxFor(Product::class.java)

    fun getShopBox(): Box<Encr> = boxStore.boxFor(Shop::class.java)
*/

}