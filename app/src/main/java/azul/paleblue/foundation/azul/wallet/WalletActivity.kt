package azul.paleblue.foundation.azul.wallet

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.wallet.receive.RequestMoneyActivity
import azul.paleblue.foundation.azul.wallet.send.ScannerActivity
import azul.paleblue.foundation.azul.wallet.send.SendMoneyActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class WalletActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
          padding = dip(30)
          
          textView("Wallet Activity")

          button("Transaction History") {
              onClick {
                  startActivity<TransactionHistoryActivity>()
              }
          }
          button("Request Money") {
              onClick {
                  startActivity<RequestMoneyActivity>()
              }
          }
          button("Send Money") {
              onClick {
                  startActivity<SendMoneyActivity>()
              }
          }
        }

    }
}
