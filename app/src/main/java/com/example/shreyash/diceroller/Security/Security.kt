package com.example.shreyash.diceroller.Security

import android.util.Base64;
import com.example.shreyash.diceroller.Model.Product
import java.nio.charset.Charset
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

object Security{
    private val ALGORITHM = "AES"
    private val KEY = "1Hbfh667adfDEJ78"
    @Throws(Exception::class)
    fun encrypt(value:String):String {
        val key = generateKey()
        val cipher = Cipher.getInstance(Security.ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedByteValue = cipher.doFinal(value.toByteArray(charset("utf-8")))
        val encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT)
        return encryptedValue64
    }
    @Throws(Exception::class)
    fun decrypt(value:String):String {
        val key = generateKey()
        val cipher = Cipher.getInstance(Security.ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decryptedValue64 = Base64.decode(value, Base64.DEFAULT)
        val decryptedByteValue = cipher.doFinal(decryptedValue64)

        val decryptedValue = String(decryptedByteValue, charset("utf-8"))
        return decryptedValue
    }
    @Throws(Exception::class)
    private fun generateKey(): Key {
        val key = SecretKeySpec(Security.KEY.toByteArray(), Security.ALGORITHM)
        return key
    }
}