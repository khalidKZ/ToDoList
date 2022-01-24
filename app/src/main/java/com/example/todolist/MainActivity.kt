package com.example.todolist

import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*

lateinit var inputTask: EditText

private lateinit var TaskListView: ListView
lateinit var add_task:Button
//طريقة تعريف المصفوفة و سترنج اي نوع القيمة
var TaskList = ArrayList<String>()
private var Adapter: ArrayAdapter<String>? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputTask= findViewById(R.id.input_task)
        TaskListView = findViewById(R.id.todolist)
         add_task = findViewById(R.id.add_task)

        add_task.setOnClickListener{
            addTask()
        }


    }
    //طريفة اظافة في المصفوفوة
    // هنا لازم تكتب اد تاسك بعد الفنك
    // لان الاد تاسك تعتبر زي المين اكتفتي بس ب وضيفة الاضافة
    fun addTask(){
        var text = inputTask.text.toString()
        // في حال ظغط الزر ولم يكتب شي في السطر تجيه رسالة
        if (text.isEmpty()){
            Toast.makeText(this,"الرجاء ادخل النص",Toast.LENGTH_SHORT).show()
            // وهنا اكواد الاضافة
        } else{
            TaskList.add(text)
            Adapter = ArrayAdapter(this,R.layout.item_list,R.id.title, TaskList)
            TaskListView.adapter = Adapter
        }
    }
    //طريق حذف في المصفوفة
    //لازم تكتب دليت تاسك بعد الفنك
    //و الفيو لازم تكتبها السبب ان هذي الفنكشن تعتبر زي المين و الفيو هي تربط بينها و بن الاكتفيتي مين اي الصفحة الي حنا فيها
    fun deleteTask(view : View){
        // و كلمت جيت بيرنت تجيبلي العناصر الي داخل صفحة الايتم لست
        val parent = view.getParent() as View
        //وهذا مثل ثاني للوصول الى صفحت التكست في و جلب عنصر التكست فيو الي سميناه تايتل
        val taskTextView = parent.findViewById<TextView>(R.id.title)
        var task = taskTextView.text.toString()
        //ريموف ات اي دالة للحذف
        TaskList.removeAt(TaskList.indexOf(task))
        Adapter?.notifyDataSetChanged()

    }
    fun updateTask(view: View){
        val parent = view.getParent() as View
        val taskTextView = parent.findViewById<TextView>(R.id.title).text.toString()
        var index = TaskList.indexOf(taskTextView)
        var taskEditText = EditText(this)
                // الديلوج اذا اضغط المستخدم زر التحديث راح تفتحله صفحة صغيرة وتطلب منه ادخال القيمة الجديدة
        var dialog = AlertDialog.Builder(this)
                // زر التحديث
                .setTitle("تحديث")
                .setView(taskEditText)

                // و هنا اضافة النص على النص القديم
                .setPositiveButton("تحديث", DialogInterface.OnClickListener(){
                    dialog,whitch ->
                    if (taskEditText.text.isEmpty()){
                        Toast.makeText(this,"ادخل النص",Toast.LENGTH_SHORT).show()

                    }else{
                        TaskList.set(index,taskEditText.text.toString())
                    }
                    Adapter?.notifyDataSetChanged()
                })
                // زر الالغاء
                .setNegativeButton("إلغاء",null)
                .create()
                dialog.show()
    }
}