package vaadin.scala.example.mongo

import scala.reflect.BeanProperty
import scala.util.Random

case class Registration(
  @BeanProperty var username: String = "username" + Random.nextInt,
  @BeanProperty var password: String = "",
  @BeanProperty var realName: String = "Joe Tester")