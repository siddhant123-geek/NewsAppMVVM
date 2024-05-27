package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.net.Uri
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.HomeScreenRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.country.CountriesRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.instantsearch.InstantSearchRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.language.LanguagesRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newsbycountry.NewsByCountryRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newsbylanguage.NewsByLanguageRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newssource.NewsSourceRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline.TopHeadlineRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadlineswithpaging.TopHeadlineWithPagingRoute
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.DefaultNetworkHelper

open class Route(val name: String) {
    object Home: Route("home")
    object TopHeadline : Route("topheadline")
    object TopHeadlineWithPaging : Route("topheadlinewithpaging")
    object NewsSource : Route("newssource")
    object Countries : Route("countries")
    object NewsByCountry : Route("{country}/newsbycountry")
    object Language : Route("language")
    object NewsByLanguage : Route("{language}/newsbylanguage")
    object Search : Route("search")
}

@Composable
fun NewsNavHost() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val netWorkHelper = DefaultNetworkHelper(context)

    NavHost(
        navController = navController,
        startDestination = Route.Home.name
    ) {
        composable(route = Route.Home.name) {
            HomeScreenRoute(onItemClick = { navigateTo(it, navController) } )
        }
        composable(route = Route.TopHeadline.name) {
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
        composable(route = Route.TopHeadlineWithPaging.name) {
            TopHeadlineWithPagingRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
        composable(route = Route.NewsSource.name) {
            NewsSourceRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        // Countries
        composable(route = Route.Countries.name) {
            CountriesRoute(onItemClick = {
                // pass the iso code of the country on clicking a country
                val routeName = "$it/newsbycountry"
                val route = Route(routeName)
                navigateTo(route, navController)
            })
        }
        composable(route = Route.NewsByCountry.name) {
            val country = it.arguments?.getString("country")
            NewsByCountryRoute(onNewsClick = { openCustomChromeTab(context, it) },
                country = country!!, netWorkHelper = netWorkHelper)
        }

        //Languages
        composable(route = Route.Language.name) {
            LanguagesRoute(onItemClick = {
                // pass the iso code of the country on clicking a country
                val routeName = "$it/newsbylanguage"
                val route = Route(routeName)
                navigateTo(route, navController)
            })
        }
        composable(route = Route.NewsByLanguage.name) {
            val language = it.arguments?.getString("language")
            NewsByLanguageRoute(onNewsClick = { openCustomChromeTab(context, it) },
                language = language!!, netWorkHelper = netWorkHelper)
        }
        composable(route = Route.Search.name) {
            InstantSearchRoute(onNewsClick = {openCustomChromeTab(context, it)})
        }
    }

}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}

fun navigateTo(route: Route, navController: NavController) {
    navController.navigate(route.name)
}
