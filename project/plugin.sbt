addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.2")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.11")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.7")
addSbtPlugin("com.github.scalaprops" % "sbt-scalaprops" % "0.3.2")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.28")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.6.1")

scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  "-Yno-adapted-args" ::
  Nil
)

fullResolvers ~= {_.filterNot(_.name == "jcenter")}
