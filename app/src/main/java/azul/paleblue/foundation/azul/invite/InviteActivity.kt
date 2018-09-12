package azul.paleblue.foundation.azul.invite

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import azul.paleblue.foundation.azul.R
import azul.paleblue.foundation.azul.network.ApiClient
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.widget.TextView
import azul.paleblue.foundation.azul.AzulApplication


class InviteActivity : FragmentActivity(), AnkoLogger {

  lateinit var model: InviteFriendsModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val app = application as AzulApplication
    model = app.inviteFriendsModel

    val viewModel = ViewModelProviders.of(this).get(InviteFriendsViewModel::class.java)
    viewModel.model = model

    var inviteText: TextView? = null

    verticalLayout {
      padding = dip(30)

      linearLayout {
        textView("Invite Code:")
        //inviteText = textView("Placeholder Code")
        inviteText = textView(viewModel.inviteCode.value)
      }

      button("Get Code") {
        onClick {
          toast("Disabled")
          //getInviteCode()
        }
      }

      button("Share Code") {
        onClick {
          shareInviteCode()
        }
      }
    }

    viewModel.inviteCode.observe(this, Observer {
      inviteText!!.text = it
    })
  }

  fun shareInviteCode() {
    val model = ViewModelProviders.of(this).get(InviteFriendsViewModel::class.java)
    val code = model.inviteCode.value

    val sendIntent: Intent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, "Please use this invite code to join Azul: $code")
      type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.invite_which_app)))
  }
}


