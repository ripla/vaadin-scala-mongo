package vaadin.scala.example.mongo.full

import vaadin.scala._

class RegistrationForm extends Form {
  caption = "Registration"
  formFieldFactory = RegistrationFormFieldFactory

  override def item_=(item: Item) = {
    require(item.isInstanceOf[BeanItem[Registration]])
    super.item = item
    visibleItemProperties = Seq("realName", "username", "password")

    addField("confirmation", formFieldFactory.get.createField(FormFieldIngredients(item, "confirmation", this)).get)
  }

  override def item: Option[BeanItem[Registration]] = super.item.map(_.asInstanceOf[BeanItem[Registration]])
}