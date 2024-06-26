package io.morelonetwork.wallet.feature.setting

import androidx.lifecycle.MutableLiveData
import io.morelonetwork.wallet.base.BaseViewModel

class ContactUsViewModel : BaseViewModel() {

    var openBrowser = MutableLiveData<String>()
    var copyUrl = MutableLiveData<String>()

    fun openBrowser(url: String) {
        openBrowser.value = url
    }

    fun copyUrl(url: String) {
        copyUrl.value = url
    }
}