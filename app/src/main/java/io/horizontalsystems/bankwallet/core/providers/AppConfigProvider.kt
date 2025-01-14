package io.horizontalsystems.bankwallet.core.providers

import io.horizontalsystems.bankwallet.BuildConfig
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.bankwallet.core.App
import io.horizontalsystems.bankwallet.core.IAppConfigProvider
import io.horizontalsystems.coinkit.models.CoinType
import io.horizontalsystems.core.IBuildConfigProvider
import io.horizontalsystems.core.ILanguageConfigProvider
import io.horizontalsystems.core.entities.Currency

class AppConfigProvider : IAppConfigProvider, ILanguageConfigProvider, IBuildConfigProvider {

    override val companyWebPageLink: String = "https://horizontalsystems.io"
    override val appWebPageLink: String = "https://unstoppable.money"
    override val appGithubLink: String = "https://github.com/horizontalsystems/unstoppable-wallet-android"
    override val companyTwitterLink: String = "https://twitter.com/UnstoppableByHS"
    override val companyTelegramLink: String = "https://t.me/unstoppable_announcements"
    override val companyRedditLink: String = "https://reddit.com/r/UNSTOPPABLEWallet/"
    override val reportEmail = "support.unstoppable@protonmail.com"
    override val walletHelpTelegramGroup = "UnstoppableWallet"
    override val btcCoreRpcUrl: String = "https://btc.horizontalsystems.xyz/rpc"

    override val cryptoCompareApiKey = App.instance.localizedContext().getString(R.string.cryptoCompareApiKey)
    override val infuraProjectId = App.instance.localizedContext().getString(R.string.infuraProjectId)
    override val infuraProjectSecret = App.instance.localizedContext().getString(R.string.infuraSecretKey)
    override val etherscanApiKey = App.instance.localizedContext().getString(R.string.etherscanKey)
    override val bscscanApiKey = App.instance.localizedContext().getString(R.string.bscscanKey)
    override val guidesUrl = App.instance.localizedContext().getString(R.string.guidesUrl)
    override val faqUrl = App.instance.localizedContext().getString(R.string.faqUrl)
    override val fiatDecimal: Int = 2
    override val maxDecimal: Int = 8
    override val feeRateAdjustForCurrencies: List<String> = listOf("USD","EUR")

    override val currencies: List<Currency> = listOf(
            Currency(code = "USD", symbol = "\u0024", decimal = 2),
            Currency(code = "EUR", symbol = "\u20AC", decimal = 2),
            Currency(code = "GBP", symbol = "\u00A3", decimal = 2),
            Currency(code = "JPY", symbol = "\u00A5", decimal = 2)
    )
    override val featuredCoinTypes: List<CoinType> = listOf(
            CoinType.Bitcoin,
            CoinType.BitcoinCash,
            CoinType.Ethereum,
            CoinType.Zcash,
            CoinType.BinanceSmartChain
    )

    //  ILanguageConfigProvider

    override val localizations: List<String>
        get() {
            val coinsString = "de,en,es,fa,fr,ko,ru,tr,zh"
            return coinsString.split(",")
        }

    //  IBuildConfigProvider

    override val testMode: Boolean = BuildConfig.testMode

    override val skipRootCheck: Boolean = BuildConfig.skipRootCheck

}
