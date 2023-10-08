package ua.dev.webnauts.cleanproject.database.datastore


import ua.dev.webnauts.cleanproject.SettingsProto
import ua.dev.webnauts.cleanproject.database.datastore.data.TokenData
import ua.dev.webnauts.cleanproject.database.datastore.data.UserData

internal fun SettingsProto.TokenData.toTokenData() =
    TokenData(
        this.accessToken,
        this.refreshToken
    )

internal fun TokenData.toSettingsTokenData() =
    SettingsProto.TokenData.newBuilder()
        .setAccessToken(this.accessToken?:"")
        .setRefreshToken(this.refreshToken?:"")
        .build()




internal fun SettingsProto.UserData.toUserData() =
    UserData(
        id = this.id,
        fullName = this.fullName,
        email = this.email,
        phone = this.phone,
        avatar = this.avatar,
        lang = this.lang
    )

internal fun UserData.toSettingsUserData() =
    SettingsProto.UserData.newBuilder()
        .setId(this.id)
        .setFullName(this.fullName)
        .setEmail(this.email)
        .setPhone(this.phone)
        .setAvatar(this.avatar)
        .setLang(this.lang)
        .build()
