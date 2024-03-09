package fr.thomasbernard03.rickandmorty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.thomasbernard03.rickandmorty.commons.extensions.toColor
import fr.thomasbernard03.rickandmorty.commons.extensions.toText
import fr.thomasbernard03.rickandmorty.domain.models.Gender
import fr.thomasbernard03.rickandmorty.domain.models.Status
import fr.thomasbernard03.rickandmorty.presentation.theme.Dark100

@Composable
fun CharactersItem(
    modifier : Modifier = Modifier,
    name: String,
    status : Status,
    species : String,
    image : String,
    gender : Gender,
    onClick : () -> Unit = { }
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
        AsyncImage(
            modifier = Modifier
                .size(120.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = "Character Image",
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(status.toColor(), shape = CircleShape).size(10.dp))

                Text(
                    text = "${status.toText()} - $species",
                    style = MaterialTheme.typography.titleSmall)
            }

            Text(
                text = gender.toText(),
                style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Preview
@Composable
private fun CharactersItemPreview() = PreviewComponents {
    CharactersItem(
        modifier = Modifier.fillMaxWidth(),
        name = "Rick",
        status = Status.Alive,
        species = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        gender = Gender.Male
    )
}