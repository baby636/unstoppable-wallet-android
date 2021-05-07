package io.horizontalsystems.bankwallet.modules.walletconnect.request.signmessage

import android.util.Log
import io.horizontalsystems.bankwallet.core.IAccountManager
import io.horizontalsystems.bankwallet.entities.AccountType
import io.horizontalsystems.bankwallet.modules.walletconnect.WalletConnectService
import io.horizontalsystems.bankwallet.modules.walletconnect.WalletConnectSignMessageRequest
import io.horizontalsystems.ethereumkit.core.EthereumKit
import io.horizontalsystems.ethereumkit.core.hexStringToByteArray
import io.horizontalsystems.ethereumkit.crypto.CryptoUtils

class WalletConnectSignMessageRequestService(
        private val request: WalletConnectSignMessageRequest,
        private val baseService: WalletConnectService,
        private val accountManager: IAccountManager
) {
    val message = String(request.message.hexStringToByteArray())

    private fun signMessage(message: ByteArray): ByteArray? {
        return baseService.evmKit?.let { evmKit ->
            val words = (accountManager.activeAccount?.type as? AccountType.Mnemonic)?.words
            val privateKey = EthereumKit.privateKey(words = words
                    ?: listOf(), networkType = evmKit.networkType)

            val prefix = "\u0019Ethereum Signed Message:\n" + message.size

            CryptoUtils.ellipticSign(CryptoUtils.sha3(prefix.toByteArray() + message), privateKey)
        }
    }

    fun sign() {
        val signedMessage = signMessage(request.message.hexStringToByteArray())

        Log.e("AAA", "signedMessage: $signedMessage")

        baseService.approveRequest(request.id, signedMessage ?: byteArrayOf())
    }

    fun reject() {
        baseService.rejectRequest(request.id)
    }

}
