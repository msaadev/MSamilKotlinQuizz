package com.msamil.msamilkotlinquizz.view.Login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.msamil.msamilkotlinquizz.R
import com.msamil.msamilkotlinquizz.view.MainActivity2
import kotlinx.android.synthetic.main.fragment_log_in.*


class LogIn : Fragment() {
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        email = emailLoginText.text.toString()
        password = passwordLoginText.text.toString()

        if (auth.currentUser != null){
           val intent = Intent(context,
               MainActivity2::class.java)
            startActivity(intent)
            activity?.finish()
        }

        btnSignup.setOnClickListener { signup(it) }
        btnLogin.setOnClickListener { signin() }


    }

   private fun signin(){
       email = emailLoginText.text.toString()
       password = passwordLoginText.text.toString()

       if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
           auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
               if (task.isSuccessful){
                   Toast.makeText(context,"Welcome : "+auth.currentUser!!.displayName,Toast.LENGTH_LONG).show()
                   val intent = Intent(context,
                       MainActivity2::class.java)
                   startActivity(intent)
                   activity?.finish()

               } else if (task.exception != null){
                   Toast.makeText(context,task.exception?.message,Toast.LENGTH_LONG).show()
               }
           }
       }

    }

    private fun signup(v :View){
        val action =
            LogInDirections.actionLogInToSignupFragment()
        v.findNavController().navigate(action)


    }
}