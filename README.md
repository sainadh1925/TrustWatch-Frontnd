# TrustWatch Android Frontend

## 📱 Overview
TrustWatch Android Frontend is a **native Android application UI** developed using **Kotlin and XML**.  
It provides an intuitive user interface for the TrustWatch phishing detection system, allowing users to scan URLs, SMS, and messages for phishing threats.

This repository contains **frontend code only**.  
Backend services are maintained in a separate repository.

---

## 🎯 Purpose of the Frontend
- Provide a clean and user-friendly mobile interface
- Allow users to interact with the phishing detection backend
- Display scan results, alerts, and analytics visually
- Support real-time phishing analysis from an Android device

---

## 🛠️ Technology Stack
- **Language:** Kotlin  
- **UI Design:** XML Layouts  
- **Architecture:** Activity-based Android architecture  
- **Build Tool:** Gradle  
- **Minimum Platform:** Android (Native)

---

## 📂 Project Structure

app/
└── src/
└── main/
├── java/ # Kotlin Activities (UI Logic)
├── res/
│ ├── layout/ # XML UI Screens
│ ├── drawable/ # Icons & UI resources
│ ├── values/ # Colors, themes, strings
│ └── mipmap/ # App icons
└── AndroidManifest.xml # App configuration


---

## 📱 Application Screens
The frontend includes multiple Android screens such as:
- Splash Screen
- Login & Signup
- OTP Verification
- Dashboard
- URL Scan
- SMS & Message Scan
- Threat Details
- Notifications
- Profile & Settings
- Subscription Page

All screens are implemented using **XML layouts** and controlled via **Kotlin activities**.

---

## 🔗 Backend Integration
- Communicates with the TrustWatch Backend via REST APIs
- Sends user input (URL, SMS, message text) for phishing analysis
- Receives threat score, risk level, and detection results
- Displays results in a user-friendly format

---

## 🚀 How to Run the App
1. Open this project in **Android Studio**
2. Let Gradle sync complete
3. Connect an Android device or start an emulator
4. Click **Run ▶**

---

## ❓ Why No HTML Files?
This is a **native Android application**, not a web app.

- Android UI is built using **XML layouts**
- Business logic is written in **Kotlin**
- HTML/CSS/JS are not required for native Android apps

---

## 📌 Note
This repository represents the **complete Android frontend** of the TrustWatch system.  
Backend logic, AI/ML processing, and databases are handled separately.

---

## 👨‍🎓 Academic Declaration
This project is developed for **educational and academic evaluation purposes** to demonstrate Android app development and frontend-backend integration.

---

**TrustWatch – Smart Phishing Protection on Android 📲**

