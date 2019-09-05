package login

interface GoogleUser {
    val googleId: String
    fun getAuthResponse(): AuthResponse
}
