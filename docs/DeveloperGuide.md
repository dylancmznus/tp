# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition


## Instructions for Manual Testing

Below is a suggested guide for **manual testing** of the ClinicEase application in a Command Line Interface (CLI) environment. 

---

## 1. Getting Started

1. **Compilation**
   - Navigate to the project’s root folder (where the `ClinicEase.java` and other `.java` files reside).
   - Compile the source files. For example:
     ```
     javac *.java
     ```
   - Alternatively, use your favorite IDE’s build tool.

2. **Launching the Application**
   - Run the compiled main class:
     ```
     java ClinicEase
     ```
   - You should see a welcome message that looks like this:
     ```
     --------------------------------------------------------------------------------
     Welcome to ClinicEase!
     Type a command, or 'bye' to exit.
     --------------------------------------------------------------------------------
     >
     ```

3. **Exiting the Application**
   - To exit, type:
     ```
     bye
     ```
   - Expected output:
     ```
     --------------------------------------------------------------------------------
     Goodbye!
     --------------------------------------------------------------------------------
     ```
   - The program will then terminate.

---

## 2. Testing Patient Management Features

### 2.1 Add a New Patient

**Command Format**: `add-patient n/NAME ic/NRIC dob/BIRTHDATE g/GENDER p/PHONE a/ADDRESS [h/MEDICAL_HISTORY]`

- `[h/MEDICAL_HISTORY]` is optional and can be multiple entries separated by commas.

**Steps to Test**
1. Input:
   ```
   add-patient n/Alice Tan ic/S1234567A dob/1990-01-01 g/F p/91234567 a/123 Bedok Road h/High blood pressure
   ```
2. Expected output:
   ```
   --------------------------------------------------------------------------------
   Patient added successfully: Alice Tan
   --------------------------------------------------------------------------------
   ```
3. The system should store the new patient data (written to `patient_data.txt`).

**Additional Test Cases**
- **Missing required fields** (e.g., no `a/ADDRESS`) should produce an `InvalidInputFormatException` message.
- **Duplicate NRIC** should produce a `DuplicatePatientIDException`.

---

### 2.2 List All Patients

**Command Format**: `list-patient`

**Steps to Test**
1. Input:
   ```
   list-patient
   ```
2. If you have existing patients, the system displays each patient in a list format:
   ```
   ------------------------------------------Patient Details------------------------------------------
   Patient NRIC: S1234567A Name: Alice Tan ...
   --------------------------------------------------------------------------------
   ```
3. If no patients exist, the system prints:
   ```
   --------------------------------------------------------------------------------
   No patients have been added.
   --------------------------------------------------------------------------------
   ```


---

### 2.3 View a Patient by NRIC

**Command Format**: `view-patient NRIC`

**Steps to Test**
1. Input:
   ``` 
   view-patient S1234567A
   ```
2. If the patient is found, detailed information is displayed. Otherwise, the system notifies you that no matching patient was found.

---

### 2.4 Delete a Patient

**Command Format**: `delete-patient NRIC`


**Steps to Test**
1. Input:
   ``` 
   delete-patient S1234567A
   ```
2. If the patient exists, the system confirms deletion:
   ```
   --------------------------------------------------------------------------------
   No patients have been added.
   --------------------------------------------------------------------------------
   ```
3. If the patient doesn’t exist, it notifies you accordingly.

---


### 2.5 Edit Patient Information

**Command Format** `edit-patient ic/NRIC [n/NAME] [dob/BIRTHDATE] [g/GENDER] [a/ADDRESS] [p/PHONE]`

- `ic/NRIC` is required to locate the patient.
- The remaining fields are optional; include only those you want to edit.

**Steps to Test**
1. Input:
   ```
   edit-patient ic/S1234567A n/Alice Tan g/F a/321 Jurong Avenue
   ```
2. Expected output upon success:
   ```
   --------------------------------------------------------------------------------
   Patient with NRIC S1234567A updated successfully.
   Edit-patient command executed.
   --------------------------------------------------------------------------------
   ```
