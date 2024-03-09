package fr.thomasbernard03.rickandmorty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.thomasbernard03.rickandmorty.commons.extensions.toColor
import fr.thomasbernard03.rickandmorty.commons.extensions.toPrettyDate
import fr.thomasbernard03.rickandmorty.commons.extensions.toText
import fr.thomasbernard03.rickandmorty.presentation.theme.Dark100
import java.util.Date

@Composable
fun EpisodesItem(
    modifier : Modifier = Modifier,
    name : String,
    episode : String,
    airDate : Date,
    onClick : () -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Dark100
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall)

            Text(
                text = "$episode - ${airDate.toPrettyDate()}",
                style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
@Preview
private fun EpisodesItemPreview() = PreviewComponents {
    EpisodesItem(name = "Pilot", episode = "S01E01", airDate = Date())
    EpisodesItem(name = "Pilot 2", episode = "S02E01", airDate = Date())
}