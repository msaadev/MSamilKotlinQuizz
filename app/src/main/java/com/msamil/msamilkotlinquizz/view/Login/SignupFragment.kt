package com.msamil.msamilkotlinquizz.view.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.msamil.msamilkotlinquizz.R
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var auth :FirebaseAuth
    private lateinit var email :String
    private lateinit var password:String
    private lateinit var userName:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        email = emailSignupText.text.toString()
        password = passwordSignupText.text.toString()
        userName = userNameSignupText.text.toString()
        buttonSignup.setOnClickListener {
            signup2(it)
        }

    }

    private fun signup2(v:View){

        email = emailSignupText.text.toString()
        password = passwordSignupText.text.toString()
        userName = userNameSignupText.text.toString()

    if (!email.isNullOrEmpty() && !password.isNullOrEmpty() && !userName.isNullOrEmpty()){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                var update = UserProfileChangeRequest.Builder().setDisplayName(userName).build()
                auth.currentUser!!.updateProfile(update)
                val action=
                    SignupFragmentDirections.actionSignupFragmentToLogIn()
                v.findNavController().navigate(action)
                auth.signOut()

            }else if (task.exception != null){
                Toast.makeText(context,task.exception!!.message,Toast.LENGTH_LONG).show()
            }
        }
    }else{
        Toast.makeText(context,"Please Write Email / User Name / Password",Toast.LENGTH_LONG).show()
    }
    }
}