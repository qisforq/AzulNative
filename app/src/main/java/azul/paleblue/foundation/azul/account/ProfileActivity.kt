package azul.paleblue.foundation.azul.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import azul.paleblue.foundation.azul.AuthActivity
import azul.paleblue.foundation.azul.R
import org.jetbrains.anko.*
import azul.paleblue.foundation.azul.location.CurrentLocationGetter
import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.persistence.KeyValueStore
import com.google.android.gms.tasks.OnSuccessListener
import org.jetbrains.anko.sdk15.coroutines.onClick


class ProfileActivity : Activity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationGetter = CurrentLocationGetter(this)
        val store = KeyValueStore(this)
        val logoutIntent = Intent(this, AuthActivity::class.java)
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        verticalLayout {
            padding = dip(30)
            textView("${R.string.text_token_session} ${store.getSessionToken()}" )
            button(R.string.btn_send_location) {
                onClick {
                    locationGetter.getCurrentLocation(OnSuccessListener {
                        toast("location retrieved")
                        if (it != null) {
                            println(it.latitude)
                            println(it.longitude)
                            doAsync {
                                //                              val apiClient = ApiClient("192.168.1.95", 50051)
//                              val response = apiClient.redeemInvite("12345", it)
//                              info("response: $response")
                            }
                        } else {
                            println("No location data available")
                        }
                    })
                }
            }
            button(R.string.btn_logout) {
                onClick {
                    store.clearSessionToken()
                    startActivity(logoutIntent)
                }
            }
        }
    }
}
