package vaadin.scala.example.mongo.full

import vaadin.scala._

class RegistrationForm extends Form {
  caption = "Registration"
  formFieldFactory = RegistrationFormFieldFactory
  writeThrough = false

  override def item_=(item: Item) = {
    require(item.isInstanceOf[BeanItem[Registration]])
    super.item = item
    visibleItemProperties = Seq("realName", "username", "password")

    addField(Option("confirmation"), formFieldFactory.flatMap(_.createField(FormFieldIngredients(item, "confirmation", this))))
  }

  override def item: Option[BeanItem[Registration]] = super.item.map(_.asInstanceOf[BeanItem[Registration]])
}