package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        // System.out.println("json-muotoinen data:");
        // System.out.println(bodyText);

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        ArrayList<Player> suomalaiset = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                suomalaiset.add(player);
            }
        }
        Collections.sort(suomalaiset);
        System.out.println("Suomalaiset pelaajat pistejärjestyksessä:");
        System.out.println("");
        for (Player player : suomalaiset) {
            System.out.println(player.scoreOutput());
        }

    }
}
