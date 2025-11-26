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
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Usage

### 1. Standard Responses

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

**Response Format:**

```json
{
  "success": true,
  "message": "Success",
  "data": { ... },
  "code": 200,
  "requestId": "123e4567-e89b-12d3-a456-426614174000"
}
```

### 2. Exception Handling

The SDK automatically handles exceptions if you scan the package. Ensure your Spring Boot application scans the SDK packages:

```java
@SpringBootApplication(scanBasePackages = {"com.your.app", "com.common.sdk"})
public class YourApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
}
```

Throw `BaseException` for custom errors:

```java
throw new BaseException(404, "User not found");
```

### 3. Correlation ID

The `CorrelationFilter` automatically checks for `x-correlation-id` header. If missing, it generates a new UUID. This ID is included in the MDC (Mapped Diagnostic Context) and the response body.

## Requirements

- Java 21
- Spring Boot 3.x

## License

[MIT](LICENSE)
