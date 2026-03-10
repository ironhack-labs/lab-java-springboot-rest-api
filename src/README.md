![logo_ironhack_blue 7](https://user-images.githubusercontent.com/23629340/40541063-a07a0a8a-601a-11e8-91b5-2f13e4e6b441.png)

# LAB | SpringBoot REST API

### Instructions

1. Fork this repo.
2. Clone your fork to your local machine.
3. Solve the challenges.

  
## Deliverables

- Upon completion, add your solution to git.
- Then commit to git and push to your repo on GitHub.
- Make a pull request and paste the pull request link in the submission field in the Student Portal.

## Tasks

1. Create a Spring Boot application using Spring Initializr with the following dependencies:
   - Spring Web
   - Spring Boot DevTools
   - Spring Boot Starter Validation

2. Create a `Product` class with the following validated properties:
   - name (not blank, min length 3)
   - price (positive number)
   - category (not blank)
   - quantity (positive number)

3. Create a `ProductService` class that manages a List of Products and has methods to:
   - Add a new product
   - Get all products
   - Get product by name
   - Update product
   - Delete product
   - Get products by category
   - Get products by price range

4. Create a `ProductController` class that:
   - Uses constructor injection for the ProductService
   - Requires an "API-Key" header for all requests (value: "123456")
   - Has the following endpoints:
     * POST `/products` - Create new product
     * GET `/products` - Get all products
     * GET `/products/{name}` - Get product by name
     * PUT `/products/{name}` - Update product
     * DELETE `/products/{name}` - Delete product
     * GET `/products/category/{category}` - Get products by category
     * GET `/products/price?min={min}&max={max}` - Get products by price range

5. Create a global exception handler that handles:
   - Validation errors (return proper error messages)
   - Missing API-Key header
   - Product not found
   - Invalid price range

6. Create a `Customer` class with the following validated properties:
   - name (not blank)
   - email (valid email format)
   - age (minimum 18)
   - address (not blank)

7. Create a `CustomerController` with endpoints to:
   - Create new customer (with validation)
   - Get all customers
   - Get customer by email
   - Update customer
   - Delete customer

**Remember**:
- Use proper package structure
- Use constructor injection instead of @Autowired
- Test all endpoints using Postman
- Include appropriate error handling
- Use meaningful variable and method names
- Return appropriate HTTP status codes
- Include validation messages in responses
