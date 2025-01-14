package com.shijo.newsapp.presentation.search.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AssistChipDefaults.IconSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.ui.theme.NewsAppTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    isLoading : Boolean
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked){
        if(isClicked){
            onClick?.invoke()
        }
    }

    Box(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .searchBar(),
            value = text,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier.size(IconSize),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            placeholder = {
                Text(
                    text = "Search...",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor =MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionSource
        )
        if(isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = Dimes.PaddingMedium)
                    .size(30.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 4.dp //
            )
        }
    }
}

fun Modifier.searchBar(): Modifier = composed {
    if (!isSystemInDarkTheme()) {
        border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        this
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    NewsAppTheme {
        SearchBar(text = "", onValueChange = {}, isLoading = true, onSearch = {})
    }
}









