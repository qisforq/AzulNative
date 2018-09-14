package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import azul.paleblue.foundation.azul.invite.InviteActivity
import azul.paleblue.foundation.azul.wallet.WalletActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import android.view.MenuItem
import azul.paleblue.foundation.azul.account.LoginActivity
import azul.paleblue.foundation.azul.account.ProfileActivity
import android.content.Intent
import azul.paleblue.foundation.azul.persistence.KeyValueStore


class MainActivity : Activity(), AnkoLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    getInviteCodeFromUrl()

    verticalLayout {
      padding = dip(30)
      textView("Main Activity")

      button("Profile") {
        onClick {
          toast("Entering Profile activity")
          startActivity<ProfileActivity>()
        }
      }
      button("Wallet") {
        onClick {
          startActivity<WalletActivity>()
        }
      }
    }
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
      R.id.logout -> {
        KeyValueStore(this).clearSessionToken()
        finish()
        true
      }
      else -> return super.onOptionsItemSelected(item)
    }
  }

  fun getInviteCodeFromUrl() {
    if (Intent.ACTION_VIEW == intent.action) {
      val segments = intent.data!!.pathSegments
      if (segments.size > 1) {
        val inviteCode = segments[1]
        info("Invite code was: $inviteCode")
        if (inviteCode != null) {
          KeyValueStore(this).storeCode(inviteCode)
        }
      }
    }
  }
}
