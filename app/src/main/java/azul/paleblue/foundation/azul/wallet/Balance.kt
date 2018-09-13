package azul.paleblue.foundation.azul.wallet

import java.math.BigDecimal

data class CurrencyBalance(val currency: String, val balance: BigDecimal)
data class AccountBalance(val currencies: List<CurrencyBalance>)