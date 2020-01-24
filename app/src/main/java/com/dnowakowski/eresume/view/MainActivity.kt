package com.dnowakowski.eresume.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dnowakowski.eresume.R
import com.dnowakowski.eresume.databinding.ActivityMainBinding
import com.dnowakowski.eresume.util.rx.observe

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        val viewModelProvider = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
        viewModelProvider.get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainNavigator(this)
        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                loadingVisibility = mainViewModel.loadingIndicatorVisibility
                lifecycleOwner = this@MainActivity
                activityMainBottomNav.setOnNavigationItemSelectedListener {
                    mainViewModel.executeAction(MainAction.SelectTab(it.itemId))
                    true
                }
            }

        mainViewModel.errorMessage.observe(this) {
            showErrorDialog( it.orEmpty() )
        }

        mainViewModel.executeAction(MainAction.FetchData)
    }

    private fun showErrorDialog(message: String) =
        AlertDialog.Builder(this)
            .setTitle(R.string.error_dialog_header)
            .setMessage(message)
            .setPositiveButton(R.string.error_dialog_button_close, null)
            .setCancelable(false)
            .setOnDismissListener { finish() }
            .show()
}
