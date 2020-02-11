name := """recipes-api"""
organization := "com.douglasjara"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

libraryDependencies += guice

enablePlugins(PlayEbean)
libraryDependencies += evolutions
libraryDependencies += jdbc
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
