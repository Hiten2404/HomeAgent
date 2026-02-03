# HomeAgent 🏠🤖

HomeAgent is an **Android application** used to **control and monitor a local home agent**
running inside a local network.  
The app provides simple controls to manage the agent lifecycle and check its health
status in real time.

---

## ✨ Features

- Start the home agent
- Stop the home agent
- Restart the home agent
- Real-time agent health check
- Status indicator (Running / Stopped)
- Clean and minimal UI

---

## 📱 App Preview

> Screenshots will be added later.

---

## 🛠 Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM
- **Networking:** Retrofit
- **State Management:** ViewModel
- **Build System:** Gradle (Kotlin DSL)

---

## 📂 Project Structure

```
app/
 ├── data/
 │   ├── model/
 │   └── repository/
 ├── network/
 │   └── AgentApiClient.kt
 ├── ui/
 │   ├── MainScreen.kt
 │   └── theme/
 ├── viewmodel/
 │   └── MainViewModel.kt
 ├── MainActivity.kt
 └── MainApplication.kt
```

---

## ⚙️ How It Works

- The app communicates with a locally running home agent via HTTP APIs.
- User actions (Start / Stop / Restart) trigger API calls.
- Agent health is periodically fetched and displayed in the UI.
- UI updates automatically based on agent status.

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Hiten2404/HomeAgent.git
   ```

2. Open the project in **Android Studio**

3. Let Gradle sync completely

4. Connect a physical Android device or start an emulator

5. Run the app

---

## 📌 Current Status

- Initial stable working version
- Core agent controls implemented
- App successfully runs on a physical device
- GitHub repository initialized and stable

---

## 🔮 Planned Improvements

- Better error handling and edge cases
- Auto-refresh agent status
- UI/UX improvements
- Authentication for agent access
- Background monitoring support

---

## 👤 Author

**Hiten Parmar**  
Android Developer (Student)

---

## 📄 License

This project is intended for **learning and personal use**.
