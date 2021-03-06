package vaadin.scala.example.mongo.min

import vaadin.scala._
import com.mongodb.casbah.Imports._
import com.novus.salat.grater
import com.novus.salat.global._
import scala.util.Random
import scala.reflect.BeanProperty

class MongoExample extends Application("Mongo & Vaadin, tied together with Scala") {

  val registrations: MongoCollection = MongoConnection()("vaadin-scala-mongo-example")("registrations")
  def mapRegistrations: List[Registration] = registrations.map(grater[Registration].asObject(_)).toList
  def saveRegistration(registration: Registration): Unit = registrations += grater[Registration].asDBObject(registration)

  override val main: ComponentContainer = new VerticalLayout {
    sizeFull()
    styleName = Reindeer.LayoutWhite

    val tableLayout = new VerticalLayout {
      size(50 pct, 50 pct)
      spacing = true

      val table = new Table {
        sizeFull()
        styleNames += (Reindeer.TableBorderless, Reindeer.TableStrong)
        container = new BeanItemContainer(mapRegistrations)
        visibleColumns = Seq("username", "realName")
      }

      val addButton = Button("Register", showForm)
      components += (table, addButton)
    }

    lazy val form = new Form {
      size(50 pct, 50 pct)
      caption = "Registration"
      writeThrough = false
      formFieldFactory = registrationFormFieldFactory
      footer = new HorizontalLayout {
        components += Button("Save", showList)
      }
    }

    components += tableLayout
    alignment(tableLayout -> Alignment.MiddleCenter)

    def showForm(): Unit = {
      form.item = new BeanItem(Registration())
      form.visibleItemProperties = Seq("realName", "username", "password")
      form.addField(Option("confirmation"), form.formFieldFactory.flatMap(_.createField(FormFieldIngredients(form.item.get, "confirmation", form))))
      replaceComponent(tableLayout, form)
      alignment(form -> Alignment.MiddleCenter)
    }

    def showList(): Unit = {
      if (form.commit.isValid) { //form handles error
        val bean = form.item.get.asInstanceOf[BeanItem[Registration]].bean
        saveRegistration(bean)
        tableLayout.table.container = new BeanItemContainer(mapRegistrations)
        tableLayout.table.visibleColumns = Seq("username", "realName")
        replaceComponent(form, tableLayout)
        alignment(tableLayout -> Alignment.MiddleCenter)
        mainWindow.showNotification("User %s registered".format(bean.username))
      }
    }
  }

  val registrationFormFieldFactory = FormFieldFactory(ing => {
    var field: Option[Field] = ing match {
      case FormFieldIngredients(_, "password", _) =>
        Some(new PasswordField {
          caption = DefaultFieldFactory.createCaptionByPropertyId("password")
        })

      case FormFieldIngredients(_, "confirmation", form: Form) =>
        Some(new PasswordField {
          caption = "Confirm password"
          validators += Validator(value => {
            if (value == form.field("password").get.value) Valid
            else Invalid(List("Passwords must match"))
          })
        })

      case otherIngredient => {
        DefaultFieldFactory.createField(otherIngredient)
      }
    }

    field.foreach(_.required = true)
    field.foreach(f => f.requiredError = "%s is required".format(f.caption.get))
    field
  })
}

case class Registration(
  @BeanProperty var username: String = "username" + Random.nextInt,
  @BeanProperty var password: String = "",
  @BeanProperty var realName: String = "Joe Tester")