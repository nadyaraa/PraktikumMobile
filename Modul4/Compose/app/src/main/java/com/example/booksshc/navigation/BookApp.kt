package com.example.booksshc.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.booksshc.ui.ui.screens.DetailScreen
import com.example.booksshc.ui.ui.screens.HomeScreen
import com.example.booksshc.viewmodel.BookVMFactory
import com.example.booksshc.viewmodel.BookView
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookApp() {
    val navController = rememberNavController()
    val viewModel: BookView = viewModel(
        factory = BookVMFactory("Toko Buku NEXGENT")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (
                        currentRoute?.startsWith("detail") == true
                    )
                        "Detail Buku" else "Daftar Buku"
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
                HomeScreen(viewModel = viewModel, onNavigateToDetail = { id ->
                    navController.navigate("detail/$id")
                })
            }

            composable(
                "detail/{bookId}",
                arguments = listOf(navArgument("bookId") {
                    type = NavType.IntType
                })
            ){
                    backStackEntry ->
                val bookId = backStackEntry.arguments?.getInt("bookId")
                val bookList by viewModel.books.collectAsState()
                val book = bookList.find {
                    it.id == bookId
                }
                if (book != null) {
                    Timber.i("Berpindah ke halaman detail buku ${book.title}")
                }

                DetailScreen(book = book)
            }
        }
    }
}