package com.demate.laucherstupid.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun ensureSignedIn() {
        // If there's already a user, nothing to do
        val current = auth.currentUser
        if (current != null) return
        // Try anonymous sign-in
        auth.signInAnonymously().await()
    }
}

