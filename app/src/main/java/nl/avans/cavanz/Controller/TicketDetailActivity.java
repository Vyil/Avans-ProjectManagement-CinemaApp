package nl.avans.cavanz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.avans.cavanz.R;

public class TicketDetailActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
    }
}
