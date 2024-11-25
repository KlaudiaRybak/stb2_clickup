package pl.akademiaqa.properties;

import java.util.ResourceBundle;

public class ClickUpProperties {

    private static final String TOKEN = "clickup.token";
    private static final String TEAM_ID = "clickup.team.id";

    public static String getToken(){
        return getProperty(TOKEN);
    }                                                                           // 2.
    public static String getTeamId(){                                           // 3.
        return getProperty(TEAM_ID);
    }
    private static String getProperty(String key) {                             // 1. prywatne metody zawsze pod publicznymi
        return ResourceBundle.getBundle("clickup").getString(key);
    }


}
