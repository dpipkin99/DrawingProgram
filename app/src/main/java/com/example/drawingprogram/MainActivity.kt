package com.example.drawingprogram


import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_color -> {
                Toast.makeText(this, "I changed the color", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.clear_canvas -> {
                clearDrawing()

                true
            }
            else -> {
                Toast.makeText(this, "I did nothing", Toast.LENGTH_SHORT)
                super.onOptionsItemSelected(item)
            }
        }
    }
    fun ClearDrawing(){
        AlertDialog.Builder(this)
            .setTitle("Confirm Clear")
            .setMessage("Are you sure")
            .setPositiveButton(R.string.confirm) { _, _ ->
                val paintView = findViewById<CustomView>(R.id.paint_view)
                paintView.clearDrawing()
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
            .show()
    }
        fun pickColor(){
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                    ColorEnvelopeListener {envelope, fromUser ->
                        val paintView = findViewById<CustomView>(R.id.paint_view)
                        paintView.pathColor = envelope.color
                        paintView.invalidate()

                    })
                .setNegativeButton(getString(R.string.cancel),
                    DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
                .attachAlphaSlideBar(true) // the default value is true.
                .attachBrightnessSlideBar(true) // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show()
        }



    }

}