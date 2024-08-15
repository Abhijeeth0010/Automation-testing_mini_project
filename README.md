# Mini Project of Automation Tesing on AJIO 
This project contains automated test cases for the Ajio e-commerce website using Selenium WebDriver and TestNG. The test suite aims to cover various functionalities, including searching for products, adding them to the cart, verifying page titles, and handling the shipping and sign-up process.

## Project Overview

This automation project is built to test the following functionalities of the Ajio website:

1. Searching for products.
2. Adding products to the cart.
3. Verifying the page title.
4. Checking the presence of the Ajio logo.
5. Proceeding through the shipping process.
6. Signing up and sending an OTP for verification.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed on your system.
- Maven installed on your system.
- Google Chrome browser.
- [ChromeDriver](https://chromedriver.chromium.org/downloads) compatible with your installed version of Google Chrome.
- An IDE or text editor for Java (e.g., Eclipse, IntelliJ IDEA, VS Code).



## Test Cases
The following test cases are included in the project:

Search and Add to Cart:

Navigates to the Ajio website.
Searches for "shoes."
Selects a product and adds it to the cart.
Title Test:

Verifies that the title of the cart page matches the expected title.
Logo Test:

Checks if the Ajio logo is displayed on the page.
Shipping:

Proceeds to the shipping process and enters a phone number.
Sign Up and OTP:

Completes the sign-up process and sends an OTP for verification.
OTP Verification:

Enters the wrong OTP and submits it for verification.
