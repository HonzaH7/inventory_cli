# Inventory CLI

An in-memory command-line application for managing a chemical inventory.
Add, withdraw, list and look up chemicals — each with a catalog ID, category,
concentration and a set of hazards. Built as a hands-on project to practice
core Java: collections, generics, streams, records/enums, exceptions and testing.

## Features

- **Add** a chemical (name, catalog ID, category, concentration, hazards)
- **Withdraw** (remove) a chemical by its ID
- **List** all chemicals
- **Find** a chemical by ID
- **Input validation** with clear, user-friendly error messages (the app never crashes on bad input)

## Tech stack

- **Java 21**
- **Maven** (via the bundled Maven Wrapper — no separate install needed)
- **JUnit 5** for testing

## Getting started

### Build & run the tests

```bash
./mvnw clean test
```

> On Windows use `.\mvnw clean test` (or `mvnw.cmd clean test`).

### Run the application

Run the `inventory.cli.Main` class from your IDE, or from the command line:

```bash
./mvnw compile
java -cp target/classes inventory.cli.Main
```

## Example session

```text
Welcome to our chemical inventory
Press numbers to choose an action.
1. Add a chemical
2. Withdraw a chemical
3. List all chemicals
4. Find a chemical by an ID
5. Exit
> 1
Enter Chemical formula: NaOH
Enter ID: 012394
Enter category (acid/base/salt): base
Enter concentration: 1
Enter properties (etching, corrosive, eyeburns) or empty: corrosive, etching
Chemical NaOH successfully saved.
> 3
Chemical{name='NaOH', id='012394', category=BASE, concentration=1.0, hazards=[CORROSIVE, ETCHING]}
> 5
Bye!
```

Invalid input is handled gracefully:

```text
> 1
...
Enter category (acid/base/salt): plasma
Error: Unknown category: plasma
```

## Design highlights

Patterns and principles applied:

- **Repository pattern** — generic `Repository<ID, T>` + in-memory implementation
- **Dependency injection** — repository injected into `Cli`; `Main` as composition root
- **Layered packages** — `domain` · `repository` · `cli` · `exception`
- **Single Responsibility** — UI, parsing, domain, and storage each isolated
- **Immutable entity** — `Chemical`: `final` fields, defensive copies, identity by ID
- **Custom exception hierarchy** — with cause chaining, handled at the CLI boundary
- **`Optional`** over `null` for lookups
- **Modern Java** — generics, `enum` / `EnumSet`

## Unit tests

The test suite (JUnit 5) covers the repository and validation logic, using
`@BeforeEach` for isolated setup, `@ParameterizedTest` for boundary cases, and
`assertThrows` to verify that invalid input throws the right exception.

```bash
./mvnw clean test
```
