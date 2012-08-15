//import net.thunderklaus.GwtPlugin._

name := "Vaadin, Scala and Mongo"
 
scalaVersion := "2.9.2"
 
seq(webSettings: _*)

//seq(gwtSettings: _*)

//gwtVersion := "2.4.0"

resolvers ++= Seq("TypeSafe repo" at "http://repo.typesafe.com/typesafe/releases",
				  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
				

// basic dependencies
libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin" % "6.8.2",
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container",
  "vaadin.scala" %% "scaladin" % "2.0.0-SNAPSHOT"
)

libraryDependencies ++= Seq(
	//Casbah and Salat
	"org.mongodb" % "casbah_2.9.2" % "2.4.1",		
	//"com.mongodb.casbah" % "casbah_2.9.0-1" % "2.1.5.0",
	"com.novus" %% "salat" % "1.9.1-SNAPSHOT"
)



// hack: sbt-gwt-plugin assumes that sources are in src/main/java
//javaSource in Compile <<= (scalaSource in Compile)

//gwtModules := List("vaadin.scala.example.mongo.MongoExampleWidgetset")

// more correct place would be to compile widgetset under the target dir and configure jetty to find it from there 
//gwtTemporaryPath := file(".") / "src" / "main" / "webapp" / "VAADIN" / "widgetsets"