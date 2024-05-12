import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    private static final String[] VOWEL = {"a", "e", "i", "o", "u"};
    private static final Pattern INVALID_PASSWORD_FORMAT = Pattern.compile("[aeiou]{3,}|[^aeiou]{3,}|([^eo])\\1");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String password = br.readLine();
            if (password.equals("end"))
                break;

            String result = "";

            if (!containsVowel(password) || INVALID_PASSWORD_FORMAT.matcher(password).find()) {
                result = "is not acceptable.";
            } else {
                result = "is acceptable.";
            }


            sb.append(String.format("<%s> ", password))
                    .append(result)
                    .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    private static boolean containsVowel(String password) {
        for (String v : VOWEL) {
            if (password.contains(v)) {
                return true;
            }
        }
        return false;
    }
}
