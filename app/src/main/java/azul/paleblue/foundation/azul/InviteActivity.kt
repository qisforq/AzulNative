package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class InviteActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(30)

            button("Invite Activity") {
                onClick {
                    toast("I sent an invitation!")
                }
            }
        }
    }

}
