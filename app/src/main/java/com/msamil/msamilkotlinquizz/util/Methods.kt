package com.msamil.msamilkotlinquizz.util

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class Methods {
private lateinit var auth: FirebaseAuth
    fun updateFB(username:String,image:Uri) : UserProfileChangeRequest{
        var change = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .setPhotoUri(image)
            .build()

        return change
    }





}