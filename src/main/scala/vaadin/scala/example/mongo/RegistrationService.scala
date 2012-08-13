package vaadin.scala.example.mongo

import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._

object RegistrationService {
  val registrations: MongoCollection = MongoConnection()("vaadin-scala-mongo-example")("registrations")

  def all: List[Registration] = registrations.map(grater[Registration].asObject(_)).toList
  def create(registration: Registration) {
    registrations += grater[Registration].asDBObject(registration)
  }
}