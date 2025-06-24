import java.util.*;

public class SocialGraph {

    private Map<String, Set<String>> graph;
    private Map<String, Set<String>> blockedMap;
    private Map<String, String> parent;  // DSU Parent Map

    public SocialGraph() {
        graph = new HashMap<>();
        blockedMap = new HashMap<>();
        parent = new HashMap<>();
    }

    // Add new user to the graph and DSU
    public void addUser(String user) {
        user = user.toLowerCase();
        graph.putIfAbsent(user, new HashSet<>());
        blockedMap.putIfAbsent(user, new HashSet<>());
        parent.putIfAbsent(user, user);  // Initialize DSU parent
    }

    // Add undirected friendship and union in DSU
    public void addFriendship(String user1, String user2) {
        user1 = user1.toLowerCase();
        user2 = user2.toLowerCase();
        addUser(user1);
        addUser(user2);
        graph.get(user1).add(user2);
        graph.get(user2).add(user1);
        union(user1, user2);  // Union the sets
    }

    // Get all friends of a user
    public Set<String> getFriends(String user) {
        user = user.toLowerCase();
        return graph.getOrDefault(user, new HashSet<>());
    }

    // Get all users in the graph
    public Set<String> getAllUsers() {
        return graph.keySet();
    }

    // Check if user exists
    public boolean userExists(String user) {
        user = user.toLowerCase();
        return graph.containsKey(user);
    }

    // Print the entire social graph (for console)
    public void printGraph() {
        for (String user : graph.keySet()) {
            System.out.println(user + " -> " + graph.get(user));
        }
    }

    // --- Blocking Feature ---

    // Block user2 for user1
    public void blockUser(String user1, String user2) {
        user1 = user1.toLowerCase();
        user2 = user2.toLowerCase();
        blockedMap.putIfAbsent(user1, new HashSet<>());
        blockedMap.get(user1).add(user2);
    }

    // Check if either user has blocked the other
    public boolean isBlocked(String user1, String user2) {
        user1 = user1.toLowerCase();
        user2 = user2.toLowerCase();
        return blockedMap.getOrDefault(user1, new HashSet<>()).contains(user2) ||
               blockedMap.getOrDefault(user2, new HashSet<>()).contains(user1);
    }

    // --- DSU for Friend Circles ---

    // Find root of user (with path compression)
    private String find(String user) {
        parent.putIfAbsent(user, user);
        if (!parent.get(user).equals(user)) {
            parent.put(user, find(parent.get(user)));  // Path compression
        }
        return parent.get(user);
    }

    // Union two users' sets
    private void union(String user1, String user2) {
        String root1 = find(user1);
        String root2 = find(user2);
        if (!root1.equals(root2)) {
            parent.put(root2, root1);  // Merge sets
        }
    }

    // Return friend circles using DSU
    public Map<String, List<String>> getFriendCirclesDSU() {
        Map<String, List<String>> circles = new HashMap<>();
        for (String user : graph.keySet()) {
            String root = find(user);
            circles.putIfAbsent(root, new ArrayList<>());
            circles.get(root).add(user);
        }
        return circles;
    }
}
