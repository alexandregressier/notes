package dev.gressier.vertx.first

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonObject

class GreetingVerticle : AbstractVerticle() {

    override fun start(startPromise: Promise<Void>) {
        vertx
            .createHttpServer()
            .requestHandler { req ->
                req.response()
                    .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .end(
                        JsonObject()
                            .put("message", "Hello, world!")
                            .encode()
                    )
            }
            .listen(8889) { http ->
                when {
                    http.succeeded() -> {
                        startPromise.complete()
                        println("HTTP server started on port 8889")
                    }
                    else -> startPromise.fail(http.cause())
                }
            }
    }
}

fun main() {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(GreetingVerticle())
}
