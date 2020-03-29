package dev.gressier.vertx.first

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.http.HttpHeaders

class MainVerticle : AbstractVerticle() {

    override fun start(startPromise: Promise<Void>) {
        vertx
            .createHttpServer()
            .requestHandler { req ->
                req.response()
                    .putHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
                    .end("Hello from Vert.x!")
            }
            .listen(8888) { http ->
                when {
                    http.succeeded() -> {
                        startPromise.complete()
                        println("HTTP server started on port 8888")
                    }
                    else -> startPromise.fail(http.cause())
                }
            }
    }
}
