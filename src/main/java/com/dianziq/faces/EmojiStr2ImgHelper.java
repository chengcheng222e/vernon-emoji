package com.dianziq.faces;

import com.dianziq.faces.util.Emoji;
import com.dianziq.faces.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将含有emoji中文表情的字符串转的是成图片¬
 */
public class EmojiStr2ImgHelper {
    private static Map<String, String> cnKey2ImgMap = new HashMap<String, String>();

    static {
        List<Emoji> emojiList = EmojiData.getEmojiList();
        for (Emoji emoji : emojiList) {
            String cn = emoji.getCnKey();
            String img = emoji.getImg();
            cnKey2ImgMap.put(cn, img);
        }
        putOldEmotion();
    }

    /**
     * 导入旧的表情图片
     */
    private static void putOldEmotion() {
        cnKey2ImgMap.put("[织]", "0.gif");
        cnKey2ImgMap.put("[神马]", "1.gif");
        cnKey2ImgMap.put("[浮云]", "2.gif");
        cnKey2ImgMap.put("[给力]", "3.gif");
        cnKey2ImgMap.put("[围观]", "4.gif");
        cnKey2ImgMap.put("[威武]", "5.gif");
        cnKey2ImgMap.put("[熊猫]", "6.gif");
        cnKey2ImgMap.put("[兔子]", "7.gif");
        cnKey2ImgMap.put("[奥特曼]", "8.gif");
        cnKey2ImgMap.put("[囧]", "9.gif");
        cnKey2ImgMap.put("[互粉]", "10.gif");
        cnKey2ImgMap.put("[礼物]", "11.gif");
        cnKey2ImgMap.put("[呵呵]", "12.gif");
        cnKey2ImgMap.put("[嘻嘻]", "13.gif");
        cnKey2ImgMap.put("[哈哈]", "14.gif");
        cnKey2ImgMap.put("[可爱]", "15.gif");
        cnKey2ImgMap.put("[可怜]", "16.gif");
        cnKey2ImgMap.put("[挖鼻屎]", "17.gif");
        cnKey2ImgMap.put("[吃惊]", "18.gif");
        cnKey2ImgMap.put("[害羞]", "19.gif");
        cnKey2ImgMap.put("[挤眼]", "20.gif");
        cnKey2ImgMap.put("[闭嘴]", "21.gif");
        cnKey2ImgMap.put("[鄙视]", "22.gif");
        cnKey2ImgMap.put("[爱你]", "23.gif");
        cnKey2ImgMap.put("[泪]", "24.gif");
        cnKey2ImgMap.put("[偷笑]", "25.gif");
        cnKey2ImgMap.put("[亲亲]", "26.gif");
        cnKey2ImgMap.put("[生病]", "27.gif");
        cnKey2ImgMap.put("[太开心]", "28.gif");
        cnKey2ImgMap.put("[懒得理你]", "29.gif");
        cnKey2ImgMap.put("[右哼哼]", "30.gif");
        cnKey2ImgMap.put("[左哼哼]", "31.gif");
        cnKey2ImgMap.put("[嘘]", "32.gif");
        cnKey2ImgMap.put("[衰]", "33.gif");
        cnKey2ImgMap.put("[委屈]", "34.gif");
        cnKey2ImgMap.put("[吐]", "35.gif");
        cnKey2ImgMap.put("[打哈气]", "36.gif");
        cnKey2ImgMap.put("[抱抱]", "37.gif");
        cnKey2ImgMap.put("[怒]", "38.gif");
        cnKey2ImgMap.put("[疑问]", "39.gif");
        cnKey2ImgMap.put("[馋嘴]", "40.gif");
        cnKey2ImgMap.put("[拜拜]", "41.gif");
        cnKey2ImgMap.put("[思考]", "42.gif");
        cnKey2ImgMap.put("[汗]", "43.gif");
        cnKey2ImgMap.put("[困]", "44.gif");
        cnKey2ImgMap.put("[睡觉]", "45.gif");
        cnKey2ImgMap.put("[钱]", "46.gif");
        cnKey2ImgMap.put("[失望]", "47.gif");
        cnKey2ImgMap.put("[酷]", "48.gif");
        cnKey2ImgMap.put("[花心]", "49.gif");
        cnKey2ImgMap.put("[哼]", "50.gif");
        cnKey2ImgMap.put("[鼓掌]", "51.gif");
        cnKey2ImgMap.put("[晕]", "52.gif");
        cnKey2ImgMap.put("[悲伤]", "53.gif");
        cnKey2ImgMap.put("[抓狂]", "54.gif");
        cnKey2ImgMap.put("[黑线]", "55.gif");
        cnKey2ImgMap.put("[阴险]", "56.gif");
        cnKey2ImgMap.put("[怒骂]", "57.gif");
        cnKey2ImgMap.put("[心]", "58.gif");
        cnKey2ImgMap.put("[伤心]", "59.gif");
        cnKey2ImgMap.put("[猪头]", "60.gif");
        cnKey2ImgMap.put("[ok]", "61.gif");
        cnKey2ImgMap.put("[耶]", "62.gif");
        cnKey2ImgMap.put("[good]", "63.gif");
        cnKey2ImgMap.put("[不要]", "64.gif");
        cnKey2ImgMap.put("[赞]", "65.gif");
        cnKey2ImgMap.put("[来]", "66.gif");
        cnKey2ImgMap.put("[弱]", "67.gif");
        cnKey2ImgMap.put("[蜡烛]", "68.gif");
        cnKey2ImgMap.put("[钟]", "69.gif");
        cnKey2ImgMap.put("[蛋糕]", "70.gif");
        cnKey2ImgMap.put("[话筒]", "71.gif");

        // 小黄人表情
        cnKey2ImgMap.put("[x不屑]", "x0.gif");
        cnKey2ImgMap.put("[x不要]", "x1.gif");
        cnKey2ImgMap.put("[x沮丧]", "x10.gif");
        cnKey2ImgMap.put("[x伤心]", "x12.gif");
        cnKey2ImgMap.put("[x生气]", "x13.gif");
        cnKey2ImgMap.put("[x吐舌]", "x14.gif");
        cnKey2ImgMap.put("[x兴奋]", "x15.gif");
        cnKey2ImgMap.put("[x点头]", "x4.gif");
        cnKey2ImgMap.put("[x哈哈]", "x5.gif");
        cnKey2ImgMap.put("[x害怕]", "x6.gif");
        cnKey2ImgMap.put("[x好冷]", "x7.gif");
        cnKey2ImgMap.put("[x喝彩]", "x8.gif");
        cnKey2ImgMap.put("[x惊讶]", "x9.gif");
        cnKey2ImgMap.put("[x看我的]", "x11.gif");
        cnKey2ImgMap.put("[x不知道]", "x2.gif");
        cnKey2ImgMap.put("[x打招呼]", "x3.gif");
    }

