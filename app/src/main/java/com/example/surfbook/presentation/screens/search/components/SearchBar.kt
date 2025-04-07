package com.example.surfbook.presentation.screens.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.surfbook.R
@Composable
fun SearchBar(
    searchQuery: String, // Текущий текст поиска
    onQueryChange: (String) -> Unit, // Функция для обновления текста
    onSearch: () -> Unit // Функция для выполнения поиска
) {
    val colors = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    TextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 64.dp)
            .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp),
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colors.secondary,
            unfocusedContainerColor = colors.onTertiary,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_default),
                contentDescription = "Поиск",
                tint = colors.secondary
            )
        },
        placeholder = {
            Text("Поиск",
                color = colors.secondary,
                style = typo.labelMedium
                )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch() // Выполняем поиск при нажатии на кнопку "Поиск"
            }
        )
    )
}

//@Preview
//@Composable
//fun SearchBarPreview(){
//    SearchBar(searchQuery = , onQueryChange = ) {
//
//    }
//}