3. Use `view-patient S1234567A` to confirm the updated details.

---

## 3. Testing Medical History Features

### 3.1 Store Medical History

**Command Format**: `store-history n/NAME ic/NRIC h/HISTORY`

- `h/HISTORY` can contain multiple entries separated by commas.

**Steps to Test**
1. Input:
   ```
   store-history n/Bob Lee ic/S7654321B h/Diabetes,High cholesterol

   ```
2. If the patient doesn’t exist, the system creates a new one and prints a confirmation message. If the patient exists, it simply adds new history entries.

---

### 3.2 View Medical History

**Command Format**:
1. By NRIC: `view-history ic/NRIC`
2. By Name: `view-history NAME`


**Steps to Test**
1. Input:
   ```
   view-history ic/S7654321B
   ```
2. Displays the patient’s history if found. Otherwise, notifies you that it cannot find the patient.

---

### 3.3 Edit Medical History

**Command Format**: `edit-history ic/NRIC old/OLD_TEXT new/NEW_TEXT`

**Steps to Test**
1. Input:
   ```
   edit-history ic/S7654321B old/Diabetes new/Type 2 Diabetes
   ```
2. If `old/Diabetes` matches an existing record, the system replaces it with `Type 2 Diabetes` and prints a confirmation message.

---

## 4. Testing Appointment Features

### 4.1 Add an Appointment

**Command Format**: `add-appointment ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`

- Date: `yyyy-MM-dd`
- Time: `HHmm` (24-hour format)

**Steps to Test**
1. Input:
   ```
   add-appointment ic/S1234567A dt/2025-12-01 t/0930 dsc/Dental Checkup
   ```
2. If the patient is found, the system adds the appointment and shows a success message. If the patient doesn’t exist, it prints an error.

---

### 4.2 List Appointments

**Command Format**: `list-appointment`

**Steps to Test**
1. Input:
   ```
   list-appointment
   ```

2. Shows all appointments if any exist. Otherwise, prints a “No appointments found” message.

---

### 4.3 Sort Appointments

**Command Format**:
1. by date:`sort-appointment byDate`
2. by appointment id: `sort-appointment byId`

**Steps to Test**
1. Input:
   ```
   sort-appointment byDate
   ```
- Appointments should be sorted chronologically.

2. Input:
   ```
   sort-appointment byId
   ```
- Appointments should be sorted by their `Axxx` IDs.

---

### 4.4 Mark and Unmark an Appointment

**Command Format**: `mark-appointment APPOINTMENT_ID unmark-appointment APPOINTMENT_ID`

**Steps to Test**
1. Input:
   ```
   mark-appointment A100
   ```
- The system marks the appointment as done (`[X]`).
2. Input:
   ```
   unmark-appointment A100
   ```
- The system reverts the appointment to undone (`[ ]`).

---

### 4.5 Find an Appointment by NRIC

**Command Format**: `find-appointment NRIC`

**Steps to Test**
1. Input:
   ``` 
   find-appointment S1234567A
   ```
2. If any matching appointment is found, it prints the details. Otherwise, it prints “No appointment found.”

---

## 5. Error Handling Scenarios

- **Unknown Commands**
- If you type something invalid like `randomCommand`, the system should respond:
 ```
 Unknown command. Please try again.
 ```
- **Missing or Invalid Parameters**
- For instance, `add-appointment` missing the `dt/DATE` should trigger an error message (`InvalidInputFormatException`).
- **Storage Failures**
- If there’s an I/O error with reading or writing to `patient_data.txt`, you might see `UnloadedStorageException`.

---

## 6. Comprehensive Test Workflow

1. **Add multiple patients** and confirm they appear correctly with `list-patient`.
2. **Add detailed medical histories** with `store-history`; verify them using `view-history`.
3. **Add appointments** to different patients and use `list-appointment`, `sort-appointment`, `mark-appointment`, etc. to test appointment functionality.
4. **Delete a patient** and confirm the removal.
5. **Exit** the program with `bye`.

---