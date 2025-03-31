package com.example.tuan5_bt1

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    navController: NavController,
    firebaseAuth: FirebaseAuth,
    googleSignInClient: GoogleSignInClient
) {
    val context = LocalContext.current

    // Xử lý kết quả đăng nhập
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { signInTask ->
                    if (signInTask.isSuccessful) {
                        Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                        navController.navigate("profile_screen")
                    } else {
                        Toast.makeText(context, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: ApiException) {
            Toast.makeText(context, "Lỗi đăng nhập: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Giao diện
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo), // Thay bằng tên file logo của bạn
            contentDescription = "UTH Logo",
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tiêu đề App
        Text(
            text = "SmartTasks",
            fontSize = 20.sp
        )

        // Mô tả ngắn
        Text(
            text = "A simple and efficient to-do app",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Welcome
        Text(
            text = "Welcome",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Mô tả login
        Text(
            text = "Ready to explore? Log in to get started.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Nút đăng nhập với Google
        Button(onClick = {
            val signInIntent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.icon_gg),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("SIGN IN WITH GOOGLE")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bản quyền (nếu muốn)
        Text(
            text = "© UTHSmartTasks",
            fontSize = 12.sp
        )
    }
}
