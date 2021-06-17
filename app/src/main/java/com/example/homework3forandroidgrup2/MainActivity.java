package com.example.homework3forandroidgrup2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TaskAdapter adapter;
     TaskMOdel taskMOdel;
    FloatingActionButton fab;
    int pos;
    boolean isEdit = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView = findViewById(R.id.rec_view);
         adapter = new TaskAdapter();
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         fab = findViewById(R.id.float_button);
         fab.setOnClickListener(v -> {
             // при нажатии на fab переходит в мейн активити и ожидает результат оттуда
             Intent intent = new Intent(MainActivity.this, SecondActivity.class);
             startActivityForResult(intent, 100);  // этот код ожидает информ оттуда
         });
          adapter.setOnMoveTo(new OnMoveTo() {
              // при нажатии на адапитер в нашем случае там наши веденнные слова в editT он переходит обратно с этими же данными
              @Override
              public void onMoveTo(int position) {
                   Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                   // мы отсюда отправили по ключам и с этими же ключами принимаем
                   intent.putExtra("login", adapter.list.get(position).getTitle() );  // позиция для title
                   intent.putExtra("password", adapter.list.get(position).getDescription()); // позиция для description
                   intent.putExtra("pos", position); // также отправляем позицию чтобы понимал какой именно
                  startActivityForResult(intent, 1);
              }
          });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // получаем первую отправленную информацию
        if (requestCode == 100 && resultCode == RESULT_OK){
            if (data != null){
                TaskMOdel mOdel = new TaskMOdel(data.getStringExtra("login"), data.getStringExtra("password"));
                adapter.addData(mOdel);
            }
        }
        // получаем отредактированную информацию
        if (requestCode == 1 && resultCode == RESULT_OK){

            TaskMOdel mOdel = new TaskMOdel(data.getStringExtra("login"), data.getStringExtra("password"));
            int pos  = data.getIntExtra("pos", 0);
            adapter.editAdd(mOdel, pos);




        }

    }
}