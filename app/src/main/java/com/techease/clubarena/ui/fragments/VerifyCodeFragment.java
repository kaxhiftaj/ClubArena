package com.techease.clubarena.ui.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techease.clubarena.R;

import java.util.HashMap;
import java.util.Map;


public class VerifyCodeFragment extends Fragment {


    EditText et_num1,et_num2,et_num3,et_num4,et_num5,et_num6;
    String verifycode;
    Button btn_verify_code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_code, container, false);

        btn_verify_code = view.findViewById(R.id.btn_verify_code);


            et_num1 = view.findViewById(R.id.et_code_num1);
            et_num2 = view.findViewById(R.id.et_code_num2);
            et_num3 = view.findViewById(R.id.et_code_num3);
            et_num4 = view.findViewById(R.id.et_code_num4);
            et_num5 = view.findViewById(R.id.et_code_num5);
            et_num6 = view.findViewById(R.id.et_code_num6);


            et_num1.addTextChangedListener(genraltextWatcher);
            et_num2.addTextChangedListener(genraltextWatcher);
            et_num3.addTextChangedListener(genraltextWatcher);
            et_num4.addTextChangedListener(genraltextWatcher);
            et_num5.addTextChangedListener(genraltextWatcher);
            et_num6.addTextChangedListener(genraltextWatcher);



            btn_verify_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String num1 = et_num1.getText().toString();
                    String num2 = et_num2.getText().toString();
                    String num3 = et_num3.getText().toString();
                    String num4 = et_num4.getText().toString();
                    String num5 = et_num5.getText().toString();
                    String num6 = et_num6.getText().toString();

                    verifycode = num1+num2+num3+num4+num5+num6;


                    Fragment fragment = new ResetPassFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack("tag").commit();

                }
            });



            return view;
        }
        private TextWatcher genraltextWatcher = new TextWatcher() {
            private View view;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (et_num1.length()==1) {

                    et_num2.requestFocus();

                }if (et_num2.length()==1){

                    et_num3.requestFocus();

                }if (et_num3.length()==1) {

                    et_num4.requestFocus();

                }if (et_num4.length()==1){

                    et_num5.requestFocus();

                }if (et_num5.length()==1){

                    et_num6.requestFocus();

                }if (et_num6.length()==1){

                    apicall();

                }

            }

        };

        private void apicall() {

            String url = "http://techeasesol.com/postcard/PostCard_apis/verifycode";

            StringRequest postRequest =  new StringRequest(Request.Method.POST, url, new
                    Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
                            Fragment fragment = new ResetPassFragment();
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("").commit();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<>();
                    // the POST parameters:
                    params.put("code", verifycode);


                    return params;
                }
            };
            Volley.newRequestQueue(getActivity()).add(postRequest);

        }

    }

