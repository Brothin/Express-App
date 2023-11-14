package com.example.aexpress.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aexpress.R;

import org.json.JSONException;
import org.json.JSONObject;


public class AddProduct extends AppCompatActivity {

    private Spinner spinnerProductType;
    private EditText editTextProductName, editTextSellingPrice, editTextTaxRate;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        spinnerProductType = findViewById(R.id.spinnerProductType);
            editTextProductName = findViewById(R.id.editTextProductName);
            editTextSellingPrice = findViewById(R.id.editTextSellingPrice);
            editTextTaxRate = findViewById(R.id.editTextTaxRate);
            Button btnSubmit = findViewById(R.id.btnSubmit);
            imageView = findViewById(R.id.imageView);
            Button btnSelectImage=findViewById(R.id.btnSelectImage);

        // Set a listener for the "Select Image" button
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the image picker
                openImagePicker();
            }
        });

            // Populate the spinner with product types
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.product_types,
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerProductType.setAdapter(adapter);

            // Set a listener for the spinner
            spinnerProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Handle item selection if needed
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Do nothing here
                }
            });

            // Set a listener for the submit button
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Validate input fields
                    if (validateFields()) {
                        // Execute API request
                        sendProductDataToAPI();
                    }
                }
            });
        }

        private boolean validateFields() {
            // Implement validation logic for fields
            String productName = editTextProductName.getText().toString().trim();
            String sellingPrice = editTextSellingPrice.getText().toString().trim();
            String taxRate = editTextTaxRate.getText().toString().trim();

            if (productName.isEmpty() || sellingPrice.isEmpty() || taxRate.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return false;
            }

            // Additional validation logic can be added as needed

            return true;
        }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void sendProductDataToAPI() {
        String productType = spinnerProductType.getSelectedItem().toString();
        String productName = editTextProductName.getText().toString().trim();
        String sellingPrice = editTextSellingPrice.getText().toString().trim();
        String taxRate = editTextTaxRate.getText().toString().trim();

        // API endpoint
        String apiUrl = "https://app.getswipe.in/api/public/add";

        // Initialize a Volley RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a JSONObject for the request body
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("product_type", productType);
            requestBody.put("product_name", productName);
            requestBody.put("price", sellingPrice);
            requestBody.put("tax", taxRate);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a multipart request using JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                apiUrl,
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the API response
                        try {
                            boolean success = response.getBoolean("success");
                            if (success) {
                                // Product added successfully
                                Toast.makeText(AddProduct.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle failure
                                String message = response.getString("message");
                                Toast.makeText(AddProduct.this, "Failed to add product: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Toast.makeText(AddProduct.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
        };

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Set the selected image to the ImageView
            imageView.setImageURI(data.getData());
            selectedImageUri = data.getData();
        }
    }
}