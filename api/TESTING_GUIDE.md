# Spring Boot REST API Testing Guide

## Prerequisites
- Java 21 installed
- Maven installed
- Postman or similar API testing tool

## Running the Application

1. Navigate to the `api` directory:
   ```bash
   cd api
   ```

2. Compile and run the application:
   ```bash
   mvn spring-boot:run
   ```
   
   Or using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

3. The application will start on `http://localhost:8080`

## API Endpoints Testing

### Product Endpoints (require API-Key header: "123456")

#### Create Product
- **Method**: POST
- **URL**: `http://localhost:8080/products`
- **Headers**: `API-Key: 123456`
- **Body**:
```json
{
  "name": "Smartphone",
  "price": 699.99,
  "category": "Electronics",
  "quantity": 25
}
```

#### Get All Products
- **Method**: GET
- **URL**: `http://localhost:8080/products`
- **Headers**: `API-Key: 123456`

#### Get Product by Name
- **Method**: GET
- **URL**: `http://localhost:8080/products/Laptop`
- **Headers**: `API-Key: 123456`

#### Update Product
- **Method**: PUT
- **URL**: `http://localhost:8080/products/Laptop`
- **Headers**: `API-Key: 123456`
- **Body**:
```json
{
  "price": 899.99,
  "category": "Electronics",
  "quantity": 15
}
```

#### Delete Product
- **Method**: DELETE
- **URL**: `http://localhost:8080/products/Laptop`
- **Headers**: `API-Key: 123456`

#### Get Products by Category
- **Method**: GET
- **URL**: `http://localhost:8080/products/category/Electronics`
- **Headers**: `API-Key: 123456`

#### Get Products by Price Range
- **Method**: GET
- **URL**: `http://localhost:8080/products/price?min=100&max=1000`
- **Headers**: `API-Key: 123456`

### Customer Endpoints

#### Create Customer
- **Method**: POST
- **URL**: `http://localhost:8080/customers`
- **Body**:
```json
{
  "name": "Alice Wilson",
  "email": "alice@example.com",
  "age": 25,
  "address": "123 Main St, City, State"
}
```

#### Get All Customers
- **Method**: GET
- **URL**: `http://localhost:8080/customers`

#### Get Customer by Email
- **Method**: GET
- **URL**: `http://localhost:8080/customers/john@example.com`

#### Update Customer
- **Method**: PUT
- **URL**: `http://localhost:8080/customers/john@example.com`
- **Body**:
```json
{
  "name": "John Updated",
  "age": 26,
  "address": "456 New Address"
}
```

#### Delete Customer
- **Method**: DELETE
- **URL**: `http://localhost:8080/customers/john@example.com`

## Error Scenarios to Test

### Validation Errors
1. Create product with name less than 3 characters
2. Create product with negative price
3. Create customer with invalid email
4. Create customer with age less than 18

### API Key Errors
1. Try to access product endpoints without API-Key header
2. Try to access product endpoints with wrong API-Key

### Not Found Errors
1. Get product that doesn't exist
2. Get customer that doesn't exist
3. Update/delete non-existent resources

### Price Range Errors
1. Try to get products with min >= max price

## Expected Responses

### Success Responses
- **201 Created**: When creating new resources
- **200 OK**: When retrieving or updating resources
- **204 No Content**: When deleting resources successfully

### Error Responses
- **400 Bad Request**: Validation errors or invalid price range
- **401 Unauthorized**: Missing or invalid API-Key
- **404 Not Found**: Resource not found
- **500 Internal Server Error**: Unexpected errors

## Sample Initial Data

The application starts with sample data:

**Products:**
- Laptop (Electronics, $999.99, 10 units)
- Book (Education, $19.99, 50 units)
- Coffee (Food, $4.99, 100 units)

**Customers:**
- John Doe (john@example.com, 25 years old)
- Jane Smith (jane@example.com, 30 years old)
- Bob Johnson (bob@example.com, 35 years old)
