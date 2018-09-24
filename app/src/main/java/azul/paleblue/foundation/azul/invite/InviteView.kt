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
        putExtra(Intent.EXTRA_TEXT, "${R.string.text_share_invite} $code")
        type = "text/plain"
      }
      ui.owner.startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.text_invite_which_app)))
    }

    verticalLayout {
      padding = dip(30)

      linearLayout {
        textView(R.string.text_invite_code)
        inviteText = textView(viewModel.inviteCode.value)
      }

      button(R.string.btn_get_code) {
        onClick {
          toast("Disabled")
          //getInviteCode()
        }
      }

      button(R.string.btn_share_code) {
        onClick {
          shareInviteCode()
        }
      }
    }
  }
}