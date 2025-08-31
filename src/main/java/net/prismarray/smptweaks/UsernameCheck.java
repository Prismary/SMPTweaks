package net.prismarray.smptweaks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsernameCheck {

    /**
     * Checks, whether a given username represents a valid Minecraft account
     * NEVER RUN IN MAIN THREAD, USE BUKKIT SCHEDULER!
     *
     * @param username username to check
     * @return true, if account exists, else false
     */
    public static boolean isValidUsername(String username) {
        try {
            // Mojang-API: gibt 200 + JSON zurück, wenn Account existiert
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
                    return reader.readLine() != null;
                }
            } else {
                // 204 oder 404 ⇒ kein Account
                return false;
            }
        } catch (Exception e) {
            // z. B. Timeout oder Netzwerkfehler
            return false;
        }
    }
}