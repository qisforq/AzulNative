package azul.paleblue.foundation.azul.account

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.invite.RedeemInviteModel
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class LoginActivity: Activity(), AnkoLogger {
  lateinit var model: AccountModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val app = application as AzulApplication
    model = app.accountModel

    verticalLayout {
      padding = dip(30)

      textView("Login")

      val username = editText {
        hint = "Name"
        textSize = 24f
      }
      val password = editText {
        hint = "Password"
        textSize = 24f
      }

      button("Login") {
        onClick {
          info("Perform Login")
          model.login(
                  username.text.toString(),
                  password.text.toString()
          )
        }
      }

    }
  }
}
