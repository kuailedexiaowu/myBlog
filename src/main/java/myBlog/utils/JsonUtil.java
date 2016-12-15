package myBlog.utils;

import java.io.IOException;

/**
 * Created by kj on 2016/10/21.
 */
public class JsonUtil {
    private static char[] hexdigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private  JsonUtil(){

    }
    public static String formatStrToJson(String val)
    {
        StringBuilder sBuilder = new StringBuilder();
        try {
            for (int i = 0; i < val.length(); i++) {
                char ch = val.charAt(i);
                if (((ch > '#') && (ch != '\\')) || (ch == ' ')) {
                    sBuilder.append(ch);
                }
                else {
                    switch (ch) {
                        case '"':
                        case '\\':
                            sBuilder.append('\\');
                            sBuilder.append(ch);
                            break;
                        case '\r':
                            sBuilder.append('\\');
                            sBuilder.append('r');
                            break;
                        case '\n':
                            sBuilder.append('\\');
                            sBuilder.append('n');
                            break;
                        case '\t':
                            sBuilder.append('\\');
                            sBuilder.append('t');
                            break;
                        case '\b':
                            sBuilder.append('\\');
                            sBuilder.append('b');
                            break;
                        case '\f':
                            sBuilder.append('\\');
                            sBuilder.append('f');
                            break;
                        default:
                            if (ch <= '\037')
                                unicodeEscape(sBuilder, ch);
                            else {
                                sBuilder.append(ch);
                            }
                    }
                }
            }
            return sBuilder.toString(); } catch (Exception exception) {
        }
        return val;
    }

    protected static void unicodeEscape(StringBuilder out, int ch)
            throws IOException
    {
        out.append('\\');
        out.append('u');
        out.append(hexdigits[(ch >>> 12)]);
        out.append(hexdigits[(ch >>> 8 & 0xF)]);
        out.append(hexdigits[(ch >>> 4 & 0xF)]);
        out.append(hexdigits[(ch & 0xF)]);
    }
}
