package io.horizontalsystems.bankwallet.modules.walletconnect.request.signmessage

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import io.horizontalsystems.bankwallet.core.App
import io.horizontalsystems.bankwallet.modules.walletconnect.WalletConnectService
import io.horizontalsystems.bankwallet.modules.walletconnect.WalletConnectSignMessageRequest
import io.horizontalsystems.core.findNavController

object WalletConnectSignMessageRequestModule {

    const val SIGN_MESSAGE_REQUEST = "sign_message_request"

    fun start(fragment: Fragment, navigateTo: Int, navOptions: NavOptions, signMessageRequest: WalletConnectSignMessageRequest) {
        fragment.findNavController().navigate(navigateTo, bundleOf(SIGN_MESSAGE_REQUEST to signMessageRequest), navOptions)
    }

    class Factory(
            private val signMessageRequest: WalletConnectSignMessageRequest,
            private val baseService: WalletConnectService
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when (modelClass) {
                WalletConnectSignMessageRequestViewModel::class.java -> {
                    val service = WalletConnectSignMessageRequestService(signMessageRequest, baseService, App.accountManager)
                    WalletConnectSignMessageRequestViewModel(service) as T
                }
                else -> throw IllegalArgumentException()
            }
        }
    }

}
