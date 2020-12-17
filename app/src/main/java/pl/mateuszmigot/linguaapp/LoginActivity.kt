package pl.mateuszmigot.linguaapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
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
}