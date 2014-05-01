import com.typesafe.sbt.web.SbtWeb

name := "similarity"

organization := "org.allenai"

scalaVersion := "2.10.4"

// `core` contains models and services for running the application.
val core = project in file("core")

// `webclient` project does not have any dependencies on other
// projects. It is simply meant for building an AngularJS web
// application which can be served up by the webserver.
val webclient = (project in file("webclient")).addPlugins(SbtWeb)

// `webserver` has two roles: serving up the `webclient`'s assets, and
// serving up a JSON API for our `core`'s services.
val webserver = (project in file("webserver")).dependsOn(core, webclient)

val root = (project in file(".")).aggregate(core, webclient, webserver)
