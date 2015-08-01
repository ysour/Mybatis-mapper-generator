package com.linkin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类，包含大量通用方法
 * 
 * @author Alex Huang
 * @email huangyu407@qq.com
 * @date 2014-9-24
 */
public class StringUtil {

	/**
	 * 检查字符串是否为空(空字符串不算空)
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null) = true
	 * StringUtil.isEmpty("") = true
	 * StringUtil.isEmpty(" ") = false
	 * StringUtil.isEmpty("bob") = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		return str == null || str.length() == 0;
	}

	public static boolean isAnyEmpty(CharSequence... strArr) {
		if (strArr == null)
			return true;

		for (CharSequence str : strArr) {
			if (isEmpty(str))
				return true;
		}
		return false;
	}

	/**
	 * 检查字符串是否不为空(空字符串不算空)
	 * 
	 * <pre>
	 * StringUtil.isNotEmpty(null) = false
	 * StringUtil.isNotEmpty("") = false
	 * StringUtil.isNotEmpty(" ") = true
	 * StringUtil.isNotEmpty("bob") = true
	 * StringUtil.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(CharSequence str) {
		return !StringUtil.isEmpty(str);
	}

	/**
	 * 检查字符串是否为空(空字符串也算空) StringUtil.isBlank(null) = true StringUtil.isBlank("")
	 * = true StringUtil.isBlank(" ") = true StringUtil.isBlank("bob") = false
	 * StringUtil.isBlank("  bob  ") = false </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(CharSequence str) {
		int len;
		if (str == null || (len = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否为空(空字符串也算空)
	 * 
	 * <pre>
	 * StringUtil.isBlank(null) = true
	 * 	StringUtil.isBlank("") = true
	 * StringUtil.isBlank(" ") = true
	 * StringUtil.isBlank("bob") = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int len;
		if (str == null || (len = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否不为空(空字符串也算空)
	 * 
	 * <pre>
	 * StringUtil.isNotBlank(null) = false
	 * StringUtil.isNotBlank("") = false
	 * StringUtil.isNotBlank(" ") = false
	 * StringUtil.isNotBlank("bob") = true
	 * StringUtil.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(CharSequence str) {
		return !StringUtil.isBlank(str);
	}

	/**
	 * 检查字符串是否不为空(空字符串也算空)
	 * 
	 * <pre>
	 * StringUtil.isNotBlank(null) = false
	 * StringUtil.isNotBlank("") = false
	 * StringUtil.isNotBlank(" ") = false
	 * StringUtil.isNotBlank("bob") = true
	 * StringUtil.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}

	/**
	 * 检查字符串是否全部有字母组成.
	 * 
	 * <pre>
	 * StringUtil.isAlpha(null)   = false
	 * StringUtil.isAlpha("")     = true
	 * StringUtil.isAlpha("  ")   = false
	 * StringUtil.isAlpha("abc")  = true
	 * StringUtil.isAlpha("ab2c") = false
	 * StringUtil.isAlpha("ab-c") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		for (int i = str.length(); i > 0; i--) {
			if (Character.isLetter(str.charAt(i - 1)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否有数字[0~9]组成
	 * 
	 * <pre>
	 * StringUtil.isDigits(&quot;&quot;) = false;
	 * StringUtil.isDigits(null) = false;
	 * StringUtil.isDigits(&quot;123&quot;) = true;
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigits(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否一个合法的数字
	 * 
	 * <pre>
	 * StringUtil.isNumeric(&quot;&quot;) = false;
	 * StringUtil.isNumeric(null) = false;
	 * StringUtil.isNumeric(&quot;123&quot;) = true;
	 * StringUtil.isNumeric(&quot;123f&quot;) = true;
	 * StringUtil.isNumeric(&quot;123l&quot;) = true;
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (StringUtil.isEmpty(str))
			return false;
		char[] chars = str.toCharArray();
		int sz = chars.length;
		boolean hasExp = false;
		boolean hasDecPoint = false;
		boolean allowSigns = false;
		boolean foundDigit = false;
		// deal with any possible sign up front
		int start = (chars[0] == '-') ? 1 : 0;
		if (sz > start + 1) {
			if (chars[start] == '0' && chars[start + 1] == 'x') {
				int i = start + 2;
				if (i == sz) {
					return false; // str == "0x"
				}
				// checking hex (it can't be anything else)
				for (; i < chars.length; i++) {
					if ((chars[i] < '0' || chars[i] > '9')
							&& (chars[i] < 'a' || chars[i] > 'f')
							&& (chars[i] < 'A' || chars[i] > 'F')) {
						return false;
					}
				}
				return true;
			}
		}
		sz--; // don't want to loop to the last char, validate it afterwords
		// for type qualifiers
		int i = start;
		// loop to the next to last char or to the last char if we need another
		// digit to
		// make a valid number (e.g. chars[0..5] = "1234E")
		while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				foundDigit = true;
				allowSigns = false;

			} else if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				hasDecPoint = true;
			} else if (chars[i] == 'e' || chars[i] == 'E') {
				// we've already taken care of hex.
				if (hasExp) {
					// two E's
					return false;
				}
				if (!foundDigit) {
					return false;
				}
				hasExp = true;
				allowSigns = true;
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (!allowSigns) {
					return false;
				}
				allowSigns = false;
				foundDigit = false; // we need a digit after the E
			} else {
				return false;
			}
			i++;
		}
		if (i < chars.length) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				// no type qualifier, OK
				return true;
			}
			if (chars[i] == 'e' || chars[i] == 'E') {
				// can't have an E at the last byte
				return false;
			}
			if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				// single trailing decimal point after non-exponent is ok
				return foundDigit;
			}
			if (!allowSigns
					&& (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
				return foundDigit;
			}
			if (chars[i] == 'l' || chars[i] == 'L') {
				// not allowing L with an exponent
				return foundDigit && !hasExp;
			}
			// last character is illegal
			return false;
		}
		// allowSigns is true iff the val ends in 'E'
		// found digit it to make sure weird stuff like '.' and '1E-' doesn't
		// pass
		return !allowSigns && foundDigit;
	}

	/**
	 * 判断字符否是中文字符
	 * 
	 * @param c
	 * @return boolean
	 * 
	 */
	public static boolean isChineseChar(char c) {
		return (c >= '\u4E00' && c <= '\u9FA5');
	}

