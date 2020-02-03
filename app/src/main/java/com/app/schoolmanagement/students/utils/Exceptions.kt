package com.app.schoolmanagement.students.utils

import java.io.IOException

class ApiException(msg:String):IOException(msg)
class NoInternetException(msg: String) : IOException(msg)