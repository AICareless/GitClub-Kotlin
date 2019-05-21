package com.i502tech.gitclubkotlin.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.i502tech.gitclubkotlin.MainActivity
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.app.SettingsPreferences
import com.i502tech.gitclubkotlin.base.BaseActivity
import com.i502tech.gitclubkotlin.utils.*
import com.i502tech.gitclubkotlin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

/**
 * description $desc$
 * created by jerry on 2019/4/24.
 */
class LoginActivity: BaseActivity() {

    private val viewModel: UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun initData() {
        SettingsPreferences.get().user ?.let {
            if (it.userId != 0){
                toActivity(MainActivity::class.java)
                finish()
            }
        }
    }

    override fun initLisenter() {
        btn_login.setOnClickListener {
            if (et_count.text.isEmpty() || et_passward.text.isEmpty()){
                toast("用户名或密码不能为空")
                return@setOnClickListener
            }
            viewModel.loginRegister(btn_login.text.toString(), et_count.text.toString(), et_passward.text.toString())
                    .observe(this, Observer {resources->
                        when {
                            resources?.isSuccess == true -> {
                                SettingsPreferences.get().user = resources.data
                                toActivity(MainActivity::class.java)
                                finish()
                            }
                            resources?.isLoading == true -> toast("${btn_login.text}中...")
                            else -> toast(resources?.msg)
                        }
                    })
        }

        tv_register.setOnClickListener {
            if (tv_register.text == "注册"){
                tv_register.text = "登录"
                btn_login.text = "注册"
            }else {
                tv_register.text = "注册"
                btn_login.text = "登录"
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}