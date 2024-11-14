package com.cmc.flashcallapp

import android.content.Intent
import android.content.res.ColorStateList
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    private val PHONE_STATE_PERMISSION_REQUEST_CODE = 101

    private lateinit var callStatusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the status text view
        callStatusText = findViewById(R.id.callStatusText)

        // Check if permissions are granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_PHONE_STATE), PHONE_STATE_PERMISSION_REQUEST_CODE)
        }

        val flashlightSwitch: Switch = findViewById(R.id.flashlightSwitch)

        flashlightSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                flashlightSwitch.thumbTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green))
                flashlightSwitch.trackTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.light_green))

                // Start the Flashlight Service
                startService(Intent(this, FlashlightService::class.java))

                // Update status text
                callStatusText.text = "Flashlight enabled for incoming calls"
            } else {
                flashlightSwitch.thumbTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))
                flashlightSwitch.trackTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.light_red))

                // Stop the Flashlight Service
                stopService(Intent(this, FlashlightService::class.java))

                // Update status text
                callStatusText.text = "Flashlight disabled"
            }
        }
    }

    // Handle the permission request response
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Camera permission is required to use the flashlight", Toast.LENGTH_SHORT).show()
                }
            }
            PHONE_STATE_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Phone state permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Phone state permission is required to detect incoming calls", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
