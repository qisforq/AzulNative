package azul.paleblue.foundation.azul.account

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.network.ApiClient
import com.google.android.gms.tasks.OnSuccessListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import azul.paleblue.foundation.azul.location.CurrentLocationGetter


class RedeemInviteActivity: Activity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationGetter = CurrentLocationGetter(this)

        verticalLayout {
            padding = dip(30)
            val username = editText {
                hint = "Name"
                textSize = 24f
            }
            val password = editText {
                hint = "Password"
                textSize = 24f
            }
            val inviteCode = editText {
                setText("QR9q9XwbwrPd5TGq")
                hint = "Invite Code"
                textSize = 24f
            }
            button("Register") {
                textSize = 26f
                onClick {
                    locationGetter.getCurrentLocation(OnSuccessListener {
                        toast("location retrieved")
                        it?.let {
                            println(it.latitude)
                            println(it.longitude)
                            println(inviteCode.text.toString())
                            println(username.text.toString())
                            println(password.text.toString())

                            doAsync {
                                val apiClient = ApiClient("192.168.1.95", 50051)
                                val response = apiClient.redeemInvite(inviteCode.text.toString(), it)
                                info("response: $response")
                            }
                        }
                    })
                }
            }
        }
    }
}