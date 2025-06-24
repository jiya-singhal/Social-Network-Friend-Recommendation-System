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

## 🔮 Graph Concepts Used

| Concept                  | Purpose                                     |
| ------------------------ | ------------------------------------------- |
| **Adjacency List**       | Store users and their friends (graph edges) |
| **Breadth-First Search** | Recommend friends via 2-level traversal     |
| **Connected Components** | Detect friend circles using DFS             |
| **HashMap & HashSet**    | Efficient user/block tracking               |
| **Sorting Algorithms**   | Prioritize friend recommendations           |

---

## 🚀 Advanced Features

* ✅ **Case-insensitive Inputs** — All user inputs are normalized
* ✅ **Mutual Friend Detection** — Lists common friends by name
* ✅ **Sorted Suggestions** — First by number of mutuals, then alphabetically
* ✅ **Block Users** — Bidirectional exclusion from suggestions
* ✅ **Friend Circle Detection** — DFS to find connected clusters
* ✅ **Graph Print** — Displays full social graph (adjacency list)

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

