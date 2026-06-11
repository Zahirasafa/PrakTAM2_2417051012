package com.example.praktam2_2417051012.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object MapsUtils {
    fun openGoogleMaps(context: Context, query: String) {
        val encodedQuery = Uri.encode(query)
        val appUri = Uri.parse("geo:0,0?q=$encodedQuery")
        val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$encodedQuery")
        
        val intent = Intent(Intent.ACTION_VIEW, appUri).apply {
            setPackage("com.google.android.apps.maps")
        }
        
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Google Maps app not available, try generic browser map link
            try {
                val webIntent = Intent(Intent.ACTION_VIEW, webUri)
                context.startActivity(webIntent)
            } catch (ex: Exception) {
                Toast.makeText(context, "Aplikasi peta tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
