# Eat-Well-Live-Wel
The "Eat Well, Live Well" Android app addresses these challenges by leveraging the Spoonacular API and modern Android development technologies. The app provides users with a personalized food discovery experience, offering a wide range of recipes tailored to their preferences and dietary needs.

Here's a detailed breakdown of each screen with its contents, including the UI components like text, buttons, images, and their placement, along with the navigation and interactions:

---

### 1. **Splash Screen**

- **Contents**:
  - **Logo**: App logo centered on the screen.
  - **Animation**: A short animation (e.g., a food-related animation or logo reveal) using Lottie.
  - **Background Color**: A solid or gradient background.
  
- **Placement**:
  - The app logo appears in the center of the screen.
  - The animation can be placed around the logo, or the logo itself can be animated.
  
- **Buttons**: No buttons on this screen.
  
- **Navigation**:
  - After the animation, the screen automatically navigates to the **Onboarding Screen** or **Home Screen** (if the user has previously completed onboarding).

---

### 2. **Onboarding Screen**

- **Contents**:
  - **Page 1**:
    - **Title**: "Discover New Recipes" at the top center.
    - **Description**: "Explore thousands of recipes from around the world." placed just below the title.
    - **Image**: A large image (or Lottie animation) of various recipe cards placed in the middle.
    - **Buttons**: 
      - **Next** button at the bottom right ("Next" in bold) to go to the next page.
      - **Skip** button at the bottom left, allowing the user to skip onboarding and go to the **Home Screen**.
      
  - **Page 2**:
    - **Title**: "Plan Your Meals" at the top center.
    - **Description**: "Create custom meal plans for the week with a click." placed below the title.
    - **Image**: A meal planner calendar image in the center.
    - **Buttons**: 
      - **Next** button at the bottom right.
      - **Back** button at the bottom left.
      
  - **Page 3**:
    - **Title**: "Generate Grocery Lists" at the top center.
    - **Description**: "Automatically create a grocery list based on your meal plan." placed below the title.
    - **Image**: A grocery list animation or image.
    - **Buttons**: 
      - **Get Started** button at the center bottom, which completes the onboarding process and navigates to the **Home Screen**.
      - **Back** button at the bottom left.
      
- **Navigation**:
  - The **Next**, **Back**, and **Skip** buttons allow users to navigate between onboarding pages or directly to the **Home Screen**.
  
---

### 3. **Home Screen**

- **Contents**:
  - **Search Bar**: Positioned at the top, allowing users to search for recipes by keywords or ingredients.
  - **Featured Section**: 
    - **Title**: "Featured Recipes" at the top of the section.
    - **Carousel of Recipe Cards**: Horizontally scrollable cards with images, recipe names, and short descriptions (e.g., prep time, calories).
  - **Categories Section**:
    - **Title**: "Browse by Category" below the featured section.
    - **Category Chips**: Buttons like "Breakfast," "Lunch," "Dinner," "Vegetarian," etc., that users can tap to filter recipes by category.
  - **Popular Recipes**: 
    - **Title**: "Popular Recipes" at the top of the section.
    - **Recipe List/Grid**: A vertically scrollable list or grid of trending recipes with images, names, and ratings.

- **Buttons**:
  - **Recipe Cards**: Clicking any recipe card takes the user to the **Recipe Detail Screen**.
  - **Category Chips**: Clicking on a category chip filters the recipes accordingly.

- **Navigation**:
  - **Bottom Navigation** with options to go to:
    - **Home** (current screen)
    - **Search**
    - **Favorites**
    - **Meal Planner**
    - **Profile**

---

### 4. **Recipe Search Screen**

- **Contents**:
  - **Search Bar**: At the top, where users can type ingredients, recipe names, or cuisines.
  - **Filter Icon**: Next to the search bar, opening a filter dialog with options for diet (vegetarian, vegan, etc.), cuisine, preparation time, etc.
  - **Search Results**:
    - **Recipe List**: A scrollable list of recipes with images, names, and brief descriptions (prep time, calories, ratings).
    - **Pagination**: Automatic loading of more recipes as the user scrolls (using Paging 3).
  
- **Buttons**:
  - **Recipe Cards**: Clicking on a recipe card opens the **Recipe Detail Screen**.
  - **Filter Button**: Clicking the filter icon opens the filter options.
  
- **Navigation**:
  - **Bottom Navigation** to access:
    - **Home**
    - **Favorites**
    - **Meal Planner**
    - **Profile**
  