    static class Pair {
        int left;
        int right;
        String s;

        public Pair(int left, int right, String s) {
            this.left = left;
            this.right = right;
            this.s = s;
        }
    }

    /**
     * 传入[xx],输出对应图片文件名
     *
     * @param cnKey 表情对应的字符串，如[怒骂]
     * @return 图片文件名, 如果没有找到返回null
     */
    public static String cnKey2Img(String cnKey) {
        if (StringUtils.isBlank(cnKey)) {
            return null;
        }
        return cnKey2ImgMap.get(cnKey);
    }

    /**
     * @param s      需要转换成图片名的字符串
     * @param prefix 每一个匹配的表情的前辍
     * @param suffix 每一个匹配的表情的后辍
     * @return
     */
    public static String str2Img(String s, String prefix, String suffix) {
        if (StringUtils.isBlank(s)) {
            return s;
        }

        List<Pair> pairList = findPairs(s);
        if (pairList == null) {
            return s;
        }
        if (prefix == null) {
            prefix = "";
        }

        if (suffix == null) {
            suffix = "";
        }
        return joinByPair(s, pairList, prefix, suffix);
    }

    private static List<Pair> findPairs(String s) {
        String regEx = "(\\[[^\\[]*?\\])";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        List<Pair> pairList = null;
        while (m.find()) {
            if (pairList == null) {
                pairList = new ArrayList<Pair>();
            }
            Pair pair = new Pair(m.start(), m.end(), m.group());
            pairList.add(pair);
        }
        return pairList;
    }


