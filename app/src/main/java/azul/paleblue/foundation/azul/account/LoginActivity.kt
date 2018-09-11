package azul.paleblue.foundation.azul.account

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity: Activity(), AnkoLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    verticalLayout {
      padding = dip(30)

      textView("Login")

      editText {
        hint = "Name"
        textSize = 24f
      }
      editText {
        hint = "Password"
        textSize = 24f
      }

      button("Login") {
        onClick {
          info("Perform Login")
          // TODO: Call API from here
        }
      }

    }
  }
}
