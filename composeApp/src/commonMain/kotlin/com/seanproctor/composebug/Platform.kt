package com.seanproctor.composebug

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform