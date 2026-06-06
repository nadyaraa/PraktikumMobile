package com.example.bookshop.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.bookshop.feature.book.presentation.screens.BookDetailScreen
import com.example.bookshop.feature.book.presentation.screens.BookScreen
import com.example.bookshop.feature.book.presentation.viewModel.BookVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(viewModel: BookVM) {
    val navController = rememberNavController()

    val isDarkMode by viewModel.isDarkMode.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (currentRoute?.startsWith("detail") == true) "Detail Buku" else "BookShop")
                },
                actions = {
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = {viewModel.toggleDarkMode(it)}
                    )
                },
                navigationIcon = {
                    if (currentRoute?.startsWith("detail") == true) {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                BookScreen(viewModel,
                    onNavigateToDetail = { bookId ->
                        navController.navigate("detail/$bookId")
                    }
                )
            }

            composable(
                route = "detail/{bookId}",
                arguments = listOf(
                    navArgument("bookId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable

                BookDetailScreen(
                    bookId = bookId,
                    viewModel = viewModel
                )
            }
        }
    }
}