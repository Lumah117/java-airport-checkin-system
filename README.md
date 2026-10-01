# Java Airport Check-In System

A Java airport check-in application developed during my university studies.

The project models passengers, flights and bookings and provides functionality for passenger check-in, baggage-limit processing, excess baggage fee calculation, CSV-based data loading, reporting and a graphical user interface built using Java Swing.

The repository preserves the original coursework implementation and demonstrates my development of object-oriented programming and multi-class application design in Java.

---

## Project Overview

The system is divided into several classes representing different parts of the airport check-in process:

```text
                    CHECK-IN SYSTEM
                          |
          +---------------+---------------+
          |               |               |
          v               v               v
      Passengers        Flights        Bookings
          |               |               |
          +---------------+---------------+
                          |
                          v
                       Check-In
                          |
              +-----------+-----------+
              |                       |
              v                       v
       Baggage Processing       Passenger List
              |
              v
       Excess Baggage Fee
              |
              v
           Reporting
```

A Swing graphical interface provides user input for the booking reference and baggage measurements.

---

## Technologies

- Java
- Object-Oriented Programming
- Java Swing
- Java Collections
- `HashMap`
- `ArrayList`
- File I/O
- CSV data processing
- Event-driven programming

---

## Repository Structure

```text
java-airport-checkin-system/
│
├── README.md
├── LICENSE
│
└── src/
    └── coursework_1/
        ├── booking.java
        ├── checkInSystem.java
        ├── flight.java
        ├── gui.java
        ├── passenger.java
        └── reportGenerator.java
```

The original implementation also references:

```text
passengers.csv
flights.csv
```

These CSV files were used to populate passenger and flight information.

If the original data files are not present in this repository, they were not available within the recovered coursework files.

---

# System Architecture

The project separates the application into several classes with different responsibilities.

```text
                        gui
                         |
                         v
                  checkInSystem
                         |
          +--------------+--------------+
          |              |              |
          v              v              v
      passenger        flight         booking
                         |
                         v
                  Passenger List

                  reportGenerator
                         |
                         v
                  checkInSystem
                         |
                         v
                    Bookings
```

This allows the graphical interface, business logic and underlying data objects to remain separated rather than placing the complete application in a single class.

---

## Passenger Model

`passenger.java` represents an individual passenger.

Each passenger stores:

- Name
- Booking reference
- Flight code
- Check-in status
- Baggage weight
- Baggage volume

Conceptually:

```text
Passenger
│
├── Name
├── Booking Reference
├── Flight Code
├── Checked-In Status
├── Baggage Weight
└── Baggage Volume
```

Getter and setter methods provide access to these values.

The passenger's check-in state can therefore be modified as they progress through the system.

---

## Flight Model

`flight.java` represents a flight within the check-in system.

Each flight contains:

```text
Flight
│
├── Flight Code
├── Destination
├── Carrier
├── Capacity
├── Maximum Baggage Weight
├── Maximum Baggage Volume
└── Passenger List
```

The passenger list is implemented using an `ArrayList`.

When a passenger is added, the implementation first checks whether the number of passengers is below the configured aircraft capacity.

Conceptually:

```text
Add Passenger
      |
      v
Current Passengers
      <
Flight Capacity?
   /       \
 Yes        No
  |          |
  v          v
 Add       Reject
Passenger  Addition
```

The class also provides methods for calculating the total baggage weight and total baggage volume associated with passengers on the flight.

---

## Booking Model

`booking.java` associates a passenger with a flight using a booking reference.

```text
Booking
│
├── Booking Reference
├── Passenger
└── Flight
```

This provides the relationship required for the system to determine which passenger and flight correspond to a particular booking.

---

# Check-In System

`checkInSystem.java` contains the main application logic.

It maintains three maps:

```text
checkInSystem
│
├── passengers
│
├── flights
└── bookings
```

These are implemented using Java `HashMap` collections.

Booking references and flight codes are normalised using trimming and uppercase conversion when records are added.

This provides key-based access to application data rather than requiring the system to search sequentially through every stored object.

---

## Passenger Check-In

The check-in process accepts a booking reference.

The system then attempts to retrieve the corresponding booking.

```text
Booking Reference
       |
       v
Search Bookings
       |
   +---+---+
   |       |
 Found   Not Found
   |       |
   v       v
Retrieve  Display
Passenger Error
+ Flight
   |
   v
Add Passenger
to Flight
   |
   v
Set Checked-In
= true
```

If the booking exists, the associated passenger is added to the flight's passenger list and their check-in state is updated.

---

# Baggage Processing

The system also contains functionality for evaluating passenger baggage against the limits configured for the associated flight.

Each flight defines:

```text
Maximum Baggage Weight
Maximum Baggage Volume
```

The system receives the passenger's actual:

```text
Baggage Weight
Baggage Volume
```

