package azul.paleblue.foundation.azul.invite

import android.arch.lifecycle.Observer
import android.content.Intent
import android.widget.TextView
import azul.paleblue.foundation.azul.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class InviteView(val viewModel: InviteViewModel) : AnkoComponent<InviteActivity> {

  override fun createView(ui: AnkoContext<InviteActivity>) = with(ui) {
    var inviteText: TextView? = null
    viewModel.inviteCode.observe(ui.owner, Observer {
      inviteText!!.text = it
    })

    fun shareInviteCode() {
      val code = viewModel.inviteCode.value
      val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Please use this invite code to join Azul: $code")
        type = "text/plain"
      }
      ui.owner.startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.invite_which_app)))
    }

    verticalLayout {
      padding = dip(30)

      linearLayout {
        textView("Invite Code:")
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
  }
}