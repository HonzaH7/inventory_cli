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

A few decisions that show the thinking behind the code:

- **Generic repository** — `Repository<ID, T>` with an in-memory implementation
  (`InMemoryRepository`) backed by a `Map`. Reusable for any entity type, not just chemicals.
- **`Chemical` as an immutable entity** — `equals`/`hashCode` are based only on the
  catalog ID (entity identity), all fields are `final`, and the mutable hazard set is
  defensively copied in and out so the object can never be changed after construction.
- **`Optional` instead of `null`** — `findById` returns `Optional<Chemical>`, forcing
  callers to handle the "not found" case explicitly.
- **Custom exception hierarchy** — `InventoryException` → `InvalidChemicalException` /
  `ChemicalNotFoundException`, with cause chaining, all handled at the CLI boundary.
- **Modern Java domain model** — `enum` with fields/methods (`Hazard`), `EnumSet` for
  hazard flags, and a `sealed` interface (`StorageLocation`) with pattern-matching `switch`.

## Unit tests

The test suite (JUnit 5) covers the repository and validation logic, using
`@BeforeEach` for isolated setup, `@ParameterizedTest` for boundary cases, and
`assertThrows` to verify that invalid input throws the right exception.

```bash
./mvnw clean test
```
