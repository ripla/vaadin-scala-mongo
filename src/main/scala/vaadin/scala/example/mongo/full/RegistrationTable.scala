package vaadin.scala.example.mongo.full

import vaadin.scala._

class RegistrationTable extends Table {
  styleNames += (Reindeer.TABLE_BORDERLESS, Reindeer.TABLE_STRONG)

  override def container_=(container: Container) = {
    require(container.isInstanceOf[BeanItemContainer[Registration]])
    super.container = container
    visibleColumns = Seq("username", "realName")
  }
}