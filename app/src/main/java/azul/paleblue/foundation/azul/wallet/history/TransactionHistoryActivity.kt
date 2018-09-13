package azul.paleblue.foundation.azul.wallet.history

import android.R
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import azul.paleblue.foundation.azul.AzulApplication
import org.jetbrains.anko.*

class TransactionHistoryActivity : FragmentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel: TransactionHistoryViewModel = ViewModelProviders.of(this).get(TransactionHistoryViewModel::class.java)
    val app = application as AzulApplication
    viewModel.model = app.transactionHistoryModel

    var txList: ListView? = null

    verticalLayout {
      txList = listView {
      }
    }

    val adapter = ArrayAdapter(this,
        R.layout.simple_list_item_1,
        R.id.text1,
        viewModel.transactions.value ?: mutableListOf())
    txList!!.adapter = adapter

    viewModel.transactions.observe(this, Observer { transactions ->
      adapter.clear()
      adapter.addAll(transactions)
      adapter.notifyDataSetChanged()
    })

  }

}
