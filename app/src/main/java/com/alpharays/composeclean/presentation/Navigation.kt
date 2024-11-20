package com.alpharays.composeclean.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "user_list_screen_static"
    ) {
        composable(NavigationConstants.DeepLinks.USER_LIST_SCREEN) { UserListScreen(navController) }
        composable("user_list_screen_static") { UserListStaticScreen(navController) }

        /*
        composable("${NavigationConstants.DeepLinks.VERIFY_OTP}/{${NavigationConstants.VERIFICATION_ID}}/{${NavigationConstants.MOBILE_NUMBER}}") { backStackEntry ->
            VerifyOtpScreen(
                navController,
                verificationId = backStackEntry.arguments?.getString(NavigationConstants.VERIFICATION_ID)
                    ?: "",
                mobileNumber = backStackEntry.arguments?.getString(NavigationConstants.MOBILE_NUMBER)
                    ?: ""
            )
        }

         */
    }
}