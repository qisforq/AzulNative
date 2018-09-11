package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.invite.InviteActivity
import azul.paleblue.foundation.azul.wallet.WalletActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : Activity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
          padding = dip(30)
          textView("Main Activity")

          button("Wallet") {
              onClick {
                  startActivity<WalletActivity>()
              }
          }

          button("Invite Friends") {
              onClick {
                  startActivity<InviteActivity>()
              }
          }
        }
    }
}
