package vaadin.scala.example.mongo.full

import vaadin.scala._

import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._

class MongoExample extends Application("Mongo & Vaadin, tied together with Scala") {

  val service: RegistrationService = RegistrationService

  override val main: ComponentContainer = new HorizontalLayout {
    sizeFull()
    styleName = Reindeer.LayoutWhite

    val tableLayout = new VerticalLayout {
      size(50 pct, 50 pct)
      spacing = true

      val table = new RegistrationTable {
        sizeFull()
        container = new BeanItemContainer(service.all)
      }

      val addButton = Button("Register", showForm)

      components += (table, addButton)
    }

    val form = new RegistrationForm {
      size(50 pct, 50 pct)

      footer = new HorizontalLayout {
        components += Button("Save", showList)
      }
    }

    components += tableLayout

    alignment(tableLayout -> Alignment.MiddleCenter)

    def showForm(): Unit = {
      form.item = new BeanItem(Registration())
      replaceComponent(tableLayout, form)
      alignment(form -> Alignment.MiddleCenter)
    }

    def showList(): Unit = {
        if(form.commit.isValid) { //form handles error
        val bean = form.item.get.bean
        service.create(bean)
        tableLayout.table.container = new BeanItemContainer(service.all)
        replaceComponent(form, tableLayout)
        alignment(tableLayout -> Alignment.MiddleCenter)
        mainWindow.showNotification("User %s registered".format(bean.username))
      } 
    }
  }
}