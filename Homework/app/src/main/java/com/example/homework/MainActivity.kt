package com.example.homework

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var number: TextView
    private var speedNumber1: String = ""
    private var speedNumber2: String = ""
    private var speedNumber3: String = ""
    private lateinit var speedDial1: Button
    private lateinit var speedDial2: Button
    private lateinit var speedDial3: Button


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number = findViewById(R.id.number_text)

        speedDial1 = findViewById(R.id.speed_dial1)
        speedDial1.setOnClickListener{number.text=speedNumber1}
        speedDial1.setOnLongClickListener{updateSpeedDial(1)}


        speedDial2 = findViewById(R.id.speed_dial2)
        speedDial2.setOnClickListener{number.text=speedNumber2}
        speedDial2.setOnLongClickListener{updateSpeedDial(2)}

        speedDial3 = findViewById(R.id.speed_dial3)
        speedDial3.setOnClickListener{number.text=speedNumber3}
        speedDial3.setOnLongClickListener{updateSpeedDial(3)}

        val number1: Button = findViewById(R.id.number1)
        number1.setOnClickListener{changeNumberText("1")}

        val number2: Button = findViewById(R.id.number2)
        number2.setOnClickListener{changeNumberText("2")}

        val number3: Button = findViewById(R.id.number3)
        number3.setOnClickListener{changeNumberText("3")}

        val number4: Button = findViewById(R.id.number4)
        number4.setOnClickListener{changeNumberText("4")}

        val number5: Button = findViewById(R.id.number5)
        number5.setOnClickListener{changeNumberText("5")}

        val number6: Button = findViewById(R.id.number6)
        number6.setOnClickListener{changeNumberText("6")}

        val number7: Button = findViewById(R.id.number7)
        number7.setOnClickListener{changeNumberText("7")}

        val number8: Button = findViewById(R.id.number8)
        number8.setOnClickListener{changeNumberText("8")}

        val number9: Button = findViewById(R.id.number9)
        number9.setOnClickListener{changeNumberText("9")}

        val numberAsterisk: Button = findViewById(R.id.numberAsterisk)
        numberAsterisk.setOnClickListener{changeNumberText("*")}

        val number0: Button = findViewById(R.id.number0)
        number0.setOnClickListener{changeNumberText("0")}

        val numberCardinal: Button = findViewById(R.id.numberCardinal)
        numberCardinal.setOnClickListener{changeNumberText("#")}

        val deleteButton: ImageButton = findViewById(R.id.backspace_button)
        deleteButton.setOnClickListener{number.text = number.text.dropLast(1)}
        deleteButton.setOnLongClickListener{
            number.text = ""
            return@setOnLongClickListener true
        }

        val phoneButton: ImageButton = findViewById(R.id.call_button)

        phoneButton.setOnClickListener{dialPhoneNumber(number.text.toString())}
    }

    private fun changeNumberText(newValue: String){
        number.text = number.text.toString() + newValue
        Log.d("PRINT0","${number.text}")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun dialPhoneNumber(phoneNumber: String) {
        Log.d("PRINT1","${number.text}")
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phoneNumber")
        if (ContextCompat.checkSelfPermission(applicationContext, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent)
        } else {
            requestPermissions(arrayOf(CALL_PHONE), 1)
            if (ContextCompat.checkSelfPermission(applicationContext, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent)
            }
        }
    }

    private fun updateSpeedDial(id: Int): Boolean {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("button",id)
        startActivityForResult(intent,1)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val newNumber = data?.getStringExtra("NUMBER")
        val name = data?.getStringExtra("NAME")
        number.text = newNumber
        when (resultCode) {
            1 -> {
                speedNumber1 = newNumber.toString()
                speedDial1.text = name.toString()
            }
            2 -> {
                speedNumber2 = newNumber.toString()
                speedDial2.text = name.toString()
            }
            3 -> {
                speedNumber3 = newNumber.toString()
                speedDial3.text = name.toString()
            }
        }
    }
}
