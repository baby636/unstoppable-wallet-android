package io.horizontalsystems.bankwallet.modules.walletconnect.request.signmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.bankwallet.core.BaseFragment
import io.horizontalsystems.bankwallet.core.setOnSingleClickListener
import io.horizontalsystems.bankwallet.modules.walletconnect.WalletConnectSignMessageRequest
import io.horizontalsystems.bankwallet.modules.walletconnect.WalletConnectViewModel
import io.horizontalsystems.core.findNavController
import kotlinx.android.synthetic.main.fragment_wallet_connect_sign_message_request.*

class WalletConnectSignMessageRequestFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallet_connect_sign_message_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signMessageRequest = arguments?.getParcelable<WalletConnectSignMessageRequest>(WalletConnectSignMessageRequestModule.SIGN_MESSAGE_REQUEST)!!

        val baseViewModel by navGraphViewModels<WalletConnectViewModel>(R.id.walletConnectMainFragment)
        val vmFactory = WalletConnectSignMessageRequestModule.Factory(signMessageRequest, baseViewModel.service)
        val viewModel by viewModels<WalletConnectSignMessageRequestViewModel> { vmFactory }

        message.text = viewModel.message

        btnReject.setOnSingleClickListener {
            viewModel.reject()
        }

        btnSign.setOnSingleClickListener {
            viewModel.sign()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.reject()
        }

        viewModel.closeLiveEvent.observe(viewLifecycleOwner, {
            findNavController().popBackStack()
        })
    }

}
