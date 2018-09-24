package azul.paleblue.foundation.azul.account

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.MainActivity
import azul.paleblue.foundation.azul.R
import azul.paleblue.foundation.azul.account.AccountModel
import azul.paleblue.foundation.azul.network.ApiClient
import com.google.android.gms.tasks.OnSuccessListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import azul.paleblue.foundation.azul.location.CurrentLocationGetter


class RedeemInviteActivity : Activity(), AnkoLogger {
  lateinit var model: AccountModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val app = application as AzulApplication
    model = app.accountModel

    verticalLayout {
      padding = dip(30)
      val username = editText {
        hint = getString(R.string.hint_name)
        textSize = 24f
        maxLines = 1
      }
      val password = editText(InputConstraints.PASSWORD) {
        hint = getString(R.string.hint_password)
        textSize = 24f
        maxLines = 1
      }
      val inviteCode = editText {
        hint = getString(R.string.hint_invite_code)
        textSize = 24f
        maxLines = 1
      }
      button(R.string.btn_register) {
        textSize = 26f
        onClick {
          doAsync {
            val error = model.register(
                    username.text.toString(),
                    password.text.toString())
            uiThread {
              if (error != null) {
                toast(error)
              } else {
                toast("Login Succeeded")
                startActivity<MainActivity>()
              }
            }
          }
        }
      }
    }
  }

}
