package com.fave.assignment.data.customException

import java.lang.Exception

class UnKnownException : Exception() {

    override val message: String?
        get() = "Some Unknown Error Occurred"
}