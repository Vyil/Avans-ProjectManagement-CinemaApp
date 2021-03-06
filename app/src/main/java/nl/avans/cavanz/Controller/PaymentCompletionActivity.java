package nl.avans.cavanz.Controller;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nl.avans.cavanz.Data.SQLiteHelper;
import nl.avans.cavanz.Domain.Ticket;
import nl.avans.cavanz.R;
import nl.avans.cavanz.Util.PDFGenerator;

public class PaymentCompletionActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Ticket> tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_completion);

        tickets = (ArrayList<Ticket>) getIntent().getSerializableExtra("TICKETS");
        String statuscode = getIntent().getStringExtra("PAYMENT_CODE");

        TextView textViewStatus = findViewById(R.id.payment_completion_text_message);
        TextView textViewExplanation = findViewById(R.id.payment_completion_text_explanation);

        Button buttonDownload = findViewById(R.id.payment_completion_button_download);
        buttonDownload.setVisibility(View.INVISIBLE);


        if (statuscode.equals("200")) {

            textViewStatus.setText(R.string.text_payment_completed_title);
            textViewExplanation.setText(R.string.text_payment_completed_explanation);

            // Show download button
            buttonDownload.setVisibility(View.VISIBLE);

            SQLiteHelper db = new SQLiteHelper(getApplicationContext());

            // TODO: Insert ticket into database.

            buttonDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(PaymentCompletionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        PDFGenerator pdf = new PDFGenerator();
                        pdf.createDocument(tickets, getApplicationContext());
                        Toast.makeText(PaymentCompletionActivity.this, R.string.text_payment_completed_ticketsaved, Toast.LENGTH_SHORT).show();
                    } else {
                        requestWritePermission(PaymentCompletionActivity.this);
                    }
                }
            });

        } else if (statuscode.equals("500")) {

            textViewStatus.setText(R.string.text_payment_failed_title);
            textViewExplanation.setText(R.string.text_payment_failed_explanation);

        } else {
            Toast.makeText(this, R.string.text_payment_error_whathappened, Toast.LENGTH_SHORT).show();
        }
    }

    private static void requestWritePermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(context)
                    .setMessage(R.string.text_permissions_internal_storage)
                    .setPositiveButton(R.string.text_permissions_allow, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    }).show();

        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

}
