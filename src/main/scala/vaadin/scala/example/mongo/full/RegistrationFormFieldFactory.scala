package vaadin.scala.example.mongo.full

import vaadin.scala._

object RegistrationFormFieldFactory extends FormFieldFactory {

  val PasswordFieldId = "password"

  def createField(ing: FormFieldIngredients) = {
    val field = ing match {
      case FormFieldIngredients(_, PasswordFieldId, _) =>
        Some(new PasswordField {
          caption = DefaultFieldFactory.createCaptionByPropertyId(PasswordFieldId)
        })

      case FormFieldIngredients(_, "confirmation", form: Form) =>
        Some(new PasswordField {
          caption = "Confirm password"
          validators += Validator(value => {
            if (value == form.field("password").get.value) Valid
            else Invalid("Passwords must match")
          })
        })

      case otherIngredient =>
        DefaultFieldFactory.createField(otherIngredient)
    }

    field.foreach(_.required = true)
    field.foreach(f => f.requiredError = "%s is required".format(f.caption.get))
    field
  }
}