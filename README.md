<!--  
  README for KmpDiceRoller  
  Author: Mohd Anas  
-->

<p align="center">
  
<img width="9680" height="2000" alt="Cover" src="https://github.com/user-attachments/assets/ad892774-cfce-487c-a9c7-fdd87df9555b" />
  <h1 align="center">Stylish</h1>
  <p align="center">Stylish is a beautifully crafted E-Commerce Android application built with Kotlin, Jetpack Compose, and MVVM architecture.</p>
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

✨ Key Highlights

🧭 Onboarding Experience – Greet users with stunning intro screens showcasing Stylish’s elegance.

🔐 Secure Authentication – Seamless Login, Signup, and Forgot Password using Firebase/Auth API.

👗 Dynamic Product Catalog – Browse the latest collections with filters and categories.

💖 Wishlist – Save your favorite fashion pieces and revisit them anytime.

🛒 Smart Cart System – Add, update, or remove items with instant price updates.

💳 Checkout Flow – Modern checkout UI with address, payment, and order summary screens.

📦 Order Tracking – Track your orders and delivery status in real time.

🌙 Dark Mode Ready – Experience the app in your preferred light or dark theme.

⚡ Offline-First Architecture – Continue exploring even when your connection drops.

🧠 Tech Stack
Layer	Technology
🖥️ UI	Jetpack Compose (Material 3)
🧩 Architecture	MVVM + Repository Pattern
💾 Local Storage	Room Database
🌐 Networking	Retrofit + OkHttp
🔥 Backend	Firebase / REST APIs
🔄 Reactive Flow	Kotlin Coroutines + Flow
🧠 Dependency Injection	Hilt
🎨 Design System	Material Design 3
🧭 Core Features
Feature	Description
🚀 Splash & Onboarding	Engaging intro with app branding and quick tips.
🔐 Authentication	Login, Register, and Password Recovery with Firebase.
👗 Product Browsing	Explore trending fashion items and categories.
💖 Wishlist	Save and manage your favorite products.
🛒 Cart	Add to cart, update quantities, and manage checkout.
💬 Product Details	Detailed view with images, prices, and reviews.
👤 User Profile	Manage user info, orders, and settings.
🌟 Why Stylish?

“Stylish” isn’t just another shopping app — it’s your personalized fashion experience, crafted with clean architecture, modern UI, and performance at its core.

It’s designed for developers, learners, and fashion lovers alike — blending practical coding architecture with a premium shopping UI that truly feels alive.

📱 Screens & Flow

Splash → Onboarding → Auth

Product List → Product Detail → Wishlist / Cart

Checkout → Profile → Order History

🧱 Project Structure
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

🚀 Run the App
# Clone repository
git clone https://github.com/username/Stylish.git

# Open in Android Studio
# Build and run on emulator or device

