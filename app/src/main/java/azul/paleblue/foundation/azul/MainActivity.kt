package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.wallet.WalletActivity
import azul.paleblue.foundation.azul.wallet.send.ScannerActivity
import azul.paleblue.foundation.azul.wallet.receive.RequestMoneyActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : Activity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
          padding = dip(30)
          textView("Main Activity")

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
              }
          }
          
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