    private static String joinByPair(String s, List<Pair> pairList, String prefix, String suffix) {
        StringBuffer sBuffer = new StringBuffer();
        int left = 0;
        int right = 0;
        for (Pair pair : pairList) {
            left = pair.left;
            if (right < left) {
                sBuffer.append(s.substring(right, left));
            }
            String cn = pair.s;
            String imgName = cnKey2Img(cn);
            if (StringUtils.isNotBlank(imgName)) {
                sBuffer.append(prefix);
                sBuffer.append(imgName);
                sBuffer.append(suffix);
            } else {
                sBuffer.append(cn);
            }
            right = pair.right;
        }

        int length = s.length();
        sBuffer.append(s.substring(right, length));
        return sBuffer.toString();
    }

    public static Map<String, String> getCnKey2ImgMap() {
        return cnKey2ImgMap;
    }


    public static void main(String[] args) {
        String people = "[em哈哈][em高兴][em开口笑][em悠闲][em眨眼][em色][em飞吻][em亲一口][em害羞][em沉思][em笑嘻嘻][em鬼脸][em闭眼][em鄙视~不高兴][em假笑][em冷汗][em深思][emo失望][em困惑][em失望~汗][em糟糕]                                             \n" +
                "[em害怕][em不屈不挠][em哭泣][em大哭][em大笑][em吃惊][em恐怖][em不高兴][em撅嘴][em困倦][em生病][em恶魔][em外星人][em黄心][em蓝心][em紫心][em爱心][em绿心][em心][em心碎][em喜欢2]       \n" +
                "[em一见钟情][em火花][em星光][em心情][em感叹号][em问号][em睡觉][em快跑][em汗水][em多音符][em音符][em火焰][em大便][em真棒][em鄙视][em好][em拳头][em握拳][em胜利][em挥手][em手掌]        \n" +
                "[em手影手掌][em上面][em下面][em右边][em左边][em双手庆祝][em双手合十][em食指][em鼓掌][em强壮][em行人][em跑步][em情侣][em跳舞][em兔女郎][em双手抱头][em双手交叉胸前][em服务员][em鞠躬][em亲吻][em情侣夫妇]                            \n" +
                "[em按摩][em理发][em指甲油][em男孩][em女孩][em女人][em男人][em婴儿][em老奶奶][em老爷爷][em金发碧眼][em瓜皮帽][em包头巾][em建筑工人][em警察][em天使][em公主][em卫兵][em骷髅头][em脚印][em红唇]                                        \n" +
                "[em嘴巴][em耳朵][em眼睛][em鼻子]";
        printIOS5(people);

        String nature = "[em太阳][em打伞][em多云][em雪人][em月亮][em闪电][em龙卷风][em波浪][em猫][em小狗][em老鼠][em仓鼠][em兔子][em狼][em青蛙][em老虎][em树袋熊][em能][em猪头][em母牛][em野猪]                \n" +
                "[em猴脸][em小猴子][em马头][em马][em骆驼][em绵羊][em大象][em蛇][em鸟][em小鸡][em鸡][em企鹅][em毛毛虫][em章鱼][em热带鱼][em鱼][em鲸鱼][em海豚][em花束][em樱花][em郁金香]                \n" +
                "[em好运][em玫瑰][em向日葵][em花朵][em枫叶][em叶子][em落叶][em棕榈树][em仙人掌][em稻穗][em贝壳]";
        printIOS5(nature);

        String objects = "[em松木纹饰][em丝带心][em和服娃娃][em书包][em毕业帽][em鲤鱼幡][em烟火1][em烟火2][em风铃][em赏月仪式][em南瓜灯][em幽灵鬼脸][em圣诞老人][em圣诞树][em礼物][em铃铛][em派对][em气球][em光学光盘][emDVD][em相机]                         \n" +
                "[em摄相机][em电脑][em电视机][em移动电话][em传真机][em电话][em迷你光盘][em录像带][em声音][em广播][em啦啦队][em收音机][em卫星天线][em双大环][em搜索][em开锁][em锁定][em钥匙][em剪刀][em锤子][em电灯泡]                                \n" +
                "[em打电话][em发邮件][em信箱][em邮箱邮筒][em洗澡][em厕所][em座位][em钱袋][em三叉戟][em吸烟][em炸弹][em手枪][em药丸][em注射器][em橄榄球][em篮球][em足球][em棒球][em网球][em高尔夫][em台球]                                            \n" +
                "[em游泳][em冲浪][em滑雪][em黑桃][em红桃][em梅花][em方块][em奖杯][em怪兽][em命中][em红中][em场记板][em记录][em书本][em调色板][em麦克风][em耳机][em喇叭][em萨克斯][em吉它][em歌记号]    \n" +
                "[em运动鞋][em凉鞋][em高跟鞋][em长靴][emT-SHIRT][em领带][em裙子][em和服][em比基尼][em丝带][em礼帽][em皇冠][em女式帽][em雨伞][em公文包][em手提包][em口红][em戒指][em钻石][em咖啡][em茶杯]                                             \n" +
                "[em啤酒][em碰杯][em酒杯][em清酒][em刀叉][em汉堡包][em薯条][em意大利面条][em咖喱饭][em盒饭][em寿司][em饭团][em米饼][em米饭][em汤面][em热气腾腾][em面包][em烹饪][em关东煮][em糯米团][em冰淇淋]                                        \n" +
                "[em刨冰][em生日庆祝][em蛋糕][em苹果][em柑橘][em西瓜][em草莓][em茄子][em西红柿]";
        printIOS5(objects);

        String places = "[em楼房建筑][em学校][em办公楼][em邮局][em医院][em银行][em便利商店][em爱情旅馆][em酒店][em婚礼][em教堂][em百货商店][em夕阳][em黄昏][em取款机][em日本城堡][em欧洲城堡][em帐篷][em工厂][em东京塔][em富士山]                            \n" +
                "[em日出1][em日出2][em自然][em自由女神][em彩虹][em摩天轮][em喷水池][em过山车][em船][em快艇][em帆船][em飞机][em火箭][em自行车][em休闲车][em汽车][em出租车][em公共洗车][em警车][em消防车][em救护车]                                    \n" +
                "[em货车][em电车][em火车站][em高速列车][em火车][em票据][em加油][em红绿灯][em警告][em施工][em符号][em老虎机][em公共汽车站][em理发店][em温泉][em赛车终点][em交叉标志][em日本国旗][em韩国国旗][em中国国旗][em美国国旗]                  \n" +
                "[em法国国旗][em西班牙国旗][em意大利国旗][em俄罗斯国旗][em英国国旗][em德国国旗]";
        printIOS5(places);

        String symbols = "[em数字1][em数字2][em数字3][em数字4][em数字5][em数字6][em数字7][em数字8][em数字9][em数字0][em#号][em上箭头][em下箭头][em左箭头][em右箭头][em右上箭头][em左上箭头][em右下箭头][em左下箭头][em三角1][em三角2]                         \n" +
                "[em快退][em快进][emOK][em新][em_top][em顶][em酷][em电影放映机][em假名KOKO][em信号][em满][em空][em特价][em打折割][em预定][em营业][em有][em无][em月][em申请][em假名SA]                  \n" +
                "[em卫生间][em男厕][em女厕][em婴儿符号][em禁止吸烟][em标志P][em轮椅][em地铁][emWC厕所][em秘密][em祝][em十八禁][em身份ID][em八辐条星号][em符号7][em心装饰][em对比][em震动模式][em手机关机][em上升图表][em货币兑换]                    \n" +
                "[em牧羊座][em金牛座][em双子座][em巨蟹座][em狮子座][em处女座][em天秤座][em天蝎座][em射手座][em摩蝎座][em水瓶座][em双鱼座][em蛇夫座][em六角座][em标志A][em标志B][em标志AB][em标志O][em379][em380][em381]                              \n" +
                "[em12点][em1点][em2点][em3点][em4点][em5点][em6点][em7点][em8点][em9点][em10点][em11点][em圆圈][em打x][em符号C][em符号R][em符号TM]";
        printIOS5(symbols);

    }

    private static void printIOS5(String s) {
        System.out.println("---------");
        List<Emoji> emojiList = EmojiData.getEmojiList();
        List<Pair> pairList = findPairs(s);
        for (Pair pair : pairList) {
            String cn = pair.s;
            for (Emoji emj : emojiList) {
                if (emj.getCnKey().equals(cn)) {
                    System.out.println(emj.getUtf16() + "=" + cn);
                }
            }
        }
    }
}
