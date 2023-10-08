package ua.dev.webnauts.cleanproject.database.datastore.data

data class UserData(
    val id: Int,
    val fullName: String,
    val email: String,
    val phone: String,
    val avatar: String,
    val lang: String
)
