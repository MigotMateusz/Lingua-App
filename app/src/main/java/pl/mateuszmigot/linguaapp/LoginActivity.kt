package pl.mateuszmigot.linguaapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
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
        //signUpButton.setOnClickListener { changeActivity(RegisterActivity::class.java) }
        //forgotButton.setOnClickListener { changeActivity(ForgotPasswordActivity::class.java) }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if(currentUser != null)
            changeActivity(MainActivity::class.java)
    }

    private fun changeActivity(activity: Class<*>) {
        val intent = Intent(this@LoginActivity, activity)
        startActivity(intent)
        overridePendingTransition(R.anim.pull_in_left, R.anim.pull_out_right)
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener(this) { task ->
               if(task.isSuccessful) {
                    changeActivity(MainActivity::class.java)
               } else {
                   Toast.makeText(baseContext, "Authentication failed.",
                       Toast.LENGTH_SHORT).show()
               }
           }
    }

    fun login() {
        val loginInput = findViewById<TextInputEditText>(R.id.LoginInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.PasswordInput)
        val login = loginInput.text.toString()
        val password = passwordInput.text.toString()
        signIn(login, password)
    }

}