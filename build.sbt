name := """recipes-api"""
organization := "com.douglasjara"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

libraryDependencies += guice

enablePlugins(PlayEbean)
libraryDependencies ++= Seq(evolutions, jdbc, ehcache,
                           "com.h2database" % "h2" % "1.4.194",
                           "org.postgresql" % "postgresql" % "42.2.10",
                           "mysql" % "mysql-connector-java" % "5.1.41",
                           )