and compares them against those limits.

---

## Excess Baggage Fees

The original implementation calculates fees for both excess weight and excess volume.

### Weight

If:

```text
Actual Weight > Flight Weight Limit
```

the excess amount is charged using a configured rate of:

```text
1.4 per excess weight unit
```

Conceptually:

```text
Weight Fee =
(Actual Weight - Maximum Weight) × 1.4
```

### Volume

If:

```text
Actual Volume > Flight Volume Limit
```

the excess amount is charged at:

```text
1.2 per excess volume unit
```

Conceptually:

```text
Volume Fee =
(Actual Volume - Maximum Volume) × 1.2
```

If both limits are exceeded, the two charges are combined.

```text
                BAGGAGE
                   |
         +---------+---------+
         |                   |
         v                   v
      Weight              Volume
         |                   |
         v                   v
   Above Limit?        Above Limit?
      /    \              /    \
    No     Yes           No     Yes
     |      |             |      |
     |      v             |      v
     |   Weight Fee       |   Volume Fee
     |      |             |      |
     +------+-------------+------+
                   |
                   v
              Total Fee
```

---

# CSV Data Loading

The check-in system includes file-loading functionality for passenger and flight information.

Passenger information is loaded from:

```text
passengers.csv
```

and flight information from:

```text
flights.csv
```

The implementation uses:

```text
BufferedReader
FileReader
```

to read each file line-by-line.

Each line is split using a comma delimiter and converted into the appropriate Java object.

Conceptually:

```text
CSV File
   |
   v
Read Line
   |
   v
Split Fields
   |
   v
Create Object
   |
   +-------> passenger
   |
   +-------> flight
   |
   v
Store in HashMap
```

This provided practical experience with persistent/external data rather than hard-coding all application records directly into the program.

---

# Graphical User Interface

`gui.java` provides a graphical front end using Java Swing.

The interface creates an:

```text
Airport Check-In System
```

window containing input fields for:

```text
Booking Reference

Luggage Weight (kg)

Luggage Volume (m³)
```

together with a:

```text
Check-In
```

button and an output area.

Conceptually:

```text
+--------------------------------+
|      Airport Check-In System   |
+--------------------------------+
| Booking Reference: [________]  |
|                                |
| Luggage Weight:   [________]   |
|                                |
| Luggage Volume:   [________]   |
|                                |
|          [ Check-In ]          |
|                                |
| ------------------------------ |
| Output                         |
|                                |
| Checked in successfully.       |
| Excess luggage fee: ...        |
+--------------------------------+
```

---

## Event-Driven Interaction

The check-in button uses a Java `ActionListener`.

When the user presses the button:

```text
Button Click
     |
     v
Read Booking Reference
     |
     v
Read Baggage Weight
     |
     v
Read Baggage Volume
     |
     v
Process Check-In
     |
     v
Calculate Baggage Fee
     |
     v
Update Output Area
```

This introduced event-driven programming alongside the underlying object-oriented system.

---

# Reporting

`reportGenerator.java` provides functionality for generating a summary of checked-in passengers.

The report iterates through the stored bookings and checks whether each passenger has been checked in.

For checked-in passengers, it collects:

- Passenger name
- Flight code
- Baggage weight
- Baggage volume
- Calculated baggage fee

It also calculates aggregate values:

```text
Total Checked-In Passengers
Total Luggage Weight
Total Luggage Volume
Total Fees Collected
```

The resulting report therefore combines information from several parts of the application's object model.

Conceptually:

```text
Bookings
   |
   v
For Each Booking
   |
   v
Passenger Checked In?
   |
  Yes
   |
   +--> Passenger
   |
   +--> Flight
   |
   +--> Baggage
   |
   +--> Fee
   |
   v
Add to Report
   |
   v
Update Totals
   |
   v
Generate Summary
```

---

# Object-Oriented Design

This project demonstrates several fundamental object-oriented concepts.

## Encapsulation

Application data is stored within dedicated classes and accessed through getter and setter methods.

For example:

```text
Passenger
   |
   +--> getName()
   +--> getBookingReference()
   +--> isCheckedIn()
   +--> setCheckedIn()
```

## Object Relationships

The system models relationships between real-world entities.

```text
Passenger
    |
    v
 Booking
    |
    v
 Flight
```

A booking therefore acts as the connection between a passenger and their corresponding flight.

## Separation of Responsibilities

Different classes perform different roles:

```text
passenger
    -> passenger data

flight
    -> flight data and passenger management

booking
    -> passenger/flight relationship

checkInSystem
    -> application logic and data management

gui
    -> user interaction

reportGenerator
    -> reporting
```

This is considerably easier to maintain than placing the entire application inside a single Java class.

---

# Java Collections

The project uses multiple Java collection types.

## HashMap

`HashMap` is used by the main check-in system to store:

