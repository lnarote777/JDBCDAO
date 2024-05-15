package org.example.output

import org.example.output.IOuputInfo

class Console: IOuputInfo {
    override fun showMessage(message: String, line: Boolean) {
        if (line) println(message) else print(message)
    }
}