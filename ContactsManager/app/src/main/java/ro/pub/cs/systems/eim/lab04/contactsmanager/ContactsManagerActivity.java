package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static android.view.View.VISIBLE;

public class ContactsManagerActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText addressEditText;
    private EditText emailEditText;
    private EditText companyEditText;
    private Button additionalFieldsButton;
    private RelativeLayout container1;
    private LinearLayout container2;
    private Button saveButton;
    private Button cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (view.getId() == additionalFieldsButton.getId()) {
                int containerVisibility = container2.getVisibility();
                if (containerVisibility == View.VISIBLE) {
                    container2.setVisibility(View.INVISIBLE);
                    additionalFieldsButton.setText("Add additional fields");
                } else if (containerVisibility == View.INVISIBLE) {
                    additionalFieldsButton.setText("Hide additional fields");
                    container2.setVisibility(View.VISIBLE);
                }
            }
            else if (view.getId() == saveButton.getId()) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String company = companyEditText.getText().toString();

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }

                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        container1 = (RelativeLayout)findViewById(R.id.first_container);
        container2 = (LinearLayout)findViewById(R.id.second_container);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
        addressEditText = (EditText) findViewById(R.id.address_edit_text);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        companyEditText = (EditText) findViewById(R.id.company_edit_text);
        additionalFieldsButton = (Button) findViewById(R.id.button);
        additionalFieldsButton.setOnClickListener(buttonClickListener);
        saveButton = (Button) findViewById(R.id.button);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(buttonClickListener);

    }
}
