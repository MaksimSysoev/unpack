package ru.sysoevm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unpack {

    private final String regex1 = "\\d{1,}\\[[a-z]+\\]";
    private final String regex2 = "([a-z]*(\\d{1,}\\[[a-z]+\\])+[a-z]*)+";
    private final String regex3 = "(\\d{1,}\\[(\\d{1,}\\[[a-z]+\\][a-z]*)+\\])+";

    private Matcher getMatcher(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        return p.matcher(input);
    }

    public String getString(String input) {
        String convert = input;
        Matcher partStr = getMatcher(regex1, input);
        while (partStr.find()) {
            String pack = input.substring(partStr.start(), partStr.end());
            String unpack = pack.replaceAll("[^a-z]", "");
            int count = Integer.parseInt(pack.replaceAll("[^\\d]", ""));
            String buf = "";
            for (int i = 0; i < count; i++) {
                buf = buf + unpack;
            }
            convert = convert.replace(pack, buf);
        }
        return convert;
    }

    public String unpackStr(String input) throws InputErrorException {
        String result = "";
        if (input.matches(regex2) || input.matches(regex3)) {
            result = getString(input);
            if (result.matches(regex2)) {
                result = getString(result);
            }
        } else {
            throw new InputErrorException("Не верный формат ввода");
        }

       return result;
    }
}