	/**
	 * 计算字符串出现次数
	 * 
	 * <pre>
	 * StringUtil.countMatches(null, *)       = 0
	 * StringUtil.countMatches("", *)         = 0
	 * StringUtil.countMatches("abba", null)  = 0
	 * StringUtil.countMatches("abba", "")    = 0
	 * StringUtil.countMatches("abba", "a")   = 2
	 * StringUtil.countMatches("abba", "ab")  = 1
	 * StringUtil.countMatches("abba", "xxx") = 0
	 * </pre>
	 * 
	 * @param str
	 * @param sub
	 * @return
	 */
	public static int count(String str, String sub) {
		return count(str, sub, 0);
	}

	/**
	 * 计算字符串出现次数
	 * 
	 * @param source
	 * @param sub
	 * @param start
	 *            start index
	 * @return
	 */
	public static int count(String source, String sub, int start) {
		int count = 0;
		int j = start;
		int sublen = sub.length();
		if (sublen == 0) {
			return 0;
		}
		while (true) {
			int i = source.indexOf(sub, j);
			if (i == -1) {
				break;
			}
			count++;
			j = i + sublen;
		}
		return count;
	}

	/**
	 * 计算字符串出现次数
	 * 
	 * @param source
	 * @param c
	 * @return
	 */
	public static int count(String source, char c) {
		return count(source, c, 0);
	}

	/**
	 * 计算字符串出现次数
	 * 
	 * @param source
	 * @param c
	 * @param start
	 * @return
	 */
	public static int count(String source, char c, int start) {
		int count = 0;
		int j = start;
		while (true) {
			int i = source.indexOf(c, j);
			if (i == -1) {
				break;
			}
			count++;
			j = i + 1;
		}
		return count;
	}

