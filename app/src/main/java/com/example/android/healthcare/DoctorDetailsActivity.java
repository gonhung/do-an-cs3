package com.example.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Trần Quang Khải", "Hospital Address : Thủ Đức-Thành phố Hồ Chí Minh", "Exp : 10 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Nguyễn Khả Như", "Hospital Address : Vinh-Nghệ An", "Exp : 8 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Phạm Viết Dũng", "Hospital Address : Bình Sơn- Quảng Ngãi", "Exp : 6 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Nguyễn Hồng Thắng", "Hospital Address : Ngũ Hành Sơn- Đà Nẵng", "Exp : 12 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Đặng Thành Trung", "Hospital Address : Nghi lộc - Nghệ An", "Exp : 18 year", "Mobile No :08465939392", "200.000VND"},
            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Hoàng Thị Hồng Nhung", "Hospital Address : Thủ Đức-Thành phố Hồ Chí Minh", "Exp : 10 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Nguyễn Khả Như", "Hospital Address : Vinh-Nghệ An", "Exp : 8 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Phạm Hoàng Phúc", "Hospital Address : Bình Sơn- Quảng Ngãi", "Exp : 6 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Phan Khải", "Hospital Address : Ngũ Hành Sơn- Đà Nẵng", "Exp : 12 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Đặng Thành Trung", "Hospital Address : Nghi lộc - Nghệ An", "Exp : 18 year", "Mobile No :08465939392", "200.000VND"},
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Nguyễn Viết Đoàn", "Hospital Address : Thủ Đức-Thành phố Hồ Chí Minh", "Exp : 10 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Phạm Phụng", "Hospital Address : Vinh-Nghệ An", "Exp : 8 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Phạm Viết Dũng", "Hospital Address : Bình Sơn- Quảng Ngãi", "Exp : 6 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Hà Hồng Thắng", "Hospital Address : Ngũ Hành Sơn- Đà Nẵng", "Exp : 12 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Đặng Chí Trung", "Hospital Address : Nghi lộc - Nghệ An", "Exp : 18 year", "Mobile No :08465939392", "200.000VND"},
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name :Lê Quang Khải", "Hospital Address : Thủ Đức-Thành phố Hồ Chí Minh", "Exp : 10 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Nguyễn Khả Như", "Hospital Address : Vinh-Nghệ An", "Exp : 8 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Phạm Viết Dũng", "Hospital Address : Bình Sơn- Quảng Ngãi", "Exp : 6 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Nguyễn Hồng Thắng", "Hospital Address : Ngũ Hành Sơn- Đà Nẵng", "Exp : 12 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Đặng Thành Trung", "Hospital Address : Nghi lộc - Nghệ An", "Exp : 18 year", "Mobile No :08465939392", "200.000VND"},
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Trần Quang Khải", "Hospital Address : Thủ Đức-Thành phố Hồ Chí Minh", "Exp : 10 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Nguyễn Khả Như", "Hospital Address : Vinh-Nghệ An", "Exp : 8 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Phạm Viết Dũng", "Hospital Address : Bình Sơn- Quảng Ngãi", "Exp : 6 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Nguyễn Hồng Thắng", "Hospital Address : Ngũ Hành Sơn- Đà Nẵng", "Exp : 12 year", "Mobile No :08465939392", "200.000VND"},
                    {"Doctor Name : Đặng Thành Trung", "Hospital Address : Nghi lộc - Nghệ An", "Exp : 18 year", "Mobile No :08465939392", "200.000VND"},
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @SuppressLint({"WrongViewCast", "SuspiciousIndentation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewBMCartTitle);
        btn = findViewById(R.id.buttonBMCheckout);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physician") == 0)
            doctor_details = doctor_details1;
        else
            if (title.compareTo("Dietitian") == 0)
            doctor_details = doctor_details2;
        else
            if (title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else
            if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line_a", doctor_details[i][0]);
            item.put("line_b", doctor_details[i][1]);
            item.put("line_c", doctor_details[i][2]);
            item.put("line_d", doctor_details[i][3]);
            item.put("line_e", "Cons Fees:" + doctor_details[i][4] + "/-");
            list.add(item);

        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line_a", "line_b", "line_c", "line_d", "line_e"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        ListView lst = findViewById(R.id.listViewBMCart);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }

}
