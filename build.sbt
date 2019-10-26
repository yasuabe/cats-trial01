name := "cats-trial01"

version := "0.1"

scalaVersion := "2.13.1"
lazy val catsVer = "2.0.0"

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

scalacOptions ++= Seq(
  "-encoding", "utf8", // Option and arguments on same line
  "-Xfatal-warnings",  // New lines for each options
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVer,
  "org.typelevel" %% "discipline-core" % "1.0.0",
  "org.typelevel" %% "discipline-scalatest" % "1.0.0-M1" % Test,
  "org.typelevel" %% "cats-laws" % catsVer % Test,
//  "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.8" % Test

)
