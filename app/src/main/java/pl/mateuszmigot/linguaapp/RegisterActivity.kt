package pl.mateuszmigot.linguaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        val loginButton = findViewById<Button>(R.id.LogMeInButton)
        loginButton.setOnClickListener { changeToLoginActivity() }

        val registerButton = findViewById<MaterialButton>(R.id.RegisterButton)
        registerButton.setOnClickListener { registerUser() }
    }

    private fun changeToLoginActivity() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.pull_in_left, R.anim.pull_out_right)
    }

    private fun createAccount(login: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    val user = mAuth.currentUser
                    addUserToDatabase(user!!.uid, login)
                    sendEmailVerification()
                    makeSuccessDialog()
                } else {
                    Toast.makeText(this@RegisterActivity, "Registration failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(username: String, userID: String) {
        val db = Firebase.firestore
        val user = hashMapOf(username to userID)
        db.collection("users")
            .add(user)
            .addOnSuccessListener { Log.v("DB", "DocumentSnapshot added with ID: $userID") }
            .addOnFailureListener {e -> Log.v("DB", "Error: $e")}
    }

    private fun sendEmailVerification() {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Email verification sent. ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@RegisterActivity,
                    "Couldn't send email verification.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun makeSuccessDialog() {
        MaterialAlertDialogBuilder(this@RegisterActivity)
            .setTitle(R.string.success_register)
            .setMessage(R.string.thank_you_register)
            .setPositiveButton("OK") { _, _ -> changeToLoginActivity() }
            .show()
    }

    private fun registerUser() {
        val loginField = findViewById<TextInputLayout>(R.id.LoginFieldRegister)
        val emailField = findViewById<TextInputLayout>(R.id.EmailFieldRegister)
        val passwordField = findViewById<TextInputLayout>(R.id.passwordFieldRegister)

        val loginInput = findViewById<TextInputEditText>(R.id.LoginInputRegister)
        val emailInput = findViewById<TextInputEditText>(R.id.EmailInputRegister)
        val passwordInput = findViewById<TextInputEditText>(R.id.PasswordInputRegister)

        loginField.error = null
        emailField.error = null
        passwordField.error = null

        val login = loginInput.text.toString()
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        when {
            login == "" -> {
                loginField.error = "This field cannot be empty!"
            }
            email == "" -> {
                emailField.error = "This field cannot be empty!"
            }
            password == "" -> {
                passwordField.error = "This field cannot be empty!"
            }
            else -> {
                createAccount(login, email, password)
            }
        }
    }

}