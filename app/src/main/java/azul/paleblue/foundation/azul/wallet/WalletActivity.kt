package azul.paleblue.foundation.azul.wallet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.R
import azul.paleblue.foundation.azul.wallet.receive.RequestMoneyActivity
import azul.paleblue.foundation.azul.wallet.send.SendMoneyActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import android.widget.ArrayAdapter
import azul.paleblue.foundation.azul.AuthActivity
import azul.paleblue.foundation.azul.account.ProfileActivity
import azul.paleblue.foundation.azul.invite.InviteActivity
import azul.paleblue.foundation.azul.persistence.KeyValueStore
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

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when (item?.getItemId()) {
      R.id.invite -> {
        startActivity<InviteActivity>()
        true
      }
      R.id.profile -> {
        startActivity<ProfileActivity>()
        true
      }
      R.id.logout -> {
        KeyValueStore(this).clearSessionToken()
        val logoutIntent = Intent(this, AuthActivity::class.java)
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(logoutIntent)
        true
      }
      else -> return super.onOptionsItemSelected(item)
    }
  }
}
