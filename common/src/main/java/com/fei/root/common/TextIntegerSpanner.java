package com.fei.root.common;

import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PengFeifei on 2018/3/8.
 */

public class TextIntegerSpanner {

    public static void colorInteger(TextView textView, @ColorInt int color) {
        CharSequence charSequence = textView.getText();
        int[][] indexs = getIndexs(charSequence.toString());
        SpannableString spannableString = new SpannableString(charSequence);
        for (int[] index : indexs) {
            spannableString.setSpan(new ForegroundColorSpan(color), index[0], index[1]+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(spannableString);
    }
    public static void colorSizeInteger(TextView textView, @ColorInt int color, int pixels) {
        CharSequence charSequence = textView.getText();
        int[][] indexs = getIndexs(charSequence.toString());
        SpannableString spannableString = new SpannableString(charSequence);
        for (int[] index : indexs) {
            spannableString.setSpan(new ForegroundColorSpan(color), index[0], index[1]+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(pixels), index[0], index[1]+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(spannableString);
    }

    private static int[][] getIndexs(String str) {
        String[] arr;
        if (str.contains(",")) {
            arr = str.split(",");
        } else {
            arr = str.split("，");

        }
        int len = arr.length;
        int[][] indexs = new int[len][2];
        for (int i = 0; i < len; i++) {
            int[] index = getIndex(arr[i]);
            if (i == 0) {
                indexs[i][0] = index[0];
                indexs[i][1] = index[1];
                continue;
            }
            indexs[i][0] = index[0] + getPrelength(arr, i);
            indexs[i][1] = index[1] + getPrelength(arr, i);
        }
        return indexs;
    }

    private static int[] getIndex(String str) {
        String regEx = "\\D";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String result = m.replaceAll("").trim();
        int index = str.indexOf(result.charAt(0));
        int indexLast = str.lastIndexOf(result.charAt(result.length() - 1));
        return new int[] {index, indexLast};
    }

    private static int getPrelength(String[] arr, int index) {
        if (index == 0) {
            return 0;
        }
        int res = 0;
        for (int i = index; i > 0; i--) {
            res = res + arr[i - 1].length();
        }
        //index逗号个数
        return res + index;

    }


}
