package com.core.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * @param text   原始文字
     * @param source 源字符串
     * @param des    目标字符串
     * @return 替换后的字符串
     */
    public static String replaceLast(String text, String source, String des) {
        int pos = text.lastIndexOf(source);
        if (pos > -1) return text.substring(0, pos) + des + text.substring(pos + source.length());
        return text;
    }

    /**
     * 获取首字母大写的字符串
     *
     * @param str
     * @return 返回首字母大写的字符串
     */
    public static String capitalize(String str) {
        if (null == str || str.length() == 0) return str;
        return new StringBuffer(str).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * 获取首字母小写的字符串
     *
     * @param str
     * @return 返回首字母为小写的字符串
     */
    public static String unCapitalize(String str) {
        if (null == str || str.length() == 0) return str;
        return new StringBuffer(str).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * 获取去掉中划线的32位小写uuid
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 获取后缀
     *
     * @return
     */
    public static String suffix(String source) {
        if (null == source || source.length() == 0) return source;
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String encodeRoleName(String name){
        String convertValue = "";
        try {
            convertValue = new String(name.getBytes("Windows-1252"),
                    Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
      return convertValue;
    }



    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = new BigInteger("3B0000789CED9A4D48545114C7CF7BF73DB57CE384942039C310829A93E120B4A8442CB391528BD2C948880CB3DC18150ED207D1AE45192643DF194249505263D42EA1A2B281502C215B14D4A2562D1235AA7B2468FFBF2BE5FC1667F7E37FEF997BDFBBF73144F3137B76B9AE9F1426FFCED375BD85C9E5415D9F2FC1E49D770B88F6422ED93557AA895E80722D270F630DB39E75241DC2FA6559ADFD332E957E6E5B89C8A98F532EDD2C8E95AF00EC7767A75DEA1B19A92A04E4F72C93B33F1791E7921F9F1975117982E5D77903ED70F2B7ECCA183C67BFBF7B13221FB89EAF97E7D3ECCA65803C37EC9C8C9635F09CA30F06818659D6EDAD6E1A9DABE83ABC0A90231B665DFA90D50AACED7FC33E5DF7350ACC5910044110840586FD8B6F3761F08292CFB508944B42BA2E865CB2A778D8EB40B9892F65E049C88EB17C1E9CF36E96D161FFE439D783F22E4E467FE78280417221CBE89C27E38F2CB241F960A796B129933D1CD215BBC4921D891B2427BA4345D401CA0D2673F6738D80F21393E494891C34E9F6A049F2051339D764D85F4CE4139D06BB2AC932BA31E226FB79C664CEDBF9AB142A373A5C41790FCB60B220080B073599A96B6631264FB01C00938FE65D22AACCC264CFA7EB16EC09A85E6EBC4A9406B9A48EA8CB440950AEAFD0C97E50AEB95866515F0E26F78FBB1E3D0493A7B9BE05E5765B37AC0A6BB7DAC60DF381C9039EAEC7B1379D6AF0F4F26C0293D72A2DBF01E563BCC26AB1138DF271B3C2E0C678E5D3C929C82515BEA7AF09E0B142259BEF101D02E5932DE91EDD07659B1B760A5C9E9B794B82674F55CD32BAB66F2CD5B125E0DA1EE3E736787C54ABA36D448B40B99465B461FB783F6780B2C39D4E07E5116E18BAB6BFB38CCE3965923C6A92BCC33348EE750D9E9E8A7F2AF42DD9C3C9E0C73415E4C30CBA31EA4C1A366EB025857983D3C87F9F01B7A4E31B48E0724F2FFE7E76AE8D9505D0E5E90CFD28C2871DE8BA65A19FE59DE63FF86348100441100441100441100441100441F8CF5F8A1BA287", 16).toString(10);
        System.out.println(encodeRoleName(str));
    }
}
