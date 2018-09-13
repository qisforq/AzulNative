package azul.paleblue.foundation.azul.wallet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Button
import android.widget.ListView
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.R
import azul.paleblue.foundation.azul.wallet.receive.RequestMoneyActivity
import azul.paleblue.foundation.azul.wallet.send.SendMoneyActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import android.widget.ArrayAdapter
import azul.paleblue.foundation.azul.wallet.history.TransactionHistoryActivity


class WalletActivity : FragmentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
    val app = application as AzulApplication
    viewModel.model = app.walletModel

    setContentView(R.layout.activity_wallet)

    find<Button>(R.id.transactionHistory).onClick {
      startActivity<TransactionHistoryActivity>()
    }

    find<Button>(R.id.requestMoney).onClick {
      startActivity<RequestMoneyActivity>()
    }

    find<Button>(R.id.sendMoney).onClick {
      startActivity<SendMoneyActivity>()
    }

    val balanceList = find<ListView>(R.id.balanceContainer)
    val adapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1, 
        android.R.id.text1, 
        viewModel.balance.value?.currencies ?: mutableListOf())
    balanceList.adapter = adapter

    viewModel.balance.observe(this, Observer { account ->
      adapter.clear()
      adapter.addAll(account!!.currencies)
      adapter.notifyDataSetChanged()
    })
  }
}
