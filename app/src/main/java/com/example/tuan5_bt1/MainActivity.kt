package com.example.tuan5_bt1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.tuan5_bt1.nav.AppNavHost
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Khởi tạo Firebase (nên đảm bảo rằng bạn đã thêm google-services.json vào thư mục app)
        FirebaseApp.initializeApp(this)
        val firebaseAuth = FirebaseAuth.getInstance()

        // Cấu hình Google Sign-In
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Đã thêm key vào strings.xml
            .requestEmail()
            .build()
        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        setContent {
            val navController = rememberNavController()
            AppNavHost(
                firebaseAuth = firebaseAuth,
                googleSignInClient = googleSignInClient,
                navController = navController
            )
        }
    }
}
