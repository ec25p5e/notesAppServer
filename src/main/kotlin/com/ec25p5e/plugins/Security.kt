package com.ec25p5e.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*

fun Application.configureSecurity(appConfig: HoconApplicationConfig) {

    authentication {
        jwt {
            val jwtAudience = appConfig.property("jwt.audience").getString()
            realm = appConfig.property("jwt.realm").getString()

            verifier(
                JWT
                    .require(Algorithm.HMAC256("secret"))
                    .withAudience(jwtAudience)
                    .withIssuer(appConfig.property("jwt.domain").getString())
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }

}

val JWTPrincipal.userId: String?
    get() = getClaim("userId", String::class)