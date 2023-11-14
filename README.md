# Setup Instructions
Follow these steps to set up the project:
1. Clone the following repository to your local machine.
2. Open Android Studio and select "Open an existing Android Studio project."
3. Navigate to the cloned repository's directory and select the project.
4. Connect your Android device to your machine or set up an emulator.
5. Ensure that your device/emulator has developer options and USB debugging enabled.
6. Build and run the project on your device/emulator.
7. Make sure all the dependencies from ./gradle are correctly installed.

In this Android Assignment, I have built two screens - a product listing screen and an add product screen. 

# Screen 1 (Fragment): Listing Products.

Requirements:

● Develop a screen that displays a list of products

● User should be able to do the following things on the screen

● Search products

● Look for all the products

● Scroll through the list

● Include a button that allows the user to navigate to the Add Product screen.

● Ensure that images are loaded for each product from a URL. If the URL is empty, use a default image instead.

● Populate all required fields, such as product name, product type, price, and tax, for each product in the list.

● Provide a visual indicator, such as a progress bar, to show loading progress.

● Use the provided API endpoint and HTTP method to retrieve the product data.

● Beautiful UI

API Specification: https://app.getswipe.in/api/public/get

Expected Response:
```
[
{
"image": "https://vx-erp-product-images.s3.ap-south-1.amazonaws.com/9_1619635829_Farm_FreshToAvatar_Logo-01.png", 
"price": 1694.91525424,
"product_name": "Testing app",
"product_type": "Product",
"tax": 18.0
},
{
"image": "https://vx-erp-product-images.s3.ap-south-1.amazonaws.com/9_1619873597_WhatsApp_Image_2021-04-30_at_19.43.23.jpeg",
"price": 84745.76271186,
"product_name": "Testing Update",
"product_type": "Service", "tax":
18.0
}
]
```

# Screen 2: Add Product Screen.

Create a screen that allows the user to add a new product with the following features:

● Select the product type from a list of options.

● Enter the product name, selling price, and tax rate using text fields.

● Optionally select images in JPEG or PNG format with a 1:1 ratio.

● Validate fields such as product type selection, non-empty product name, and decimal numbers for selling price and tax.

● Submit the data using the POST method to the API endpoint: https://app.getswipe.in/api/public/add.

● Use a user-friendly interface to display the screen.

● Document the code for future reference.

● Provide clear and concise feedback to the user upon completion of the action.

API Specifications: POST Method.

END POINT: https://app.getswipe.in/api/public/add

Body Type: form-data

Expected response:
```
{
"message": "Product added Successfully!",
"product_details": {
//details of added product
},
"product_id": 2657,
"success": true
}
```