---

### 5. **Recipe Detail Screen**

- **Contents**:
  - **Recipe Image**: A high-quality image at the top.
  - **Title**: Recipe name below the image.
  - **Action Buttons**:
    - **Favorite Button** (heart icon) to mark/unmark as a favorite.
    - **Share Button** (share icon) to share the recipe link.
  - **Ingredients Section**:
    - **Title**: "Ingredients" as a header.
    - **List of Ingredients**: A scrollable list of ingredients, each with quantities.
    - **Add to Grocery List Button**: Button next to each ingredient allowing users to add items to their grocery list.
  - **Instructions Section**:
    - **Title**: "Instructions" as a header.
    - **Steps**: A step-by-step list of instructions with a progress tracker (completed steps can be checked off).
  - **Nutrition Facts Section**:
    - **Title**: "Nutrition Facts" as a header.
    - **Details**: Calories, protein, carbs, fats, etc., displayed in a compact view.
  
- **Buttons**:
  - **Favorite Button**: At the top near the recipe title.
  - **Add to Grocery List Button**: Next to each ingredient.
  - **Share Button**: At the top right.
  
- **Navigation**:
  - **Back Button**: At the top-left corner to return to the previous screen.
  - **Bottom Navigation**: Allows navigation back to **Home**, **Favorites**, **Meal Planner**, and **Profile**.

---

### 6. **Favorites Screen**

- **Contents**:
  - **Favorites List/Grid**: A scrollable list or grid of saved favorite recipes, each displayed with an image, recipe name, and an icon to remove from favorites.
  - **Empty State**: If no favorites exist, show a message: "No favorites yet. Start adding recipes you love!" with a link back to **Home** or **Search** to find recipes.

- **Buttons**:
  - **Recipe Cards**: Clicking on any recipe takes the user to the **Recipe Detail Screen**.
  - **Remove Favorite Button**: A heart icon next to each recipe that users can click to remove from favorites.
  
- **Navigation**:
  - **Bottom Navigation**: Access to **Home**, **Search**, **Meal Planner**, and **Profile**.

---

### 7. **Meal Planner Screen**

- **Contents**:
  - **Calendar View**: A weekly or monthly calendar where users can assign recipes to specific days.
  - **Add Recipe Button**: A FAB (Floating Action Button) to add a recipe to a specific day (opens the **Recipe Search** screen).
  - **Meal Plan List**: For each day, display the assigned meals (e.g., breakfast, lunch, dinner) along with the recipe names.
  - **Auto-Generate Button**: A button at the bottom of the screen that auto-generates a meal plan using the **Generate Meal Plan API**.

- **Buttons**:
  - **Add Recipe FAB**: At the bottom-right corner to add recipes to specific days.
  - **Auto-Generate Button**: At the bottom center to generate meal plans automatically.
  
- **Navigation**:
  - **Bottom Navigation**: Access to **Home**, **Search**, **Favorites**, and **Profile**.

---

### 8. **Grocery List Screen**

- **Contents**:
  - **Checklist View**: A list of all grocery items, with checkboxes next to each item. Items are grouped by categories (e.g., fruits, vegetables, dairy).
  - **Clear All Button**: A button at the top-right to clear the entire grocery list.
  - **Add Custom Item Button**: A FAB to manually add a new item to the grocery list.

- **Buttons**:
  - **Checkboxes**: Users can check/uncheck items as they shop.
  - **Clear All Button**: At the top-right to clear the list.
  - **Add Custom Item FAB**: At the bottom-right to add custom items.
  
- **Navigation**:
  - Can be accessed from the **Meal Planner** or **Recipe Detail Screen**.

---

### 9. **Profile Screen**

- **Contents**:
  - **Profile Information**:
    - **Avatar**: Profile picture at the top (default image if not set).
    - **Name and Email**: Below the avatar.
  - **Dietary Preferences**:
    - **Title**: "Dietary Preferences."
    - **Options**: Vegan, Vegetarian, Gluten-Free, etc.
  - **

Account Settings**: Links to **Edit Profile**, **Manage Subscriptions**, and **Logout** options.
  - **Support Links**: "Help & Support," "Privacy Policy," and "Terms of Service."

- **Buttons**:
  - **Edit Profile**: Button next to profile information to edit name, avatar, etc.
  - **Logout Button**: At the bottom of the screen.

---

This layout provides a clean, modern, and intuitive user experience for the Spoonacular-based recipe app.
