package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import azul.paleblue.foundation.azul.account.LoginActivity
import azul.paleblue.foundation.azul.account.RedeemInviteActivity
import azul.paleblue.foundation.azul.persistence.KeyValueStore
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class AuthActivity: Activity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (KeyValueStore(this).getSessionToken() != null) {
            startActivity<MainActivity>()
        } else {
            verticalLayout {
                padding = dip(30)

                button(R.string.btn_login) {
                    onClick {
                        startActivity<LoginActivity>()
                    }
                }
                button(R.string.btn_register) {
                    onClick {
                        startActivity<RedeemInviteActivity>()
                    }
                }

            }
        }
    }
}

