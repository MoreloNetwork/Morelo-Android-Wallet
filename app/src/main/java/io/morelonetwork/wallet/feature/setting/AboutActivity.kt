package io.morelonetwork.wallet.feature.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import io.morelonetwork.wallet.App
import io.morelonetwork.wallet.R
import io.morelonetwork.wallet.base.BaseTitleSecondActivity
import io.morelonetwork.wallet.support.BackgroundHelper
import io.morelonetwork.wallet.support.extensions.dp2px
import io.morelonetwork.wallet.support.extensions.getCurrentLocale
import io.morelonetwork.wallet.support.extensions.openBrowser
import io.morelonetwork.wallet.support.extensions.versionName
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseTitleSecondActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setCenterTitle(R.string.about_us)

        version.setLeftString(getString(R.string.version_placeholder, versionName()))
        version.setOnClickListener {
            openBrowser("https://wallet.wookey.io")
        }

        agreement.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java).apply {
                putExtra("url", "https://wallet.wookey.io/service-docs/terms-of-service.html")
            })
        }

        privacy.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java).apply {
                putExtra("url", "https://wallet.wookey.io/service-docs/privacy-policy.html?lang=${getCurrentLocale()}")
            })
        }
    }

    override fun onResume() {
        super.onResume()
        if (App.newVersion) {
            version.rightTextView.visibility = View.VISIBLE
            version.setRightString(getString(R.string.find_new_version))
            version.rightTextView.compoundDrawablePadding = dp2px(5)
            version.rightTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    BackgroundHelper.getRedDotDrawable(this), null)
        } else {
            version.rightTextView.visibility = View.INVISIBLE
        }
    }
}