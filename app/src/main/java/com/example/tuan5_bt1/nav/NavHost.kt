package com.example.tuan5_bt1.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tuan5_bt1.LoginScreen
import com.example.tuan5_bt1.ProfileScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun AppNavHost(
    firebaseAuth: FirebaseAuth,
    googleSignInClient: GoogleSignInClient, 
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            LoginScreen(navController = navController, firebaseAuth = firebaseAuth, googleSignInClient = googleSignInClient)
        }
        composable("profile_screen") {
            ProfileScreen(navController = navController, firebaseAuth = firebaseAuth, googleSignInClient = googleSignInClient) // ✅ Truyền googleSignInClient vào
        }
    }
}

