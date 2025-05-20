package com.goormthonuniv.hoseo.hosiktamtam.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KoreanSearchUtil {

    // 한글 초성 배열
    private static final char[] CHOSUNG = {
            'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
            'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };

    // 한글 중성 배열
    private static final char[] JUNGSUNG = {
            'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ',
            'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'
    };

    // 한글 종성 배열
    private static final char[] JONGSUNG = {
            ' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ',
            'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ',
            'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };

    /**
     * 문자열에서 초성만 추출
     * @param text 대상 문자열
     * @return 초성 문자열
     */
    public String extractChosung(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (isKorean(c)) {
                result.append(getChosung(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 한글 여부 확인
     * @param c 검사할 문자
     * @return 한글이면 true
     */
    public boolean isKorean(char c) {
        return c >= '가' && c <= '힣';
    }

    /**
     * 한글 초성 여부 확인
     * @param c 검사할 문자
     * @return 초성이면 true
     */
    public boolean isChosung(char c) {
        for (char chosung : CHOSUNG) {
            if (c == chosung) {
                return true;
            }
        }
        return false;
    }

    /**
     * 한글 문자에서 초성 추출
     * @param c 한글 문자
     * @return 초성
     */
    public char getChosung(char c) {
        int index = (c - '가') / (JUNGSUNG.length * JONGSUNG.length);
        return CHOSUNG[index];
    }

    /**
     * 초성 문자열로 검색
     * @param targetList 검색 대상 문자열 리스트
     * @param chosungKeyword 초성 키워드
     * @return 일치하는 문자열 리스트
     */
    public List<String> searchByChosung(List<String> targetList, String chosungKeyword) {
        List<String> result = new ArrayList<>();
        for (String target : targetList) {
            String targetChosung = extractChosung(target);
            if (targetChosung.contains(chosungKeyword)) {
                result.add(target);
            }
        }
        return result;
    }

    /**
     * 키워드가 초성인지 일반 텍스트인지 판별
     * @param keyword 검색 키워드
     * @return 초성이면 true
     */
    public boolean isChosungKeyword(String keyword) {
        for (char c : keyword.toCharArray()) {
            if (!isChosung(c) && isKorean(c)) {
                return false;
            }
        }
        return true;
    }
}