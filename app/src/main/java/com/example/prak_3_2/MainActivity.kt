package com.example.prak_3_2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.start)
        button.setOnClickListener {
            val inputText: String = findViewById<EditText?>(R.id.input).text.toString()
            val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
            val checkRadioButton: RadioButton = findViewById(radioGroup.checkedRadioButtonId)

            when (checkRadioButton.id) {
                R.id.url -> {
                    Url(inputText)
                }
                R.id.geo -> {
                    Map(inputText)
                }
                R.id.telefon -> {
                    Phone(inputText)
                }
                R.id.auto -> {
                    Auto(inputText)
                }
            }

        }
    }

    fun Url(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun Map(geoinfo: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=$geoinfo")))
    }

    fun Phone(number: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("tel", number, null)))
    }

    fun Auto(text: String) {
        val phoneRegex = Regex("""^\+?[0-9]{11}${'$'}""")
        val urlRegex =
            Regex("""^https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)$""")
        val geoRegex = Regex("""^\d+\.\d+, \d+\.\d+${'$'}""")

        if (phoneRegex.containsMatchIn(text)) {
            Phone(text)
        } else if (urlRegex.containsMatchIn(text)) {
            Url(text)
        } else if (geoRegex.containsMatchIn(text)) {
            Map(text)
        } else {
            Toast.makeText(this@MainActivity, "Try Another Request", Toast.LENGTH_SHORT)
                .show()
        }
    }
}