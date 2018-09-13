package azul.paleblue.foundation.azul.account

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class AuthActivity: Activity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(30)

            button("Login") {
                onClick {
                    startActivity<LoginActivity>()
                }
            }
            button("Create Account") {
                onClick {
                    startActivity<RedeemInviteActivity>()
                }
            }

        }
    }
}
