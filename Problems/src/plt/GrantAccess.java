package plt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrantAccess {
    Map<User, Set<Node>> accesses = new HashMap<>();

    class User {
        String name;
    }

    class Node {
        List<Node> nodes;
    }

}
