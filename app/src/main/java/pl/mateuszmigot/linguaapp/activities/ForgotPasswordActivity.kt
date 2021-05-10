package pl.mateuszmigot.linguaapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import pl.mateuszmigot.linguaapp.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val goBackButton = findViewById<Button>(R.id.GoBackButton)
        goBackButton.setOnClickListener { changeToLoginActivity() }

        val sendButton = findViewById<MaterialButton>(R.id.SendButton)
        sendButton.setOnClickListener { resetPassword() }


    }

    private fun changeToLoginActivity() {
        val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.pull_in_right, R.anim.pull_out_left)
    }

    private fun resetPassword() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        val emailField = findViewById<TextInputLayout>(R.id.EmailField)
        val emailInput = findViewById<TextInputEditText>(R.id.ForgotEmailInput)
        emailField.error = null
        when (val email = emailInput.text.toString()) {
            "" -> {
                emailField.error = "This field cannot be empty"
            }
            else -> {
                sendPasswordResetEmail(email)
            }
        }

    }

    private fun sendPasswordResetEmail(email: String) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Toast.makeText(this@ForgotPasswordActivity, "Email sent.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ForgotPasswordActivity, "Invalid email. Try again.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}