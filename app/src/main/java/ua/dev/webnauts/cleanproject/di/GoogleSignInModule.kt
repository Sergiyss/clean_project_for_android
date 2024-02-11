package ua.dev.castleguide.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.dev.webnauts.cleanproject.auth.google.GoogleSignIn
import ua.dev.webnauts.cleanproject.auth.google.IGoogleSignIn
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleSignInModule {

    @Provides
    @Singleton
    fun provideGoogleSignIn(googleSignIn: GoogleSignIn): IGoogleSignIn = googleSignIn

}