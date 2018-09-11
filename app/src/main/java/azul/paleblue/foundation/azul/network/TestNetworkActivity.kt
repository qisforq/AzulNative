package azul.paleblue.foundation.azul.network

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import azul.paleblue.foundation.azul.network.ApiClient
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TestNetworkActivity : Activity(), AnkoLogger {

    fun makeEditable(s: String): Editable {
      val f = Editable.Factory()
      return f.newEditable(s)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(30)

            val host = editText {
              hint = "Host"
              text = makeEditable("192.168.1.95")
            }
            val port = editText {
              hint = "Port"
              text = makeEditable("50051")
            }
            val name = editText {
              hint = "Message"
              text = makeEditable("Azul Wallet")
            }

            button("Invite Activity") {
                onClick {
                    toast("Sending Invite")
                    doAsync {
                        info("Sending invite")
                        val apiClient = ApiClient(host.text.toString(), Integer.parseInt(port.text.toString()))
                        val response = apiClient.sendMessage(name.text.toString())
                        info("Invite response received: $response")
                        toast(response)
                    }
                }
            }
        }
    }

}
