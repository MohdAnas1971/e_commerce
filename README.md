| Feature                  | Description                                                 | File / Folder                                                                                  |
| ------------------------ | ----------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| 🛒 **Product List**      | Displays all available products with filtering and sorting. | [ProductList.kt](app/src/main/java/com/example/app/ui/product/ProductList.kt)                  |
| 💖 **Wishlist**          | Allows users to add/remove products to wishlist.            | [Wishlist.kt](app/src/main/java/com/store/ecommerceapplication/presentation/wishlist/Wishlist.kt)                       |
| 🧾 **Product Details**   | Shows detailed view of selected product.                    | [ProductDetailScreen.kt](app/src/main/java/com/example/app/ui/product/ProductDetailScreen.kt)  |
| 🔍 **Search**            | Search products by name or category.                        | [SearchBar.kt](app/src/main/java/com/example/app/ui/components/SearchBar.kt)                   |
| 🗃️ **Repository Layer** | Handles data fetching (local + remote).                     | [ProductRepository.kt](app/src/main/java/com/example/app/data/repository/ProductRepository.kt) |
| 💾 **Database**          | Room entities and DAO interfaces.                           | [AppDatabase.kt](app/src/main/java/com/example/app/data/local/AppDatabase.kt)                  |
| 🌐 **Network API**       | Retrofit API interface for product data.                    | [ProductApi.kt](app/src/main/java/com/example/app/data/remote/ProductApi.kt)                   |
