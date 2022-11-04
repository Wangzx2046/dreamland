package com.example.otherTest;

import cn.hutool.dfa.StopChar;
import cn.hutool.extra.emoji.EmojiUtil;

public class Test {

    public static void main(String[] args) {

        String str = "孤城孤刀[表情]";

        System.out.println(exist(str));

    }

    public static boolean exist(String str) {

        return EmojiUtil.containsEmoji(str);

    }

    public static boolean exist1(String str) {

        return StopChar.isStopChar(str);

    }

}
