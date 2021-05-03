package com.fave.assignment.data.customException

import java.io.IOException

class NotFoundException : IOException() {

    override val message: String?
        get() = "Not Found"
}