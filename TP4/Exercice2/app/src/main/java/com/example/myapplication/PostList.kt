package com.example.myapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PostList(viewModel: MainViewModel = viewModel()) {
    val posts by viewModel.posts.collectAsState()
    LazyColumn {
        items(posts) { post ->
            Text(text = post.title)
        }
    }

    DisposableEffect(Unit) {
        viewModel.getPosts()
        onDispose {}
    }
}
