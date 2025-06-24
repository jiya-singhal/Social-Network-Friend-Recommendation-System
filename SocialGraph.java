import java.util.*;

public class SocialGraph {
    private Map<String, Set<String>> graph;

    public SocialGraph() {
        graph = new HashMap<>();
    }

    public void addUser(String user) {
        user = user.toLowerCase();
        graph.putIfAbsent(user, new HashSet<>());
    }

    public void addFriendship(String user1, String user2) {
        user1 = user1.toLowerCase();
        user2 = user2.toLowerCase();
        addUser(user1);
        addUser(user2);
        graph.get(user1).add(user2);
        graph.get(user2).add(user1);
    }

    public Set<String> getFriends(String user) {
        user = user.toLowerCase();
        return graph.getOrDefault(user, new HashSet<>());
    }

    public Set<String> getAllUsers() {
        return graph.keySet();
    }

    public boolean userExists(String user) {
        user = user.toLowerCase();
        return graph.containsKey(user);
    }

    public void printGraph() {
        for (String user : graph.keySet()) {
            System.out.println(user + " -> " + graph.get(user));
        }
    }
    private Map<String, Set<String>> blockedMap = new HashMap<>();

// Block user2 for user1
public void blockUser(String user1, String user2) {
    user1 = user1.toLowerCase();
    user2 = user2.toLowerCase();
    blockedMap.putIfAbsent(user1, new HashSet<>());
    blockedMap.get(user1).add(user2);
}

// Check if user1 blocked user2 or vice versa
public boolean isBlocked(String user1, String user2) {
    user1 = user1.toLowerCase();
    user2 = user2.toLowerCase();
    return (blockedMap.getOrDefault(user1, new HashSet<>()).contains(user2) ||
            blockedMap.getOrDefault(user2, new HashSet<>()).contains(user1));
}

    
}
