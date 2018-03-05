package com.payghost.wedding;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://mydm.co.za/Nissa/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String Sname, String dob, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("surname", Sname);
        params.put("dob", dob);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
