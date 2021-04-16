package pl.mateuszmigot.linguaapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val loginButton = findViewById<MaterialButton>(R.id.LoginButton)
        val forgotButton = findViewById<Button>(R.id.ForgetPassButton)
        val signUpButton = findViewById<Button>(R.id.SignUpButton)

        loginButton.setOnClickListener { login() }
        signUpButton.setOnClickListener { changeActivity(RegisterActivity::class.java, R.anim.pull_in_right, R.anim.pull_out_left) }
        forgotButton.setOnClickListener { changeActivity(ForgotPasswordActivity::class.java, R.anim.pull_in_left, R.anim.pull_out_right) }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        //if(currentUser != null)
        //    changeActivity(MainActivity::class.java, R.anim.pull_in_right, R.anim.pull_out_left)
    }

    private fun changeActivity(activity: Class<*>, enterAnim: Int, exitAnim: Int) {
        val intent = Intent(this@LoginActivity, activity)
        startActivity(intent)
        overridePendingTransition(enterAnim, exitAnim)
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener(this) { task ->
               if(task.isSuccessful) {
                    changeActivity(MainActivity::class.java, R.anim.pull_in_right, R.anim.pull_out_left)
               } else {
                   Toast.makeText(baseContext, "Authentication failed.",
                       Toast.LENGTH_SHORT).show()
               }
           }
    }

    private fun login() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        val loginField = findViewById<TextInputLayout>(R.id.LoginField)
        val passwordField = findViewById<TextInputLayout>(R.id.passwordField)
        val loginInput = findViewById<TextInputEditText>(R.id.LoginInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.PasswordInput)
        loginField.error = null
        passwordField.error = null
        val login = loginInput.text.toString()
        val password = passwordInput.text.toString()
        when {
            login == "" -> {
                loginField.error = "This field cannot be empty"
            }
            password == "" -> {
                passwordField.error = "This field cannot be empty"
            }
            else -> {
                signIn(login, password)
            }
        }
    }

}