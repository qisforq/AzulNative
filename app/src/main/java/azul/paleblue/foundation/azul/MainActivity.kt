package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import azul.paleblue.foundation.azul.invite.InviteActivity
import azul.paleblue.foundation.azul.wallet.WalletActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.view.MenuInflater
import android.view.MenuItem
import azul.paleblue.foundation.azul.account.LoginActivity


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

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_main, menu)

    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.getItemId()) {
      R.id.invite -> {
        startActivity<InviteActivity>()
        return true
      }
      R.id.login -> {
        startActivity<LoginActivity>()
        return true
      }
      else -> return super.onOptionsItemSelected(item)
    }

  }

}
