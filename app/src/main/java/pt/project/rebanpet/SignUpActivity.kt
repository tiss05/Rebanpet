package pt.project.rebanpet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pt.project.rebanpet.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignupBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)

        signupBinding.buttonSignupUser.setOnClickListener {

            val userEmail = signupBinding.editTextEmailSignup.text.toString()
            val userPassword = signupBinding.editTextPasswordSignup.text.toString()

            signupWithFirebase(userEmail,userPassword)

        }

    }

    fun signupWithFirebase(userEmail:String, userPassword:String){

        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener{task ->

            if (task.isSuccessful){

                Toast.makeText(applicationContext,
                    "Your account has been created",
                    Toast.LENGTH_SHORT).show()
                finish()

            } else {

                Toast.makeText(applicationContext,
                    task.exception?.toString(),
                    Toast.LENGTH_SHORT).show()

            }

        }

    }
}