	/**
	 * 往左截断指定字符
	 * 
	 * <pre>
	 * StringUtil.substringBefore(null, *)      = null
	 * StringUtil.substringBefore("", *)        = ""
	 * StringUtil.substringBefore("abc", "a")   = ""
	 * StringUtil.substringBefore("abcba", "b") = "a"
	 * StringUtil.substringBefore("abc", "c")   = "ab"
	 * StringUtil.substringBefore("abc", "d")   = "abc"
	 * StringUtil.substringBefore("abc", "")    = ""
	 * StringUtil.substringBefore("abc", null)  = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return "";
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * 往右截断指定字符
	 * 
	 * <pre>
	 * StringUtil.substringAfter(null, *)      = null
	 * StringUtil.substringAfter("", *)        = ""
	 * StringUtil.substringAfter(*, null)      = ""
	 * StringUtil.substringAfter("abc", "a")   = "bc"
	 * StringUtil.substringAfter("abcba", "b") = "cba"
	 * StringUtil.substringAfter("abc", "c")   = ""
	 * StringUtil.substringAfter("abc", "d")   = ""
	 * StringUtil.substringAfter("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return "";
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return "";
		}
		return str.substring(pos + separator.length());
	}

	/**
	 * 截取中间字符串
	 * 
	 * <pre>
	 * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
	 * StringUtils.substringBetween(null, *, *)          = null
	 * StringUtils.substringBetween(*, null, *)          = null
	 * StringUtils.substringBetween(*, *, null)          = null
	 * StringUtils.substringBetween("", "", "")          = ""
	 * StringUtils.substringBetween("", "", "]")         = null
	 * StringUtils.substringBetween("", "[", "]")        = null
	 * StringUtils.substringBetween("yabcz", "", "")     = ""
	 * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
	 * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @param open
	 * @param close
	 * @return
	 */
	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * 拼接给定的字符串集合为一个长字符串
	 * 
	 * @param glue
	 *            连接符
	 * @param wrap
	 *            外围包裹字符串
	 * @param coll
	 *            字符串集合
	 * @return
	 */
	public final static String join(String glue, String wrap,
			Collection<String> coll) {
		StringBuilder sb = new StringBuilder();
		if (null == coll || coll.isEmpty())
			return sb.toString();
		boolean hasWrap = isNotEmpty(wrap);
		Iterator<String> it = coll.iterator();
		if (hasWrap) {
			sb.append(wrap).append(it.next()).append(wrap);
			while (it.hasNext())
				sb.append(glue).append(wrap).append(it.next()).append(wrap);

		} else {
			sb.append(it.next());
			while (it.hasNext())
				sb.append(glue).append(it.next());
		}
		return sb.toString();
	}

	/**
	 * 只替换一次relpace
	 * 
	 * <pre>
	 * StringUtil.replaceOnce(null, *, *)        = null
	 * StringUtil.replaceOnce("", *, *)          = ""
	 * StringUtil.replaceOnce("any", null, *)    = "any"
	 * StringUtil.replaceOnce("any", *, null)    = "any"
	 * StringUtil.replaceOnce("any", "", *)      = "any"
	 * StringUtil.replaceOnce("aba", "a", null)  = "aba"
	 * StringUtil.replaceOnce("aba", "a", "")    = "ba"
	 * StringUtil.replaceOnce("aba", "a", "z")   = "zba"
	 * </pre>
	 * 
	 * @param text
	 * @param searchString
	 * @param replacement
	 * @return
	 */
	public static String replaceOnce(String text, String searchString,
			String replacement) {
		return replace(text, searchString, replacement, 1);
	}

	public static String replaceIndex(String text, int index, String replacement) {
		if (isEmpty(text) || replacement == null || index < 1
				|| index > text.length()) {
			return text;
		}

		return text.substring(0, index) + replacement
				+ text.substring(index + 1);
	}

