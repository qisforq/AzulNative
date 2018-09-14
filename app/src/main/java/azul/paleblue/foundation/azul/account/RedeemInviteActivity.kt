package azul.paleblue.foundation.azul.account

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.invite.RedeemInviteModel
import azul.paleblue.foundation.azul.network.ApiClient
import com.google.android.gms.tasks.OnSuccessListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import azul.paleblue.foundation.azul.location.CurrentLocationGetter


class RedeemInviteActivity : Activity(), AnkoLogger {
  lateinit var model: RedeemInviteModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val app = application as AzulApplication
    model = app.redeemInviteModel

    verticalLayout {
      padding = dip(30)
      val username = editText {
        hint = "Name"
        textSize = 24f
        maxLines = 1
      }
      val password = editText {
        hint = "Password"
        textSize = 24f
        maxLines = 1
      }
      val inviteCode = editText {
        setText(model.inviteCode)
        hint = "Invite Code"
        textSize = 24f
        maxLines = 1
      }
      button("Register") {
        textSize = 26f
        onClick {
          model.redeemInvite(
              username.text.toString(),
              password.text.toString())
        }
      }
    }
  }

}
