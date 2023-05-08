package com.example.githubapi

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseAuthService {
    private const val PROVIDER_ID = "github.com"
    val provider = OAuthProvider.newBuilder(PROVIDER_ID)
    val firebaseAuth: FirebaseAuth = Firebase.auth
    private const val TAG: String = "FirebaseAuthService"
    var token: String = ""
        private set

    fun loginFlow(activity: Activity) {
        val pendingResultTask = firebaseAuth.pendingAuthResult
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                .addOnSuccessListener {
                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                    // The OAuth secret can be retrieved by calling:
                    // ((OAuthCredential)authResult.getCredential()).getSecret().
                }
                .addOnFailureListener {
                    // Handle failure.
                }
        } else {
            // There's no pending result so you need to start the sign-in flow.
            // See below.
            firebaseAuth
                .startActivityForSignInWithProvider( activity, provider.build())
                .addOnSuccessListener {
                    Log.d(TAG, "success")

                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    (it.credential as? OAuthCredential)?.let {
                        token = it.accessToken ?: ""
                        Log.d(TAG, "token: $token")
                    }

                    // The OAuth secret can be retrieved by calling:
                    // ((OAuthCredential)authResult.getCredential()).getSecret().
                }
                .addOnFailureListener {
                    Log.d(TAG, "failure")
                    // Handle failure.
                }
        }
    }

    fun isLogin(): Boolean  {
        val isLogin = firebaseAuth.currentUser != null
        val res = if (isLogin) "yes" else "no"
        Log.d(TAG, "isLogin: $res")
        return isLogin
    }

    fun signOut() {
        Log.d(TAG, "sign out")

        firebaseAuth.signOut()
    }

    fun signInWithPassword() {
        firebaseAuth.signInWithEmailAndPassword(
            "narisin1124@gmail.com" , "acappella1124"
        )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "signInWithEmail:failure", task.exception)
                }
            }
    }
}