package ua.dev.webnauts.cleanproject.database.room

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long = 0,
    val token: String,
    val resetToken :String,
    val userNiceName: String,
    val userFirstName : String,
    val userFastName : String,
    val userFullName : String,
    val userPhone : String,
    val userEmail: String,
    val language : String,
)

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE userId = :userId")
    fun getUserById(userId: Long): User?

    @Query("SELECT * FROM user_table WHERE userEmail = :email")
    fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM user_table")
    fun getUserProfiles(): User?

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Update
    fun updateUserProfile(user: User)
}