# Common SDK

A shared Spring Boot library providing standardized API responses, global exception handling, and utility components for microservices.

## Features

- **Standardized API Responses**: Unified JSON structure for success and error responses.
- **Global Exception Handling**: Centralized handling for common Spring exceptions and custom business exceptions.
- **Request Correlation**: Automatic `x-correlation-id` tracking for distributed tracing.
- **Response Utilities**: Helper classes to simplify controller response construction.

## Installation

### Maven

Add the repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/1prabhakarpal/common_sdk</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>com.common.sdk</groupId>
    <artifactId>common_sdk</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Usage

### 1. Setup

Ensure your Spring Boot application scans the SDK packages by adding `@SpringBootApplication(scanBasePackages = ...)`:

```java
@SpringBootApplication(scanBasePackages = {"com.your.app", "com.common.sdk"})
public class YourApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
}
```

### 2. Standard Responses

Use `ResponseHandler` in your controllers to return standardized responses.

```java
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final ResponseHandler responseHandler;
    private final UserService userService;

    @GetMapping("/{id}")
    public GenericApiResponse<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return responseHandler.ok(user);
    }
    
    @PostMapping
    public GenericApiResponse<Void> createUser(@RequestBody UserDto user) {
        userService.save(user);
        return responseHandler.ok(APIResponseCode.SUCCESS);
    }
}
```

**Success Response Format:**

```json
{
  "success": true,
  "message": "Success",
  "data": {
    "id": 1,
    "name": "John Doe"
  },
  "code": 200,
  "requestId": "123e4567-e89b-12d3-a456-426614174000"
}
```

### 3. Exception Handling

The SDK provides a `BaseGlobalExceptionHandler` that automatically handles:

- **Validation Errors**: `MethodArgumentNotValidException` (Returns 422)
- **Missing Parameters**: `MissingServletRequestParameterException` (Returns 440)
- **Missing Headers**: `MissingRequestHeaderException` (Returns 442)
- **Type Mismatch**: `MethodArgumentTypeMismatchException` (Returns 443)
- **Malformed JSON**: `HttpMessageNotReadableException` (Returns 444)
- **Method Not Allowed**: `HttpRequestMethodNotSupportedException` (Returns 405)
- **Unsupported Media Type**: `HttpMediaTypeNotSupportedException` (Returns 415)
- **Internal Errors**: `NullPointerException`, `IllegalArgumentException`, `IllegalStateException` (Returns 500/501/502/503)

**Custom Exceptions:**

Throw `BaseException` to return specific error codes:

```java
// Throwing a 404 Not Found
throw new BaseException(404, "User not found");
```

**Error Response Format:**

```json
{
  "success": false,
  "message": "User not found",
  "data": null,
  "code": 404,
  "requestId": "123e4567-e89b-12d3-a456-426614174000"
}
```

### 4. Distributed Tracing (Correlation ID)

The `CorrelationFilter` automatically manages the `x-correlation-id` header.

- **Incoming Request**: If `x-correlation-id` is present, it is used. If missing, a new UUID is generated.
- **MDC**: The ID is added to the SLF4J MDC as `correlationId` for logging.
- **Response**: The ID is returned in the `x-correlation-id` response header and the `requestId` field in the JSON body.

### 5. Utilities

**CommonUtils**

Helper methods for accessing context information.

```java
// Get the current request's correlation ID
String requestId = CommonUtils.getCurrentRequestId();
```

## Requirements

- Java 21
- Spring Boot 3.5.8
