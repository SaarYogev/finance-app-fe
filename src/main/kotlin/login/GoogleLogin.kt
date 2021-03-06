@file:JsModule("react-google-login")

package login

import react.RClass
import react.RProps

@JsName("default")
external val GoogleLogin: RClass<GoogleLoginProps>

external interface GoogleLoginProps: RProps {
    var clientId: String
    var buttonText: String?
    var onSuccess: (GoogleUser) -> Unit
    var onFailure: (GoogleLoginFailureDetails) -> Unit
    var cookiePolicy: () -> String?
}
