package com.example.tester // ကိုယ့်ရဲ့ package name နဲ့ အစားထိုးပါ

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTaskInput: EditText
    private lateinit var btnAdd: Button
    private lateinit var listViewTasks: ListView

    // Task များကို သိမ်းဆည်းထားမည့် List
    private val taskList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // XML ထဲက View များနှင့် ချိတ်ဆက်ခြင်း
        etTaskInput = findViewById(R.id.etTaskInput)
        btnAdd = findViewById(R.id.btnAdd)
        listViewTasks = findViewById(R.id.listViewTasks)

        // Adapter တည်ဆောက်ပြီး List နှင့် ListView ကို ချိတ်ဆက်ခြင်း
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        listViewTasks.adapter = adapter

        // Add ခလုတ်နှိပ်လိုက်သည့်အခါ
        btnAdd.setOnClickListener {
            val taskText = etTaskInput.text.toString().trim()

            if (taskText.isNotEmpty()) {
                taskList.add(taskText)      // List ထဲသို့ ထည့်မည်
                adapter.notifyDataSetChanged() // UI ကို အသစ်ဖြစ်ကြောင်း အကြောင်းကြားမည်
                etTaskInput.text.clear()    // EditText ကို ရှင်းလင်းမည်

                Toast.makeText(this, "Task ထည့်ပြီးပါပြီ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "ကျေးဇူးပြု၍ စာသားရိုက်ထည့်ပါ", Toast.LENGTH_SHORT).show()
            }
        }

        // Task တစ်ခုခုကို နှိပ်ပြီး ဖျက်ချင်သည့်အခါ (Long Click ဖြင့် ဖျက်ရန်)
        listViewTasks.setOnItemLongClickListener { _, _, position, _ ->
            val removedTask = taskList[position]
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "'$removedTask' ကို ဖျက်လိုက်ပါပြီ", Toast.LENGTH_SHORT).show()
            true
        }
    }
}