package com.esfita.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String viewTypes[] = {"Please Select", "Male", "Female"};
    String rbString = "", spType = "";
    EditText et;
    RadioButton[] rb;
    RadioGroup rg;
    Spinner spinner;
    Button btn;
    private int hint=0;

    List<ProductModel> productModelList = new ArrayList<>();
    APIInterface APIInterface;
    LinearLayout ll1,llRadio,llSpinner,llRadioRoot,llSpinnerRoot,lleditRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = (LinearLayout) findViewById(R.id.lllayout);
        TextView tv = new TextView(this);
        tv.setText("Text View 1");

        tv.setBackground(getResources().getDrawable(R.drawable.border_white));
        ll.addView(tv);
        lleditRoot = new LinearLayout(this);
        LinearLayout.LayoutParams lparamseditroot = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lleditRoot.setOrientation(LinearLayout.VERTICAL);
        lleditRoot.setLayoutParams(lparamseditroot);

        ll1 = new LinearLayout(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setLayoutParams(lparams);

        llRadioRoot = new LinearLayout(this);
        LinearLayout.LayoutParams lparamsradioroot = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRadioRoot.setOrientation(LinearLayout.VERTICAL);
        llRadioRoot.setLayoutParams(lparamsradioroot);

        llRadio = new LinearLayout(this);
        LinearLayout.LayoutParams lparamsradio = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRadio.setOrientation(LinearLayout.HORIZONTAL);
        llRadio.setLayoutParams(lparamsradio);

        llSpinnerRoot = new LinearLayout(this);
        LinearLayout.LayoutParams lparamsspinnerroot = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llSpinnerRoot.setOrientation(LinearLayout.VERTICAL);
        llSpinnerRoot.setLayoutParams(lparamsspinnerroot);

        llSpinner = new LinearLayout(this);
        LinearLayout.LayoutParams lparamsspinner = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llSpinner.setOrientation(LinearLayout.HORIZONTAL);
        llSpinner.setLayoutParams(lparamsspinner);
        // add edit text
        et = new EditText(this);
        et.setBackground(getResources().getDrawable(R.drawable.border_white));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 60);
        params.setMargins(10, 10, 10, 10);

        et.setLayoutParams(params);
        et.setPadding(10, 10, 10, 10);
        et.setText("EditText ");
        et.setMinLines(1);
        et.setMaxLines(3);

        ll1.addView(et);

        Button btnadd = new Button(this);
        btnadd.setText("Add");

        ll1.addView(btnadd);


        lleditRoot.addView(ll1);
        ll.addView(lleditRoot);

        rb = new RadioButton[2];

        rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.HORIZONTAL);
        rb[0] = new RadioButton(this);
        rb[1] = new RadioButton(this);
        rb[0].setText("YES");
        rb[1].setText("NO");

        rg.addView(rb[0]);
        rg.addView(rb[1]);
        llRadio.addView(rg);
        llRadioRoot.addView(llRadio);

        Button btnaddRB = new Button(this);
        btnaddRB.setText("Add");
        llRadio.addView(btnaddRB);
        ll.addView(llRadioRoot);


        spinner = new Spinner(this);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, viewTypes);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, viewTypes[position], Toast.LENGTH_SHORT).show();
                rbString = viewTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        llSpinner.addView(spinner);
        llSpinnerRoot.addView(llSpinner);

        ll.addView(llSpinnerRoot);
        Button btnaddSpinner= new Button(this);
        btnaddSpinner.setText("Add");
        llSpinner.addView(btnaddSpinner);


        btn = new Button(this);

        btn.setText("Submit");
        btn.setBackground(getResources().getDrawable(R.drawable.oval_border_button));
        ll.addView(btn);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                Toast.makeText(MainActivity.this, rb.getText().toString(), Toast.LENGTH_SHORT).show();
                rbString = rb.getText().toString();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, et.getText().toString() + tv.getText().toString() + "   spinner    " + spType + "    " + rbString, Toast.LENGTH_SHORT).show();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("editText", et.getText().toString());
                    jsonObject.put("rb", rbString);
                    jsonObject.put("spinner", spType);
                    et.setText(jsonObject.getString("editText"));
