# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed. <br> 
   **Mac users:** Ensure you have the precise JDK version prescribed 
   [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest version of `Duke` from [here](http://link.to/duke).   
3. Copy the file to the folder you want to use as the home folder for ClinicEase.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the 
   `java -jar ClinicEase.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:
   - `add-patient n/John ic/S1234567D dob/2003-04-06 g/M p/91218188 a/123 Main St`: <br>
     Adds a patient named `John` to the patient list.
   - `delete-patient S1234567D`: Deletes the patient with NRIC `S1234567D` in the patient list. 
   - `bye`: Exits the app.
6. Refer to the Features below for details of each command.


## Features 
> [!NOTE]
> - Words in `UPPER_CASE` represent parameters that must be provided by the user. <br>
    e.g. in `view-patient NRIC`, `NRIC` is a parameter which can be used as `view-patient S1234567D`.
> - Items in square brackets are optional. <br>
    e.g `ic/NRIC [n/NAME] [dob/BIRTHDATE]` can be used as `ic/S1234567D n/John` or as `ic/S1234567D dob/2002-06-07`.
> - Every parameter must be supplied by the user. <br>
    e.g. if the command specifies `add-appointment ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`, the user
    must fill in all parameters for the input to be valid.
> - Parameters must be entered in the **specified order**. <br>
    e.g. if the command specifies `ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`, the user
    must follow this exact sequence for the input to be valid.
> - Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored. <br>
    e.g. if the command specifies `list-patient 12345`, it will be interpreted as `list-patient`.
> - Command words are **case-insensitive**. <br>
    e.g. `liST-paTIEnt` will be interpreted as `list-patient`.

### Adding an appointment: `add-appointment`
Adds a new appointment to the list of appointment.

Format: `add-appointment ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`

* The patient with the specified `NRIC` **must** exist must exist in the system.
* `DATE` format: `yyyy-MM-dd`, where `yyyy` is year, `MM` is month,
  `dd` is day (e.g., `2025-03-31`).  
* `TIME` format: `HHmm` in 24-hour format (e.g., `1430` for 2:30 PM).

Example of usage: 

`add-appointment ic/S1234567D dt/2025-03-31 t/1200 dsc/Annual checkup`

### Deleting an appointment: `delete-appointment`
Deletes a specified appointment from the appointment list.

Format: `delete-appointment APPOINTMENT_ID`

* The `APPOINTMENT_ID` refers to the unique identifier assigned by the program (e.g., "A1XX") to an appointment.
* The `APPOINTMENT_ID` can be found in the displayed appointment list when using the list-appointment command.

Example of usage:

`delete-appointment A100`

### Sorting appointments: `sort-appointment`
Sorts the appointments in the appointment list.

Format: `sort-appointment byDate` or `sort-appointment byId`

* The `sort-appointment byDate` sorts the appointments by date and time in **ascending order**. 
* The `sort-appointment byId` sorts the appointments by `APPOINTMENT_ID` in **ascending order**.

Example of usage:

* `sort-appointment byDate`
* `sort-appointment byId`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

| Action                | Format, Examples                                                                                                                                                                               |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add patient           | `add-patient n/NAME ic/NRIC dob/BIRTHDATE g/GENDER p/PHONE a/ADDRESS`<br/> e.g. `add-patient n/John Doe ic/S1234567D dob/1999-12-12 g/M p/98765432 a/123 Main Street h/Diabetes, Hypertension` |
| Delete patient        | `delete-patient NRIC`<br/> e.g. `delete-patient S1234567D`                                                                                                                                     |
| Edit patient          | `edit-patient ic/NRIC [n/NAME] [dob/BIRTHDATE] [g/GENDER] [a/ADDRESS] [p/PHONE]`<br/> e.g. `edit-patient ic/S1234567D n/Billy Joe dob/1999-12-21`                                              |
| List patient          | `list-patient`                                                                                                                                                                                 |
| View patient          | `view-patient NRIC`<br/> e.g. `view-patient S1234567D`                                                                                                                                         |
| Store medical history | `store-history n/NAME ic/NRIC h/MEDICAL_HISTORY`<br/> e.g. `store-history n/John Doe ic/S1234567D h/Depression`                                                                                |
| View medical history  | `view-history NRIC` or `view-history NAME`<br/> e.g. `view-history S1234567D` or `view-history John Doe`                                                                                       |
| Edit medical history  | `edit-history ic/NRIC old/OLD_TEXT new/NEW_TEXT`<br/> e.g. `edit-history ic/S1234567D old/Depression new/Obesity`                                                                              |
| Add appointment       | `add-appointment ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`<br/> e.g. `add-appointment ic/S1234567D dt/2025-06-15 t/1400 dsc/Annual Checkup`                                                      |
| Delete appointment    | `delete-appointment APPOINTMENT_ID`<br/> e.g. `delete-appointment A123`                                                                                                                        |
| List appointment      | `list-appointment`                                                                                                                                                                             |
| Mark appointment      | `mark-appointment APPOINTMENT_ID`<br/> e.g. `mark-appointment A101`                                                                                                                            |
| Unmark appointment    | `unmark-appointment APPOINTMENT_ID`<br/> e.g. `unmark-appointment A101`                                                                                                                        |
| Sort appointment      | `sort-appointment byDate` or `sort-appointment byId`                                                                                                                                           |
| Find appointment      | `find-appointment PATIENT_NRIC`<br/> e.g. `find-appointment S1234567D`                                                                                                                         |

# About us



