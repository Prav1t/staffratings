## Testing Approach

I used a mix of slice and integration tests as per asked for in the assignment:

- Validation tests: JUnit + Jakarta Validation to confirm invalid email, out-of-range scores, and missing required fields are rejected.
- Persistence tests: `@DataJpaTest` using an embedded H2 database to verify saving/retrieving works and delete removes entries.
- Web/controller tests: `@SpringBootTest` + `@AutoConfigureMockMvc` integration tests.

I also used integration style controller tests to avoid Mockito/ByteBuddy compatibility issues with Java 25.
