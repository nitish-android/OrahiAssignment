package in.resoluteai.orahiassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class OTPVeroification extends AppCompatActivity {
    EditText ed_otp;
    RelativeLayout verify_otp_btn;
    CardView verify_button_card_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_veroification);
        ed_otp = findViewById(R.id.otp_verified);
        verify_otp_btn = findViewById(R.id.verify_otp_button);
        verify_button_card_view=findViewById(R.id.verify_button_card_view);
        String string = getIntent().getStringExtra("otp");
        ed_otp.setText(string);


        verify_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DemoGraph.class);
                startActivity(intent);
                finish();
                Toast.makeText(OTPVeroification.this, "Your mobile number is verified", Toast.LENGTH_SHORT).show();

            }
        });
    }


}