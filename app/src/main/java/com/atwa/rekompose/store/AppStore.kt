package com.atwa.rekompose.store

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atwa.rekompose.R
import com.atwa.rekompose.core.di.ServiceLocator.coroutineScope
import com.atwa.rekompose.core.di.ServiceLocator.githubRepo
import com.atwa.rekompose.feature.filter.RepositoryFilterDialog
import com.atwa.rekompose.feature.repositories.RepositoriesScreen
import com.atwa.rekompose.feature.repositories.RepositoriesState
import com.atwa.rekompose.feature.repositories.repositoriesReducer
import com.atwa.rekompose.core.middleware.coroutineDispatcherMiddleware
import com.atwa.rekompose.core.middleware.loggerMiddleware
import com.atwa.rekompose.core.middleware.networkMiddleware
import com.atwa.rekompose.designsystem.theme.rekomposeSampleTheme
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.threadsafe.createTypedThreadSafeStore
import org.reduxkotlin.thunk.createThunkMiddleware


@Composable
fun AppStore() = StoreProvider(
    createTypedThreadSafeStore(
        ::appReducer,
        AppState(),
        applyMiddleware(
            createThunkMiddleware(),
            coroutineDispatcherMiddleware(coroutineScope),
            networkMiddleware(githubRepo, coroutineScope),
            loggerMiddleware()
        )
    )
) {
    AppScreen()
}

fun appReducer(state: AppState, action: Any) = AppState(
    repositories = repositoriesReducer(state.repositories, action),
)

data class AppState(
    val repositories: RepositoriesState = RepositoriesState(),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    rekomposeSampleTheme {
        val openFilterDialog = remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Github Repositories",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 70.dp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    actions = {
                        Box(
                            modifier = Modifier.size(width = 50.dp, height = 25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = { openFilterDialog.value = true }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_filter),
                                    "",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                    )
                )
            },
            content = {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 70.dp), color = MaterialTheme.colorScheme.surface
                ) {
                    RepositoriesScreen()
                    when {
                        openFilterDialog.value -> RepositoryFilterDialog(
                            onDismissRequest = { openFilterDialog.value = false },
                        )
                    }
                }
            }
        )

    }
}