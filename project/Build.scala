import sbt._
import Keys._

object BuildSettings
{

	val buildSettings = Defaults.defaultSettings ++ Seq(
			organization := "com.bryghts.kissnumber",
			version      := "0.0.2",
			scalaVersion := "2.10.1",
			scalacOptions ++= Seq(),
			publishMavenStyle := true,
			publishTo <<= version { (v: String) =>
				val nexus = "https://oss.sonatype.org/"
				if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots") 
				else                             Some("releases"  at nexus + "service/local/staging/deploy/maven2")
			},
			publishArtifact in Test := false,
//			useGpg := true,
			pomExtra := (
				<url>http://www.brights.com</url>
				<licenses>
					<license>
					<name>mit</name>
					</license>
				</licenses>
				<scm>
					<url>git@github.com:marcesquerra/KissNumber.git</url>
					<connection>scm:git:git@github.com:marcesquerra/KissNumber.git</connection>
				</scm>
				<developers>
					<developer>
						<name>Marc Esquerrà i Bayo</name>
						<email>esquerra@bryghts.com</email>
					</developer>
				</developers>
			),
			pomIncludeRepository := { _ => false }
	)

}

object MyBuild extends Build
{
	val projectName = "KissNumber"

	import BuildSettings._


	lazy val root: Project = Project(
		projectName,
		file("."),
		settings = buildSettings
	)

}