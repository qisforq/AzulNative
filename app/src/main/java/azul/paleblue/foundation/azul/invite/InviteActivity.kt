package azul.paleblue.foundation.azul.invite

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import azul.paleblue.foundation.azul.R
import azul.paleblue.foundation.azul.network.ApiClient
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class InviteActivity : Activity(), AnkoLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    verticalLayout {
      padding = dip(30)

      val inviteText = textView()

      button("Get Code") {
        onClick {
          getInviteCode()
        }
      }

      button("Share Code") {
        onClick {
          shareInviteCode()
        }
      }
    }
  }

  fun getInviteCode() {
    toast("Sending Invite")
    doAsync {
      info("Sending invite")
      val apiClient = ApiClient()
      val inviteCode = apiClient.generateInvite()
      info("Invite code received: $inviteCode")
      inviteText.text = inviteCode
    }
  }

  fun shareInviteCode() {
    val sendIntent: Intent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
      type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.invite_which_app)))
  }
}


class MyViewModel : ViewModel() {
  private var users: MutableLiveData<List<User>>? = null

  fun getUsers(): LiveData<List<User>> {
    if (users == null) {
      users = MutableLiveData<List<User>>()
      loadUsers()
    }
    return users
  }

  private fun loadUsers() {
    // Do an asynchronous operation to fetch users.
  }
}
