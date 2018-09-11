package azul.paleblue.foundation.azul.invite

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import azul.paleblue.foundation.azul.R
import azul.paleblue.foundation.azul.network.ApiClient
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.widget.TextView


class InviteActivity : FragmentActivity(), AnkoLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val model = ViewModelProviders.of(this).get(InviteViewModel::class.java!!)

    var inviteText: TextView? = null

    verticalLayout {
      padding = dip(30)

      inviteText = textView()

      button("Get Code") {
        onClick {
          toast("Disabled")
          // getInviteCode()
        }
      }

      button("Share Code") {
        onClick {
          shareInviteCode()
        }
      }
    }

    model.getInviteCode().observe(this, Observer {
      inviteText!!.text = it
    })
  }

//  fun getInviteCode() {
//    toast("Sending Invite")
//    doAsync {
//      info("Sending invite")
//      val apiClient = ApiClient()
//      val inviteCode = apiClient.generateInvite()
//      info("Invite code received: $inviteCode")
//    }
//  }

  fun shareInviteCode() {
    val model = ViewModelProviders.of(this).get(InviteViewModel::class.java!!)
    val code = model.getInviteCode().value

    val sendIntent: Intent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, "Please use this invite code to join Azul: $code")
      type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.invite_which_app)))
  }
}


class InviteViewModel : ViewModel() {
  private var inviteCode: MutableLiveData<String>? = null

  fun getInviteCode(): LiveData<String> {
    if (inviteCode == null) {
      inviteCode = MutableLiveData<String>()
      loadInviteCode()
    }
    return inviteCode as MutableLiveData<String>
  }

  private fun loadInviteCode() {
    val apiClient = ApiClient()
    inviteCode!!.value = apiClient.generateInvite()
  }
}