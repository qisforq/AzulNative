package azul.paleblue.foundation.azul.persistence

import android.content.Context

val prefsPath = "foundation.paleblue.azul"

class InviteCodeStorage(val context: Context) {

  val inviteKey  = "INVITE_CODE"
  val sessionKey = "SESSION"

  val preferences = context.getSharedPreferences(prefsPath, Context.MODE_PRIVATE)

  fun storeCode(code: String) {
    preferences.edit().putString(inviteKey, code).apply()
  }

  fun getCode(): String? {
    return preferences.getString(inviteKey, null)
  }
  
  fun storeSessionToken(code: String) {
    preferences.edit().putString(sessionKey, code).apply()
  }

  fun getSessionToken(): String? {
    return preferences.getString(sessionKey, null)
  }

}