//                    if (jsonObject.getString("rb").equalsIgnoreCase("Male")) {
//                        rb[1].setChecked(true);
//                    } else {
//                        rb[2].setChecked(true);
//                    }
                    for (int i = 0; i < viewTypes.length; i++) {
                        if (viewTypes[i].equalsIgnoreCase(jsonObject.getString("spinner"))) {
                            spinner.setSelection(i);
                        }
                    }
                    //   loadlist(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEdittext();
            }
        });
        btnaddRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRadioButton();
            }
        });
btnaddSpinner.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        addSpinner();
    }
});


    }

    private void addEdittext() {
      LinearLayout  ll= new LinearLayout(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(lparams);

        EditText edittTxt = new EditText(this);
        int maxLength = 5;
        hint++;
        edittTxt.setHint("editText"+hint);

        // edtTxt.setBackgroundColor(Color.WHITE);

        edittTxt.setId(hint);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        edittTxt.setFilters(fArray);
        Button btndel= new Button(this);
        btndel.setText("Delete");


        ll.addView(edittTxt);
        ll.addView(btndel);
        lleditRoot.addView( ll);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lleditRoot.removeView(ll);
            }
        });

    }
    private void addRadioButton() {
        LinearLayout  ll= new LinearLayout(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(lparams);

        RadioButton[] rbb = new RadioButton[2];
        rbb = new RadioButton[2];

      RadioGroup  rgg = new RadioGroup(this); //create the RadioGroup
        rgg.setOrientation(RadioGroup.HORIZONTAL);
        rbb[0] = new RadioButton(this);
        rbb[1] = new RadioButton(this);
        rbb[0].setText("YES");
        rbb[1].setText("NO");

        rgg.addView(rbb[0]);
        rgg.addView(rbb[1]);
        ll.addView(rgg);

        Button btnradiodel= new Button(this);
        btnradiodel.setText("Delete");

        ll.addView(btnradiodel);
        llRadioRoot.addView(ll);
        rgg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                Toast.makeText(MainActivity.this, rb.getText().toString(), Toast.LENGTH_SHORT).show();
                rbString = rb.getText().toString();

            }
        });
        btnradiodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llRadioRoot.removeView(ll);
            }
        });


    }
    private void addSpinner() {
        LinearLayout  ll= new LinearLayout(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(lparams);

     Spinner   spinner1 = new Spinner(this);
        spinner1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, viewTypes);
        spinner1.setAdapter(arrayAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, viewTypes[position], Toast.LENGTH_SHORT).show();
                rbString = viewTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ll.addView(spinner1);


        Button btnspinnerdel= new Button(this);
        btnspinnerdel.setText("Delete");

        ll.addView(btnspinnerdel);
        llSpinnerRoot.addView(ll);
        btnspinnerdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSpinnerRoot.removeView(ll);
            }
        });

    }

    public void loadlist(JSONObject jsonObject) {
        APIInterface = APIClient.getApiClient().create(APIInterface.class);
        Call<List<ProductModel>> call = APIInterface.postData(jsonObject);
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (response.isSuccessful()) {
                    Log.e("Success", new Gson().toJson(response.body()));

                    productModelList = response.body();

                    et.setText(productModelList.get(0).getEtValue());
                    for (int i = 0; i < viewTypes.length; i++) {
                        if (viewTypes[i].equalsIgnoreCase(productModelList.get(i).getSpValue())) {
                            spinner.setSelection(i);
                        }
                    }
                    if (productModelList.get(0).getRbValue().equalsIgnoreCase("Male")) {
                        rb[1].setChecked(true);
                    } else {
                        rb[2].setChecked(true);
                    }


                } else
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));


            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });


    }

}