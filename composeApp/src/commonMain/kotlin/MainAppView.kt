
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import common.ui.theme.CineTrackerTheme
import navigation.MainNavGraph
import navigation.components.MainNavBar
import navigation.components.MainNavBarItem
import navigation.components.TopNavBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun MainAppView() {
    KoinContext {
        CineTrackerTheme {
            val navController = rememberNavController()
            val navItems = mainNavBarItems
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen = currentBackStackEntry?.destination?.route

            var mainBarState by rememberSaveable { mutableStateOf(true) }

            Scaffold(
                topBar = {
                    TopNavBar(
                        currentScreen = currentScreen,
                        // mainViewModel = mainViewModel,
                        // displaySortScreen = displaySortScreen,
                    )
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = mainBarState,
                        enter = fadeIn(spring(stiffness = Spring.StiffnessHigh)),
                        exit = fadeOut(spring(stiffness = Spring.StiffnessHigh)),
                    ) {
                        MainNavBar(
                            navController = navController,
                            // mainViewModel = mainViewModel,
                            navBarItems = navItems,
                        )
                    }
                },
                content = { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavGraph(navController)
                    }
                },
            )
        }
    }
}

val mainNavBarItems = listOf<MainNavBarItem>(
    MainNavBarItem.Home,
    MainNavBarItem.Browse,
    MainNavBarItem.Watchlist,
    MainNavBarItem.Search,
)