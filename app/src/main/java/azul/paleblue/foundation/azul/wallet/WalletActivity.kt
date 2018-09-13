package azul.paleblue.foundation.azul.wallet

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.TextView
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.wallet.receive.RequestMoneyActivity
import azul.paleblue.foundation.azul.wallet.send.ScannerActivity
import azul.paleblue.foundation.azul.wallet.send.SendMoneyActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class WalletActivity : FragmentActivity() {
  
    lateinit var model: WalletModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as AzulApplication
        model = app.walletModel
    
//        val viewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
//        viewModel.model = model

      var currentBalance: TextView? = null

        verticalLayout {
          padding = dip(30)
          
          textView("Wallet Activity")
          
          linearLayout {
            textView("Current Balance:")
            currentBalance = textView()
          }

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

        currentBalance!!.text = "1M BTC"
    }
}
