<!--  
  README for Stylish  
  Author: Mohd Anas  
-->

<p align="center">
  <img width="9680" height="2000" alt="Cover" src="https://github.com/user-attachments/assets/ad892774-cfce-487c-a9c7-fdd87df9555b" />
  <h1 align="center">Stylish</h1>
  <p align="center">A beautifully crafted E-Commerce Android application built with Kotlin, Jetpack Compose, and MVVM architecture.</p>
  <p align="center">
    <a href="https://github.com/MohdAnas1971/e_commerce/stargazers">
      <img alt="GitHub stars" src="https://img.shields.io/github/stars/MohdAnas1971/e_commerce?style=for-the-badge" />
    </a>
    <a href="https://github.com/MohdAnas1971/e_commerce/actions">
      <img alt="CI Status" src="https://img.shields.io/github/actions/workflow/status/MohdAnas1971/e_commerce/ci.yml?style=for-the-badge" />
    </a>
    <a href="https://github.com/MohdAnas1971/e_commerce/issues">
      <img alt="GitHub issues" src="https://img.shields.io/github/issues/MohdAnas1971/e_commerce?style=for-the-badge" />
    </a>
    <a href="https://github.com/MohdAnas1971/e_commerce/network/members">
      <img alt="GitHub forks" src="https://img.shields.io/github/forks/MohdAnas1971/e_commerce?style=for-the-badge" />
    </a>
    <a href="LICENSE">
      <img alt="License" src="https://img.shields.io/github/license/MohdAnas1971/e_commerce?style=for-the-badge" />
    </a>
    <img alt="Made with ❤️" src="https://img.shields.io/badge/Made%20with-%E2%9D%A4-red?style=for-the-badge" />
  </p>
</p>

---

## 🏷️ Table of Contents

- [About Stylish](#about-stylish)
- [Features](#features)
- [Screenshots](#screenshots)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)
- [FAQ](#faq)

---

## 🛍️ About Stylish

**Stylish** is a modern E-Commerce app for Android, built with the latest technologies and best practices. It offers a seamless shopping experience, beautiful UI, and robust architecture, making it ideal for both end-users and developers as a learning resource.

---

## ✨ Features

| Feature                | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| 🧭 Onboarding          | Stunning intro screens showcasing Stylish’s elegance.                        |
| 🔐 Authentication      | Secure Login, Signup, and Password Recovery via Firebase/Auth API.           |
| 👗 Product Catalog     | Dynamic browsing with filters and categories.                                |
| 💖 Wishlist            | Save favorite fashion pieces and revisit anytime.                            |
| 🛒 Cart System         | Add, update, or remove items with instant price updates.                     |
| 💳 Checkout Flow       | Modern checkout with address, payment, and order summary screens.            |
| 📦 Order Tracking      | Real-time tracking of orders and delivery status.                            |
| 🌙 Dark Mode           | Toggle between light and dark themes.                                        |
| ⚡ Offline-First       | Explore products even without an active internet connection.                  |
| 🧑‍💼 User Profile      | Manage profile, order history, and settings.                                 |
| 💬 Product Details     | View images, prices, reviews, and detailed info for each product.            |

---

## 📸 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/ad892774-cfce-487c-a9c7-fdd87df9555b" width="500" alt="Splash Screen"/>
  <!-- Add more screenshots here as needed -->
</p>

---

## 🧠 Tech Stack

| Layer              | Technology                              |
|--------------------|-----------------------------------------|
| 🖥️ UI              | Jetpack Compose (Material 3)            |
| 🧩 Architecture    | MVVM + Repository Pattern                |
| 💾 Local Storage   | Room Database                           |
| 🌐 Networking      | Retrofit + OkHttp                       |
| 🔥 Backend         | Firebase / REST APIs                    |
| 🔄 Reactive Flow   | Kotlin Coroutines + Flow                |
| 🧠 DI              | Hilt                                    |
| 🎨 Design System   | Material Design 3                       |

---

## 📂 Project Structure

```ascii
app/
 └── src/
     └── main/
         └── java/com/example/stylish/
             ├── data/
             │   ├── local/
             │   ├── remote/
             │   └── repository/
             ├── model/
             ├── ui/
             │   ├── auth/
             │   ├── onboarding/
             │   ├── splash/
             │   ├── product/
             │   ├── wishlist/
             │   ├── cart/
             │   └── profile/
             └── viewmodel/
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (latest version recommended)
- JDK 11+
- Internet connection for Firebase features

### Installation

```bash
# Clone the repository
git clone https://github.com/MohdAnas1971/e_commerce.git

# Open in Android Studio
# Build and run on emulator or physical device
```

### Firebase Setup

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/).
2. Download `google-services.json` and place it in `app/`.
3. Enable Authentication and Firestore Database.

---

## 🤝 Contributing

We welcome contributions of all kinds!

- Fork the repo
- Create your feature branch (`git checkout -b feature/my-feature`)
- Commit your changes (`git commit -am 'Add new feature'`)
- Push to the branch (`git push origin feature/my-feature`)
- Open a Pull Request

See [CONTRIBUTING.md](CONTRIBUTING.md) for more info.

---

## 📖 License

Distributed under the MIT License. See [LICENSE](LICENSE) for details.

---

## 📬 Contact

- **Author:** Mohd Anas ([MohdAnas1971](https://github.com/MohdAnas1971))
- **Project Issues:** [GitHub Issues](https://github.com/MohdAnas1971/e_commerce/issues)
- **Email:** [Your Email Here]

---

## ❓ FAQ

**Q: Can I use this project commercially?**  
A: Yes, as per the MIT License.

**Q: Where are API keys stored?**  
A: Use local properties or environment variables; never commit secrets to the repository.

**Q: How do I report bugs or request features?**  
A: Open an issue [here](https://github.com/MohdAnas1971/e_commerce/issues).

---

<p align="center">
  <b>Stylish — Your Fashion, Your Way!</b>
</p>
