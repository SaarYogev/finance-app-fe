package index

import kotlinext.js.require
import kotlinext.js.requireAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import login.app
import react.dom.render
import kotlin.browser.document

@ExperimentalCoroutinesApi
fun main() {
    requireAll(require.context("kotlin", true, js("/\\.css$/")))

    render(document.getElementById("root")) {
        app()
    }
}
