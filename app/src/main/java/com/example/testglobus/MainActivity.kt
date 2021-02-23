package com.example.testglobus

import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var available_btn: Button
    private lateinit var pending_btn: Button
    private lateinit var sold_btn: Button
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        available_btn = findViewById(R.id.available_btn)
        pending_btn = findViewById(R.id.pending_btn)
        sold_btn = findViewById(R.id.sold_btn)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        observeGetPosts()
        buttonsClickListener()

    }

    // Наблюдаем за нашей лайвдатой
    // В зависимости от Ивента устанавливаем нужное состояние вью
    private fun observeGetPosts() {
        activityViewModel.simpleLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> viewOneLoading()
                Status.SUCCESS -> viewOneSuccess(it.data)
                Status.ERROR -> viewOneError(it.error)
            }
        })
    }

    private fun initRV(petsList: MutableList<String>, status: String) {
        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RecyclerAdapter(petsList, status)
    }

    private fun buttonsClickListener() {
        available_btn.setOnClickListener {
            activityViewModel.getUsers(status = "available")
        }
        pending_btn.setOnClickListener {
            activityViewModel.getUsers(status = "pending")
        }
        sold_btn.setOnClickListener {
            activityViewModel.getUsers(status = "sold")
        }

    }

    private fun viewOneLoading() {
        Toast.makeText(this,"Loading", Toast.LENGTH_SHORT).show()
    }

    private fun viewOneSuccess(data: Pets?) {
        Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
        val nameList: MutableList<String> = data?.name as MutableList<String>
        val status: String = data.status
        initRV(nameList, status)
    }

    private fun viewOneError(error: Error?) {
        Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
        // Показываем ошибку
    }
}