package example.androidstudio0507;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //数据源，全部学生数据
    ArrayList<Student> dataSource = new ArrayList<>();

    ListView listView;
    MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.id_listview);
        listAdapter = new MyListAdapter();
        listView.setAdapter(listAdapter);

        //准备数据
        dataSource.add(new Student(201901, "张龙", true));
        dataSource.add(new Student(201902, "王梅", false));
        dataSource.add(new Student(201903, "黄汉", true));
        dataSource.add(new Student(201904, "李柳", true));

        //显示
        showAll();
    }

    private void showAll(){
        //准备要显示的数据：dataSource里的全部显示
        listAdapter.result.addAll(dataSource);

        //通知ListView数据已经更新
        listAdapter.notifyDataSetChanged();
    }

    private class Student{
        public int id;
        public String name;
        public boolean sex;

        public Student(int id, String name, boolean sex){
            this.id = id;
            this.name = name;
            this.sex = sex;
        }
    }

    private class MyListAdapter extends BaseAdapter{
        //要显示的数据放在result里
        ArrayList<Student> result = new ArrayList<Student>();
        Drawable ic_male, ic_female;

        public MyListAdapter(){
            ic_male = getDrawable(R.drawable.ic_male);
            ic_female = getDrawable(R.drawable.ic_female);
        }

        @Override
        public int getCount() {
            return result.size();
        }

        @Override
        public Object getItem(int position) {
            return result.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.layout_student_item, parent, false);

            Student data = (Student)getItem(position);
            Log.e("abcde",data.name);

            TextView textView = (TextView)convertView.findViewById(R.id.id_item_text);
            textView.setText(data.name);

            ImageView imageView = (ImageView)convertView.findViewById(R.id.id_item_icon);
            if(data.sex)
                imageView.setImageDrawable(ic_male);
            else
                imageView.setImageDrawable(ic_female);

            return convertView;
        }
    }
}
