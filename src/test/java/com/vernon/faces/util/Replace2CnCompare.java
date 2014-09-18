package com.vernon.faces.util;

import com.vernon.faces.EmojiHelper;

/**
 * xxx
 * User: chenyuan
 * Date: 2013-12-25
 */
public class Replace2CnCompare {
    public static void main(String[] args) {
        String s1 = "Re有假期place2CndwadwCompare\uD83D\uDC13\uD83Dadwad\uDC15\uD83Ddw\uDC16\uD83D\uDC01\uD83D\uDC03\uD83D\uDC03";
        EmojiHelper.replace2cn("");
        ReplaceOldHelper.replace2cn("");

        compare(s1);
        String s2 = "😄😃😀😊😘😍😉☺️😚😗😙😜😁😳😛😝😔😣😥😩😠😆😴😵😲😆😎👿😯😏👷👵😺😼😾🙈👽💩💫🌟💦💧👎👍✊✊👐👆🙌💪👏💃💇💆🙎🙎👑👡👔👥👤💭";
        s2 = s2 + "👜🎀💙🐰🐨🐮🐗🐑🐴🐧🐄🐅🐐🐁🐊🐫🐪🐆🍄🍁🌺🌷🌸🍂🌵🌗🌓🌒🌖🌏🌋🌌🌠☁️⛅️☀️⭐️⚡️☔️❄️⛄️🌊🌈🌁🌀🎓🎐🎅🎉🔮🎥💻💻📠📻";
        s2 = s2 + "wd🍎🍒🍋🍓🍐🍅🌽🍍🍑🍒🏨🏤🗾🎡🚂🚦🚒🇨🇳🇮🇹🎊🎅🎑🎃🚲🚔🚘🚗🚒🚡💈🚥⬆️↔️🔼🚯🚷🚳🔴⛺️🎱🍫🍪🍨📍🚩🇯🇵🇺🇸🇨🇳🇩🇪🇰🇷🇫🇷🇪🇸🇮🇹🇬🇧";
        compare(s2);
        compare(s2 + s2);

        compare("haha🐓");

    }

    private static void compare(String s) {
        int max = 1000;
        String s1 = "";
        String s2 = "";
        long start3 = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            s1 = ReplaceOldHelper.replace2cn(s);
        }
        long end3 = System.currentTimeMillis();

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            s2 = EmojiHelper.replace2cn(s);
        }
        long end4 = System.currentTimeMillis();

        String ss = s1.equals(s2) + "时间对比,length=" + s.length() +
                ",旧版=" + (end3 - start3)
                + "ms,新版=" + (end4 - start4) + "ms";
        System.out.println(ss);
        System.out.println("--");
    }

}
