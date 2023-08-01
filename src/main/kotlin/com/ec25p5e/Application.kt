package com.ec25p5e

import com.ec25p5e.di.mainModule
import com.ec25p5e.plugins.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin

fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.183.107", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    startKoin {
        modules(mainModule)
    }

    val appConfig = HoconApplicationConfig(ConfigFactory.load())

    configureSecurity(appConfig)
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureAdministration()
    configureRouting(appConfig)
}
