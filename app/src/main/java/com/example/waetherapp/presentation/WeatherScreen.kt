package com.example.waetherapp.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waetherapp.R
import com.example.waetherapp.presentation.components.Animatedweatherbackground
import com.example.waetherapp.presentation.components.LoadingAnimation
import com.example.waetherapp.presentation.components.Weathercard
import com.example.waetherapp.presentation.components.searchbar
import com.example.waetherapp.ui.theme.CloudWhite
import com.example.waetherapp.util.Resultt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewmodel) {
    val weatherstate = viewModel.weatherstate.value
    val city = viewModel.city
    val snackbarHoststate = remember { SnackbarHostState() }
    val scroll = rememberScrollState()

    LaunchedEffect(key1 = viewModel.snackbarmessage) {
        viewModel.snackbarmessage?.let {
            snackbarHoststate.showSnackbar(it)
            viewModel.clearsnackmessage()
        }
    }
    Scaffold (
        snackbarHost ={SnackbarHost(snackbarHoststate)},
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    Text(
                   text = "Weather App",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        }

    ){innerpadding->
        Animatedweatherbackground(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                searchbar(
                    city = city,
                    onCityChange={viewModel.updatecity(it)},
                    onSearchClick = {viewModel.searchweather()}
                )
                Spacer(modifier = Modifier.height(16.dp))
                when(weatherstate){
                   is Resultt.Loading ->  {
                       Column(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(16.dp),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ) {
                           LoadingAnimation()

                       }
                   }
                    is Resultt.Initial->{

                        Emptystate()
                    }
                    is Resultt.Error->{

                        Errormessage(message = weatherstate.message)
                    }

                    is Resultt.Success -> {
                        Weathercard(weatherDto = weatherstate.data)
                    }
                }
            }



        }
    }

}

@Composable
fun Emptystate(modifier: Modifier = Modifier) {
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
      Box (
          modifier= Modifier
              .size(120.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(MaterialTheme.colorScheme.primaryContainer),
          contentAlignment = Alignment.Center
      ){
         Icon( painter = painterResource(id = R.drawable.ic_weather),
             contentDescription = "",
         modifier= Modifier.size(65.dp),
             tint = MaterialTheme.colorScheme.onPrimaryContainer,

         )
      }
        Spacer(modifier= Modifier.height(16.dp))
        androidx.compose.material3.Text(
            text ="Search for city",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier= Modifier.height(16.dp))
        androidx.compose.material3.Text(
            text ="Enter a city name above to see the current weather",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

    }
}



@Composable
fun Errormessage(message:String) {
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box (
            modifier= Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.errorContainer),
            contentAlignment = Alignment.Center
        ){
            Icon( imageVector = Icons.Default.Warning,
                contentDescription = "",
                modifier= Modifier.size(65.dp),
              tint =MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier= Modifier.height(16.dp))
        androidx.compose.material3.Text(
            text ="Oops! something went wrong",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier= Modifier.height(16.dp))
        androidx.compose.material3.Text(
            text =message,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

    }
}







