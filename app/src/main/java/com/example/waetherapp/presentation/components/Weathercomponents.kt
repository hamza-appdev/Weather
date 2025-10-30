package com.example.waetherapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waetherapp.R
import com.example.waetherapp.data.remote.dto.WeatherDto
import com.example.waetherapp.ui.theme.CloudWhite
import com.example.waetherapp.ui.theme.DeepBlue
import com.example.waetherapp.ui.theme.LightBlue
import com.example.waetherapp.ui.theme.LightGray
import com.example.waetherapp.ui.theme.RainyBlue
import com.example.waetherapp.ui.theme.SkyBlue
import com.example.waetherapp.ui.theme.StormGray
import io.ktor.websocket.Frame

@SuppressLint("SuspiciousIndentation")
@Composable
fun searchbar(
    modifier: Modifier= Modifier,
    city: String,
    onCityChange:(String)-> Unit,
    onSearchClick:()->Unit,
){
    val textColor = if (isSystemInDarkTheme()) LightBlue  else LightBlue
            Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = onCityChange,
            modifier = Modifier.weight(1f),
            placeholder = {Text("Enter city name", color = textColor)},
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CloudWhite,
                unfocusedBorderColor = CloudWhite,
                focusedContainerColor =  CloudWhite.copy(alpha = 0.2f),
                unfocusedContainerColor = CloudWhite.copy(alpha = 0.2f),
               cursorColor =textColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,

                ),
            textStyle = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.W500,
                color = textColor
            )


        )

        Button(
            onClick = onSearchClick,
            modifier = Modifier.height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CloudWhite
            )
        ) {
            Text("Search", color = textColor)
        }
    }
}
@Composable
fun Weathercard(
    modifier: Modifier = Modifier,
    weatherDto: WeatherDto
    ) {
    Spacer(modifier= Modifier.height(40.dp))
    val temperature = (weatherDto.main.temp - 273.15).toInt()
    val weathercondition = weatherDto.weather.firstOrNull()?.main?:"unknown"
    val weatherdescription = weatherDto.weather.firstOrNull()?.description?:"unknown"
    val humidity = weatherDto.main.humidity
    val windSpeed = weatherDto.wind.speed

    val gradientColors = listOf(
        SkyBlue.copy(alpha = 1.4f ),
        LightBlue.copy(alpha = 0.9f ),
        CloudWhite.copy(alpha = 2.5f)
    )


    Card(
        modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier= Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = gradientColors
                    )
                )
                .padding(20.dp)
        ) {
            // City name and country
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = weatherDto.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = weatherDto.sys.country,
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            // Temperature and weather condition
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "$temperature°C",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = weathercondition,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = weatherdescription,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(DeepBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        getweatheremoji(weathercondition),
                        fontSize = 40.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            //Additional weather details
            Row (
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                WeatherdetailItem(
                    modifier = Modifier.weight(1f),
                    label = "Humidity",
                    value = "$humidity%",
                    iconResid =R.drawable.ic_humidity
                )
                Divider(
                    modifier= Modifier.height(40.dp)
                        .width(1.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)
                )
                WeatherdetailItem(
                    modifier = Modifier.weight(1f),
                    label = "Wind",
                    value = "$windSpeed m/s",
                    iconResid =R.drawable.ic_wind
                )
                Divider(
                    modifier= Modifier.height(40.dp)
                        .width(1.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)
                )
                WeatherdetailItem(
                    modifier = Modifier.weight(1f),
                    label = "Pressure",
                    value = "${weatherDto.main.pressure} hpa",
                    iconResid =R.drawable.ic_pressure
                )
            }

        }
    }
}

@Composable
fun WeatherdetailItem(
    modifier: Modifier = Modifier,
    label : String,
    value: String,
    iconResid: Int,
) {
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id = iconResid),
            contentDescription = "$label",
            modifier= Modifier.size(24.dp),
            tint = Color.Black
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

    }

}

private fun getweatheremoji(condition:String): String{
    return when(condition.lowercase()){
        "clear" -> "☀️"
        "clouds" -> "☁️"
        "rain" -> "🌧️"
        "drizzle" -> "🌦️"
        "thunderstorm" -> "⛈️"
        "snow" -> "❄️"
        "mist", "fog", "haze" -> "🌫️"
        else -> "🌤️"
    }
}


