package io.morelonetwork.wallet.dialog

import androidx.lifecycle.MutableLiveData
import io.morelonetwork.wallet.R
import io.morelonetwork.wallet.base.BaseViewModel
import io.morelonetwork.wallet.core.XMRWalletController
import io.morelonetwork.wallet.data.entity.Node
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NodeEditViewModel : BaseViewModel() {

    val success = MutableLiveData<Node>()

    val showLoading = MutableLiveData<Boolean>()
    val hideLoading = MutableLiveData<Boolean>()
    val toastRes = MutableLiveData<Int>()

    fun testRpcService(symbol: String, url: String) {
        showLoading.postValue(true)
        uiScope.launch {
            try {
                val responseTime = withContext(Dispatchers.IO) {
                    XMRWalletController.testRpcService(url)
                }
                hideLoading.postValue(true)
                success.postValue(Node(symbol = symbol, url = url, responseTime = responseTime))
            } catch (e: Exception) {
                e.printStackTrace()
                hideLoading.postValue(true)
                toastRes.postValue(R.string.node_connect_failed)
            }
        }
    }
}
