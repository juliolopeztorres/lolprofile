package oob.lolprofile.Util;

public class RoleNamesParser {
    public static String parse(String roleName) {
        switch (roleName) {
            case "DUO_SUPPORT":
                return "SUPPORT";
            default:
                return roleName;
        }
    }

    public static String getChampionAPIKey(String roleNameParsed) {
        switch (roleNameParsed) {
            case "SUPPORT":
                return "DUO_SUPPORT";
            default:
                return roleNameParsed;
        }
    }
}
