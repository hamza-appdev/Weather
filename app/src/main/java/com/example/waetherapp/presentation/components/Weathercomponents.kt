package com.example.waetherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.waetherapp.ui.theme.LightBlue
import com.example.waetherapp.ui.theme.SkyBlue
import io.ktor.websocket.Frame

@Composable
fun searchbar(
    modifier: Modifier= Modifier,
    city: String,
    onCityChange:(String)-> Unit,
    onSearchClick:()->Unit,
){
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
            placeholder = {Text("Enter city name", color = Color.White)},
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CloudWhite,
                unfocusedBorderColor = CloudWhite,
                focusedContainerColor =  CloudWhite.copy(alpha = 0.2f),
                unfocusedContainerColor = CloudWhite.copy(alpha = 0.2f),
               cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

        )

        Button(
            onClick = onSearchClick,
            modifier = Modifier.height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CloudWhite
            )
        ) {
            Text("Search")
        }
    }
}
@Composable
fun Weathercard(
    modifier: Modifier = Modifier,
    weatherDto: WeatherDto
    ) {
    Spacer(modifier= Modifier.height(40.dp))
    val temperature = weatherDto.main.temp.toInt()
    val weathercondition = weatherDto.weather.firstOrNull()?.main?:"unknown"
    val weatherdescription = weatherDto.weather.firstOrNull()?.description?:"unknown"
    val humidity = weatherDto.main.humidity
    val windSpeed = weatherDto.wind.speed

    val gradientColors = listOf(
        SkyBlue.copy(alpha = 0.7f ),
        LightBlue.copy(alpha = 0.8f ),
        CloudWhite.copy(alpha = 0.6f)
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
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = weatherDto.sys.country,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
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
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = weathercondition,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = weatherdescription,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
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
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
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


