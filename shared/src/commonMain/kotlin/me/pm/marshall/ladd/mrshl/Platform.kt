package me.pm.marshall.ladd.mrshl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