	/**
	 * 
	 * <pre>
	 * StringUtil.replace(null, *, *, *)         = null
	 * StringUtil.replace("", *, *, *)           = ""
	 * StringUtil.replace("any", null, *, *)     = "any"
	 * StringUtil.replace("any", *, null, *)     = "any"
	 * StringUtil.replace("any", "", *, *)       = "any"
	 * StringUtil.replace("any", *, *, 0)        = "any"
	 * StringUtil.replace("abaa", "a", null, -1) = "abaa"
	 * StringUtil.replace("abaa", "a", "", -1)   = "b"
	 * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
	 * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
	 * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
	 * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * @param text
	 * @param searchString
	 * @param replacement
	 * @param max
	 * @return
	 */
	public static String replace(String text, String searchString,
			String replacement, int max) {
		if (isEmpty(text) || isEmpty(searchString) || replacement == null
				|| max == 0) {
			return text;
		}
		int start = 0;
		int end = text.indexOf(searchString, start);
		if (end == -1) {
			return text;
		}
		int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = (increase < 0 ? 0 : increase);
		increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
		StringBuilder buf = new StringBuilder(text.length() + increase);
		while (end != -1) {
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = text.indexOf(searchString, start);
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 重复一段字符串并拼接起来
	 * 
	 * <pre>
	 * StringUtil.repeat(null, 2) = null
	 * StringUtil.repeat("", 0)   = ""
	 * StringUtil.repeat("", 2)   = ""
	 * StringUtil.repeat("a", 3)  = "aaa"
	 * StringUtil.repeat("ab", 2) = "abab"
	 * StringUtil.repeat("a", -2) = ""
	 * </pre>
	 * 
	 * @param str
	 * @param repeat
	 * @return
	 */
	public static String repeat(String source, int count) {
		StringBuilder result = new StringBuilder(count * source.length());
		while (count > 0) {
			result.append(source);
			count--;
		}
		return result.toString();
	}

	/**
	 * 重复一段字符串并拼接起来
	 * 
	 * @param c
	 * @param count
	 * @return
	 */
	public static String repeat(char c, int count) {
		StringBuilder result = new StringBuilder(count);
		while (count > 0) {
			result.append(c);
			count--;
		}
		return result.toString();
	}

	/**
	 * 把一个英文字符串的第一个字符变成大写字母
	 * 
	 * <pre>
	 * StringUtil.capitalize(null)  = null
	 * StringUtil.capitalize("")    = ""
	 * StringUtil.capitalize("cat") = "Cat"
	 * StringUtil.capitalize("cAt") = "CAt"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen)
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 把一个英文字符串的第一个字符变成小写字母
	 * 
	 * <pre>
	 * StringUtil.uncapitalize(null)  = null
	 * StringUtil.uncapitalize("")    = ""
	 * StringUtil.uncapitalize("Cat") = "cat"
	 * StringUtil.uncapitalize("CAT") = "cAT"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen)
				.append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 检查一个字符串指定前缀开始,忽略大小写
	 * 
	 * <pre>
	 * StringUtil.startsWithIgnoreCase(null, null)      = true
	 * StringUtil.startsWithIgnoreCase(null, "abcdef")  = false
	 * StringUtil.startsWithIgnoreCase("abc", null)     = false
	 * StringUtil.startsWithIgnoreCase("abc", "abcdef") = true
	 * StringUtil.startsWithIgnoreCase("abc", "ABCDEF") = true
	 * </pre>
	 * 
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return startsWith(str, prefix, true);
	}

	/**
	 * 检查一个字符串指定前缀开始
	 * 
	 * @param str
	 * @param prefix
	 * @param ignoreCase
	 * @return
	 */
	private static boolean startsWith(String str, String prefix,
			boolean ignoreCase) {
		if (str == null || prefix == null) {
			return (str == null && prefix == null);
		}
		if (prefix.length() > str.length()) {
			return false;
		}
		return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
	}

	/**
	 * 检查一个字符串指定字符串结尾,忽略大小写
	 * 
	 * <pre>
	 * StringUtil.endsWithIgnoreCase(null, null)      = true
	 * StringUtil.endsWithIgnoreCase(null, "abcdef")  = false
	 * StringUtil.endsWithIgnoreCase("def", null)     = false
	 * StringUtil.endsWithIgnoreCase("def", "abcdef") = true
	 * StringUtil.endsWithIgnoreCase("def", "ABCDEF") = false
	 * </pre>
	 * 
	 * @param str
	 * @param suffix
	 * @return
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		return endsWith(str, suffix, true);
	}

	/**
	 * 检查一个字符串指定字符串结尾
	 * 
	 * @param str
	 * @param suffix
	 * @param ignoreCase
	 * @return
	 */
	private static boolean endsWith(String str, String suffix,
			boolean ignoreCase) {
		if (str == null || suffix == null) {
			return (str == null && suffix == null);
		}
		if (suffix.length() > str.length()) {
			return false;
		}
		int strOffset = str.length() - suffix.length();
		return str.regionMatches(ignoreCase, strOffset, suffix, 0,
				suffix.length());
	}

	/**
	 * 把null的字符串转换为"", 否则原样返回
	 * 
	 * @param str
	 */
	public static String nullToEmpty(String str) {
		if (null == str) {
			return "";
		}
		return str;
	}

	/**
	 * 作为分隔方便的方法来返回集合
	 * 
	 * @param coll
	 * @param delim
	 */
	public static String toDelimitedString(Collection<String> coll, String delim) {
		return toDelimitedString(coll, delim, "", "");
	}

	/**
	 * 作为分隔方便的方法来返回集合
	 * 
	 * @param coll
	 * @param delim
	 * @param prefix
	 * @param suffix
	 */
	public static String toDelimitedString(Collection<String> coll,
			String delim, String prefix, String suffix) {
		if (coll == null || coll.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	/**
	 * 删除空字符串
	 * 
	 * <pre>
	 * StringUtil.deleteWhitespace(null)         = null
	 * StringUtil.deleteWhitespace("")           = ""
	 * StringUtil.deleteWhitespace("abc")        = "abc"
	 * StringUtil.deleteWhitespace("   ab  c  ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String deleteWhitespace(String str) {
		if (isEmpty(str)) {
			return str;
		}
		int sz = str.length();
		char[] chs = new char[sz];
		int count = 0;
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				chs[count++] = str.charAt(i);
			}
		}
		if (count == sz) {
			return str;
		}
		return new String(chs, 0, count);
	}

	/**
	 * Collection转成数组
	 * 
	 * @param collection
	 * @return
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * Enumeration转成数组
	 * 
	 * @param enumeration
	 * @return
	 */
	public static String[] toStringArray(Enumeration<String> enumeration) {
		if (enumeration == null) {
			return null;
		}
		List<String> list = Collections.list(enumeration);
		return list.toArray(new String[list.size()]);
	}

	public static String replaceChars(String str, char searchChar,
			char replaceChar) {
		if (str == null) {
			return null;
		}
		return str.replace(searchChar, replaceChar);
	}

	public static String[] split(String str) {
		return split(str, null, -1);
	}

	public static String[] split(String str, char separator) {
		return split(str, separator, false);
	}

	public static String[] split(String str, char separator,
			boolean preserveTokens) {
		// Performance tuned for 2.0 (JDK1.4)

		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		List<String> list = new ArrayList<String>();
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (i < len) {
			if (str.charAt(i) == separator) {
				if (match || preserveTokens) {
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				}
				start = ++i;
				continue;
			}
			lastMatch = false;
			match = true;
			i++;
		}
		if (match || (preserveTokens && lastMatch)) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String[] split(String str, String delimeter, int max) {
		return split(str, delimeter, max, false);
	}

	public static String[] split(String str, String delimeter, int max,
			boolean preserveTokens) {
		// Performance tuned for 2.0 (JDK1.4)
		// Direct code is quicker than StringTokenizer.
		// Also, StringTokenizer uses isSpace() not isWhitespace()

		if (str == null || str.length() == 0) {
			return new String[0];
		}

		int len = str.length();

		List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (delimeter == null) {
			// Null separator means use whitespace
			while (i < len) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match || preserveTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else if (delimeter.length() == 1) {
			// Optimise 1 character case
			char sep = delimeter.charAt(0);
			while (i < len) {
				if (str.charAt(i) == sep) {
					if (match || preserveTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else {
			// standard case
			while (i < len) {
				if (delimeter.indexOf(str.charAt(i)) >= 0) {
					if (match || preserveTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		}
		if (match || (preserveTokens && lastMatch)) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static boolean isAnyEmpty(String... array) {
		for (String str : array) {
			if (isEmpty(str))
				return true;
		}
		return false;
	}

	public static boolean isAlphanumeric(String str, int minInclude,
			int maxInclude) {
		Pattern pattern = Pattern
				.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{" + minInclude
						+ "," + maxInclude + "}$");
		return pattern.matcher(str).matches();
	}

	public static boolean matchPwd(String str) {
		Pattern pattern = Pattern.compile("^[0-9A-Za-z]{6,20}$");
		return pattern.matcher(str).matches();
	}

	private static final Pattern PHONE_PATTERN = Pattern
			.compile("^(1[0-9][0-9])\\d{8}$");

	private static final Pattern PHONE_PATTERN2 = Pattern
			.compile("(1[0-9][0-9])\\d{8}");

	public static String getPhoneNum(String phoneStr) {
		Matcher matcher = PHONE_PATTERN2.matcher(phoneStr);
		if (matcher.find()) {
			return matcher.group(0);
		}
		return null;
	}

	public static boolean isPhone(String phoneStr) {
		return PHONE_PATTERN.matcher(phoneStr).matches();
	}

	public static String encode(String str) {
		if (str == null)
			return null;

		try {
			str = URLEncoder.encode(str, "UTF-8");
			return str;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static int getWordCount(String s) {
		if (s == null)
			return 0;

		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;

		}
		return length;
	}
}
