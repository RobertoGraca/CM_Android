package com.example.homework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Activity2: AppCompatActivity() {
    private var newNumber: String = ""
    private var newName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        val editNumber: EditText = findViewById(R.id.edit_number)
        val editName: EditText = findViewById(R.id.edit_name)

        val saveButton: Button = findViewById(R.id.save_button)
        saveButton.setOnClickListener{
            val extras: Bundle? = intent.extras
            val id: Int? = extras?.getInt("button")
            newNumber = editNumber.text.toString()
            newName = editName.text.toString()
            val intent = Intent()
            intent.putExtra("NUMBER",newNumber)
            intent.putExtra("NAME",newName)
            if (id != null) {
                setResult(id,intent)
            }
            finish()
        }

        val cancelButton: Button = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener{finish()}
    }
}
