package index

import login.app
import kotlinext.js.require
import kotlinext.js.requireAll
import react.dom.render
import kotlin.browser.document

fun main(args: Array<String>) {
    requireAll(require.context("kotlin", true, js("/\\.css$/")))

    render(document.getElementById("root")) {
        app()
    }
}
