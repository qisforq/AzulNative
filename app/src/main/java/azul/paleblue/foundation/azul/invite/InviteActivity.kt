package azul.paleblue.foundation.azul.invite

import android.os.Bundle
import org.jetbrains.anko.*
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.R

class InviteActivity : FragmentActivity(), AnkoLogger {

  lateinit var model: InviteModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val app = application as AzulApplication
    model = app.inviteModel

    val viewModel = ViewModelProviders.of(this).get(InviteViewModel::class.java)
    viewModel.model = model

    setContentView(R.layout.activity_invite)

    // val ui = InviteView(viewModel)
    // ui.setContentView(this)
  }
}
