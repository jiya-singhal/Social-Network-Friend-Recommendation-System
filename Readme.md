# Friend Recommendation System 🤝

A Java-based project that simulates a real-world friend suggestion engine using core Graph Data Structures and Algorithms. This system recommends friends based on mutual connections, allows blocking users, and detects friend communities.

---

## ✨ Project Features

### 🤖 Core Functionalities

* **Add Users**
* **Add Friendships** (Undirected Graph)
* **Recommend Friends**
* **Show Mutual Friends** (Names & Count)
* **Exclude Blocked Users** from suggestions
* **Detect Friend Circles** (Connected Components)
* **View Social Graph**
* **GUI Interface with Swing** (Enhanced Styling)


---

## 📊 Graph Concepts Used

| Graph Concept            | Purpose                                        |
|--------------------------|------------------------------------------------|
| **Adjacency List**       | Store users and friendships efficiently        |
| **Breadth-First Search** | Recommend friends (2-hop neighbors)            |
| **Depth-First Search**   | Detect connected components (friend circles)   |
| **HashMap & HashSet**    | Fast user lookup, friendship, and block logic |
| **Sorting**              | Sort recommendations by mutuals, alphabetically |

---

## 🚀 Advanced Features

- 🔤 **Case-insensitive input handling**
- 🧍‍🤝‍🧍 **Mutual Friend Detection with Names**
- 📶 **Sorted Suggestions** — By mutual friends count & then by name
- ⛔ **Block Users** — Blocked users are excluded from recommendations
- 🌐 **Friend Circles** — Community detection using DFS
- 📋 **Social Graph Viewer** — View entire graph adjacency list
- 🎨 **Fancy GUI Styling** — Colors, emojis & button grouping

---
## 🌐 Demo Video & Screenshots

> [🎥 Watch the Demo Video](https://your-demo-link.com)
> [📄 View GUI Screenshot](./screenshots/fancy-ui.png)

---

## 📁 How to Run

#### 1. GUI Version:
```bash
javac UI.java SocialGraph.java RecommendationEngine.java
java UI
```

### 2. Or Run Console Version:

```bash
javac Main.java SocialGraph.java RecommendationEngine.java
java Main
```

---

## 📂 File Structure

```
├── Main.java                     # Console menu interface
├── UI.java                       # Swing GUI
├── SocialGraph.java              # Graph data structures and logic
├── RecommendationEngine.java     # Recommendation and DFS/BFS logic
└── README.md
```


---

## ✍️ Author

**Jiya Singhal**
CS Engineering | 2nd Year | Friend Recommendation System Project

---

