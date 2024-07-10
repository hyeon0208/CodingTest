import java.io.*;
import java.util.*;

public class Main {
    private static int P;
    private static int M;
    private static List<Room> rooms = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            Player player = new Player(level, name);
            boolean joined = false;

            for (Room room : rooms) {
                if (room.canJoin(level)) {
                    room.addPlayer(player);
                    joined = true;
                    break;
                }
            }

            if (!joined) {
                Room newRoom = new Room(level);
                newRoom.addPlayer(player);
                rooms.add(newRoom);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Room room : rooms) {
            if (room.isFull()) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }

            Collections.sort(room.players);
            for (Player player : room.players) {
                sb.append(player.level).append(" ").append(player.name).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static class Player implements Comparable<Player> {
        String name;
        int level;

        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }

    private static class Room {
        int minLevel, maxLevel;
        List<Player> players = new ArrayList<>();

        Room(int level) {
            this.minLevel = level - 10;
            this.maxLevel = level + 10;
        }

        boolean canJoin(int level) {
            return level >= minLevel && level <= maxLevel && players.size() < M;
        }

        void addPlayer(Player player) {
            players.add(player);
        }

        boolean isFull() {
            return players.size() == M;
        }
    }
}
