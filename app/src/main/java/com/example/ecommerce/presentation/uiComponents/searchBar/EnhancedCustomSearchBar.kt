package com.example.ecommerce.presentation.uiComponents.searchBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R
import com.example.ecommerce.domain.model.SearchSuggestion
import com.example.ecommerce.domain.model.SuggestionType
import com.example.ecommerce.presentation.theme.Black
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun EnhancedCustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    suggestions: List<SearchSuggestion>,
    onSuggestionClick: (SearchSuggestion) -> Unit,
    searchHistory: List<String> =emptyList(),
    onClearHistory: (() -> Unit)? = null,
    onClearHistoryItem: ((String) -> Unit)? = null,
    onExpandedChange:(Boolean)-> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier,
) {

    var showSuggestions by remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }

    // Track if we should show keyboard after suggestion selection
    var shouldShowKeyboardAfterSuggestion by remember { mutableStateOf(false) }

    LaunchedEffect(expanded) {
        if (expanded) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    // Close suggestions when query is cleared
    LaunchedEffect(query) {
        if (query.isEmpty() && suggestions.isEmpty()) {
            showSuggestions = false
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // Custom Search Input Field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Gray, RoundedCornerShape(28.dp))
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.surface)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onExpandedChange(true)
                    showSuggestions = true
                    shouldShowKeyboardAfterSuggestion = true
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.width(12.dp))

                BasicTextField(
                    value = query,
                    onValueChange = { newValue ->
                        onQueryChange(newValue)
                        showSuggestions = true
                        if (newValue.isNotEmpty()) {
                            onExpandedChange(true)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                onExpandedChange(true)

                                showSuggestions = true
                                shouldShowKeyboardAfterSuggestion = true
                            }
                        },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (query.isNotEmpty()) {
                                onSearch(query)
                                onExpandedChange(false)

                                showSuggestions = false
                                keyboardController?.hide()
                            }
                        }
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            if (query.isEmpty()) {
                                Text(
                                    text = "Search for products, brands, and more",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                if (query.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear search",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onQueryChange("")
                                showSuggestions = true
                                focusRequester.requestFocus()
                                keyboardController?.show()
                            },
                        tint = Color.Gray
                    )
                }
            }
        }

        // Suggestions Box
        if (expanded && showSuggestions) {
            AnimatedVisibility(
                visible = suggestions.isNotEmpty(),
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 400.dp)
                    ) {
                        // Group suggestions by type
                        val groupedSuggestions = suggestions.groupBy { it.type }

                        groupedSuggestions.forEach { (type, typeSuggestions) ->
                            if (typeSuggestions.isNotEmpty()) {
                                // Header for each suggestion type with clear option for history
                                item {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = when (type) {
                                                SuggestionType.HISTORY -> "Recent Searches"
                                                SuggestionType.TRENDING -> "Trending Now"
                                                SuggestionType.RECOMMENDED -> "Recommended For You"
                                                SuggestionType.CATEGORY -> "Categories"
                                            },
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                color = Color.Gray,
                                            )
                                        )

                                        // Clear button for history
                                        if (type == SuggestionType.HISTORY && onClearHistory != null) {
                                            TextButton(
                                                onClick = onClearHistory,
                                                // modifier = Modifier.height(32.dp)
                                            ) {
                                                Text(
                                                    text = "Clear all",
                                                    fontSize = 12.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        }
                                    }
                                }

                                // Suggestions for this type
                                items(typeSuggestions) { suggestion ->
                                    SearchSuggestionItem(
                                        suggestion = suggestion,
                                        onClick = {
                                            onSuggestionClick(suggestion)
                                            onExpandedChange(false)
                                            showSuggestions = false
                                            if (shouldShowKeyboardAfterSuggestion) {
                                                keyboardController?.hide()
                                            }
                                        },
                                        onClearItem = if (type == SuggestionType.HISTORY && onClearHistoryItem != null) {
                                            { onClearHistoryItem(suggestion.text) }
                                        } else {
                                            null
                                        }
                                    )

                                    // Add divider except for last item
                                    if (typeSuggestions.last() != suggestion) {
                                        HorizontalDivider(
                                            modifier = Modifier.padding(start = 56.dp, end = 16.dp),
                                            thickness = 0.5.dp,
                                            color = Color.LightGray.copy(alpha = 0.5f)
                                        )
                                    }
                                }
                            }
                        }

                        // Empty state if no suggestions but search is active
                        if (suggestions.isEmpty() && query.isNotEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No results found",
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter Section - Only show when not in search mode
        if (!expanded) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "All Featured",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Black
                    )
                )

                Row {
                    FilterChip(
                        selected = true,
                        onClick = {},
                        label = { Text("Sort") },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.sort_card_icon),
                                contentDescription = "Sort products"
                            )
                        },
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    FilterChip(
                        selected = true,
                        onClick = {},
                        label = { Text("Filter") },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.outline_filter_alt_24),
                                contentDescription = "Filter products"
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun SearchSuggestionItem(
    suggestion: SearchSuggestion,
    onClick: () -> Unit,
    onClearItem: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        // Icon based on suggestion type
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            when (suggestion.type) {
                SuggestionType.HISTORY -> Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "Recent search",
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray
                )
                SuggestionType.TRENDING -> Icon(
                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                    contentDescription = "Trending search",
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFF4285F4)
                )
                SuggestionType.RECOMMENDED -> Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Recommended search",
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFFFFA000)
                )
                SuggestionType.CATEGORY -> Icon(
                    imageVector = Icons.Default.Category,
                    contentDescription = "Category search",
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = suggestion.text,
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.weight(1f)
        )

        // Show search count for trending searches
        if (suggestion.type == SuggestionType.TRENDING && suggestion.searchCount > 0) {
            Text(
                text = "${suggestion.searchCount}",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray,
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        // Clear button for individual history items
        if (suggestion.type == SuggestionType.HISTORY && onClearItem != null) {
            IconButton(
                onClick = onClearItem,
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove from history",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}