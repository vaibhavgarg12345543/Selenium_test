# Setup

1. *Initial Setup:*
   - Ensure Java and Maven (MVN) are installed and configured in your IDE (VSCode, IntelliJ, etc.).
   - Create a GitHub repository for your project.
   - Add a README file to your repository.

2. *Project Setup:*
   - Create a Maven project in your IDE.
   - Configure your pom.xml to include dependencies for TestNG, Selenium, WebDriverManager, Aventstack, and other required libraries.
3. *Writing Tests:*
   - Use XPath and CSS selectors to locate and interact with web elements in your test cases.
   - Capture screenshots as needed.
   - For reports, use Extent Reports to generate detailed HTML reports for test results.

4. *Running Tests:*
   - To execute your tests, run the test file using Java. Navigate to src/test/java and run SeleniumTest.java.

5. *Report Generation:*
   - Extent Reports is used to create test reports. The report is an HTML file that provides a comparative analysis of products.
   - Note: Update the paths for reports and screenshots according to your directory structure. The default paths are set for a specific desktop configuration, and may need adjustment to avoid "path not found" errors.

6. *Cypress Testing:*
   - Cypress does not support window switching, which is needed for certain UI 
   automations (e.g., adding items to the cart). Hence, Selenium was used for such scenarios.

## Selenium Tests

1. *Amazon:*
   - Open the Amazon URL.
   - Search for a product, click on the first result, and save the product name, price, and link.
   - Add the item to the cart, proceed to checkout, and confirm the checkout window.

2. *Flipkart:*
   - Open the Flipkart URL.
   - Search for a product, click on the first result, and save the product data.
   - Add the item to the cart, proceed to checkout, and confirm the checkout page.

3. *Comparison:*
   - Compare product prices between Amazon and Flipkart.
   - Log the output to identify the better option based on price.

## Backend Test
- For amazon and flipkart we need bearer key and without paying for that we can not access aws client id and api keyt 
- For test we can use httpclient where
   - make a request by giving product name in api 
   - provide bearer api key for Oauth
   - call the get or post call ..like for search get call will be implemented
   - Httpget request and call excute operation of httpclient 
   - response will be there and we can easily parse the json body and get price and product name 
   - we can use product name in flipkart like i had used amazon product name in flipkart tp shhow 
   - we can compare price also by this 