```text
Booking Reference -> Passenger

Flight Code -> Flight

Booking Reference -> Booking
```

This provides direct key-based access to the stored objects.

## ArrayList

Each flight maintains an `ArrayList` of passengers.

This provides a dynamically sized collection representing the passengers associated with that flight.

---

# Concepts Demonstrated

This project provided practical experience with:

- Java
- Object-oriented programming
- Multi-class application design
- Encapsulation
- Object relationships
- Constructors
- Getters and setters
- Java collections
- `HashMap`
- `ArrayList`
- File I/O
- CSV parsing
- Java Swing
- Graphical user interfaces
- Event listeners
- Event-driven programming
- Data validation
- Business logic
- Capacity constraints
- Fee calculations
- Report generation
- Separation of responsibilities

---

# Original Implementation

The source in this repository preserves the original university coursework implementation.

It has intentionally not been extensively rewritten to make the project appear representative of my current Java or software-engineering practices.

This allows the repository to show my programming progression authentically.

---

# Known Limitations

Reviewing the original implementation highlights several areas that would require improvement before the application could be considered production-ready.

### Booking Data Loading

The recovered implementation loads passenger and flight information from CSV files but does not contain a completed process for reconstructing the booking objects that associate those passengers with their corresponding flights.

Because the check-in process depends on the booking map, this would need to be completed for the recovered application to operate end-to-end from the CSV data.

### Input Validation

The GUI converts baggage values directly using numeric parsing.

Invalid user input could therefore generate an exception.

A more robust implementation would validate:

- Empty booking references
- Invalid numbers
- Negative baggage values
- Unknown booking references
- Missing flight information

before attempting the check-in operation.

### Passenger State

The `passenger` constructor accepts a `checkedIn` argument but the original implementation initially assigns the internal state as `false`.

The CSV loader subsequently updates this value, but the constructor behaviour could be made more consistent.

### CSV Parsing

The original implementation uses:

```text
line.split(",")
```

which is sufficient for simple coursework data but is not a complete CSV parser.

Real CSV files may contain:

- Quoted fields
- Embedded commas
- Escaped characters
- Missing values
- Headers

A production implementation would use a dedicated CSV library or more robust parser.

### Naming Conventions

The original class names use lowercase identifiers such as:

```text
passenger
flight
booking
checkInSystem
reportGenerator
```

Standard Java naming conventions would instead use:

```text
Passenger
Flight
Booking
CheckInSystem
ReportGenerator
```

The original names have been retained to preserve the coursework implementation.

### Configuration

File names and baggage-fee rates are currently embedded directly within the source.

A larger application would move these values into configuration or constants.

### Error Handling

File-loading errors are primarily handled by printing stack traces.

A more complete application would provide structured error handling and useful feedback to the user.

---

# How I Would Approach It Today

With my current software-development experience, I would separate the project more formally into layers:

```text
                 USER INTERFACE
                       |
                       v
                  SERVICE LAYER
                       |
             +---------+---------+
             |                   |
             v                   v
       Check-In Service     Baggage Service
             |                   |
             +---------+---------+
                       |
                       v
                 DOMAIN MODEL
                       |
          +------------+------------+
          |            |            |
          v            v            v
      Passenger      Booking       Flight
                       |
                       v
                DATA / REPOSITORY
                       |
                       v
                  CSV / Database
```

I would also introduce:

- Consistent Java naming conventions
- Dedicated exception handling
- Input validation
- Unit tests
- Separate configuration
- More robust CSV parsing
- Explicit booking-data loading
- Better separation between GUI and business logic
- Persistent storage
- Logging
- Build tooling such as Maven or Gradle

---

# Testing

A modern version of the project would include automated tests covering scenarios such as:

```text
Valid booking
     -> check-in succeeds

Unknown booking
     -> check-in rejected

Flight at capacity
     -> passenger not added

Baggage below limits
     -> fee = 0

Weight above limit
     -> weight fee calculated

Volume above limit
     -> volume fee calculated

Both limits exceeded
     -> combined fee calculated

Invalid GUI input
     -> validation message

Checked-in passengers
     -> correctly included in report
```

These tests would make the behaviour of the application easier to verify as functionality is changed.

---

# Portfolio Context

This project represents a significant progression from my earlier introductory Java exercises.

```text
Early Java Exercises
        |
        | Variables
        | Conditionals
        | Arrays
        | Loops
        v
Data Structures / Algorithms
        |
        | Multiple classes
        | Collections
        v
Airport Check-In System
        |
        | Object-oriented design
        | GUI development
        | File I/O
        | Data modelling
        | Business logic
        | Reporting
        v
Larger Software Systems
```

Rather than solving a single programming problem, this project required several components to work together as one application.

It therefore demonstrates an important stage in my progression from learning individual Java programming concepts toward designing software containing multiple interacting classes, external data, user interfaces and application logic.
