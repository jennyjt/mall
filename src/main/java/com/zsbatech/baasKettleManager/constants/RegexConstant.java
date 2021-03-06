package com.zsbatech.baasKettleManager.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexConstant {
	//图片类型
	public static final String pic = "(?i)png|jpg|jpeg|gif|bmp";
	//文档类型
	public static final String doc = "(?i)pdf|docx|doc|xlsx|xls";
	
	public static final List<Pattern> list_release_time = new ArrayList<Pattern>();
	public static final List<Pattern> list_season = new ArrayList<Pattern>();
	public static final List<Pattern> list_episode3 = new ArrayList<Pattern>();
	public static final List<Pattern> list_episode2 = new ArrayList<Pattern>();
	// public static final List<Pattern> list_title = new ArrayList<Pattern>();
	public static final String resource_protocol = "(?i)(^ed2k://)|(^thunder://)|(^magnet:\\?)|(\\.torrent$)|(#torrent$)|(^https?://pan\\.baidu\\.com/.*)";
	public static final String ed2k = "(?i)^ed2k://";
	public static final String thunder = "(?i)^thunder://";
	public static final String magnet = "(?i)^magnet:\\?";
	public static final String torrent = "(?i)(\\.torrent|#torrent)";
	public static final String baiduNet = "(?i)^https?://pan\\.baidu\\.com/.*";
	public static final String pattern_for_charset = "(?i)charset\\s*=\\s*['\"]*([^\\s;'\"]*)";
	public static final String quality = "(?i)HDCAM|HD-CAM|CAM|HDTS|HD-TS|HDTC|HD-TC|DVDSCR|DVDRIP|TVRIP|HDRIP|HDTV|HDDVD|BluRay|BRRip|WEB-DL|WEBRIP|REMUX|R5|TS|TC|SCR|DVD|HD|BD|WEB|蓝光";
	public static final String resolution = "(?i)(720P|1080P|480P|720|1080|480|2160P|2160|1280|普清|高清|超清|清晰|4K)";
	public static final String subtitle = "(?i)中英双字|英语中字|英语中文|国语中字|国英双语|国语配音|国语中文|粤语中字|中文字幕|简体字幕|中英精校|官方中英|中韩字幕|韩语中字|日语中字";
	public static final String subtitle_m_encn = "(?i)chs\\.eng|chs-eng|English.CHS|中英";
	public static final String subtitle_m_cn = "(?i)中字|chs|字幕|翻译|听译|译制";
	public static final String format = "(?i)MP4|MKV|RMVB|AVI";
	public static final String format_end = "(?i)\\.mp4$|\\.rmvb$|\\.mkv$|\\.avi$";
	public static final String size = "(?i)\\d+(\\.\\d+)?(GB|G|MB|M)";
	public static final String chinese = "[\u4E00-\u9FA5]+";
	public static final String cn_number = "^[一二三四五六七八九十]*$";
	public static final String pure_name = "\\d*?[\u4E00-\u9FA5]+[：|\\d|。|，|！]*[\u4E00-\u9FA5|\\d]*";
	public static final String not_word_blank = "[^a-zA-Z| ]";
	public static final String slash = "^/|/$";
	public static final String types = "\\(?(韩版|美版|剧场版|完整版|真人版|加长版)\\)?";
	public static final String getan = "(?<=^哥谭)市";
	public static final String slashSep = "\\s*/\\s*";
	public static final String colonSep = "\\s*[:：]\\s*";
	public static final String blank = "\\s|\\t|\\r|\\n| |　|(&nbsp;)";
	public static final String DYtitle = ".*?导(\\s|\\t|\\r|\\n| |　|(&nbsp;))*?演.*?(?=[\u3040-\u309F\u30A0-\u30FF\u4E00-\u9FA5\uac00-\ud7ffa-zA-Z0-9])";
	public static final String YYtitle = ".*?演(\\s|\\t|\\r|\\n| |　|(&nbsp;))*?员.*?(?=[\u3040-\u309F\u30A0-\u30FF\u4E00-\u9FA5\uac00-\ud7ffa-zA-Z0-9])";
	public static final String ZYtitle = ".*?主(\\s|\\t|\\r|\\n| |　|(&nbsp;))*?演.*?(?=[\u3040-\u309F\u30A0-\u30FF\u4E00-\u9FA5\uac00-\ud7ffa-zA-Z0-9])";

	public static void main(String[] args) {
		String d = "】おっぱいバレー";
		Matcher m = Pattern.compile("[\u3040-\u309F\u30A0-\u30FF\u4E00-\u9FA5\uac00-\ud7ff]+").matcher(d);
		if (m.find()) {
			System.out.println(m.group());
		}
	}

	static {
		list_release_time
				.add(Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(?=\\([\u4E00-\u9FA5|/]*中[\u4E00-\u9FA5|/]*\\))"));// 代码解释：正向（即需要获取的内容在？前边）获取
		list_release_time
				.add(Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(?=\\([\u4E00-\u9FA5|/]*美[\u4E00-\u9FA5|/]*\\))"));
		list_release_time
				.add(Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(?=\\([\u4E00-\u9FA5|/]*韩[\u4E00-\u9FA5|/]*\\))"));
		list_release_time
				.add(Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(?=\\([\u4E00-\u9FA5|/]*日[\u4E00-\u9FA5|/]*\\))"));
		list_release_time.add(Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}"));
		list_release_time.add(Pattern.compile("\\d{4}-\\d{1,2}"));
		list_release_time.add(Pattern.compile("\\d{4}"));

		list_season.add(Pattern.compile("第([一二三四五六七八九十]{1,3}|\\d{1,2})季"));
		list_season.add(Pattern.compile("(?i)S(\\d{1,2})"));
		list_season.add(Pattern.compile("(?i)Season(\\d{1,2})"));

		list_episode3.add(Pattern.compile("[第全新]([一二三四五六七八九十]{1,3}|\\d{1,3}\\-\\d{1,3}|\\d{1,3})[集期]"));
		list_episode3.add(Pattern.compile("(?i)EP(\\d{1,3}\\-\\d{1,3}|\\d{1,3})"));
		list_episode3.add(Pattern.compile("(?i)E(\\d{1,3}\\-\\d{1,3}|\\d{1,3})"));
		list_episode3.add(Pattern.compile("(?i)Episode(\\d{1,3}\\-\\d{1,3}|\\d{1,3})"));
		list_episode3.add(Pattern.compile("(全集)"));
		list_episode3.add(Pattern.compile("(?i)(?<=\\d{1,3}x)(\\d{1,3})")); // 代码解释：反向（即需要获取的内容在？后边）获取
		list_episode3.add(Pattern.compile("(\\d{1,3}\\-\\d{1,3})"));
		list_episode3.add(Pattern.compile("(\\d{1,3})\\.")); // 优先级最低

		list_episode2.add(Pattern.compile("[第全新]([一二三四五六七八九十]{1,2}|\\d{1,2}\\-\\d{1,2}|\\d{1,2})[集期]"));
		list_episode2.add(Pattern.compile("(?i)EP(\\d{1,2}\\-\\d{1,2}|\\d{1,2})"));
		list_episode2.add(Pattern.compile("(?i)E(\\d{1,2}\\-\\d{1,2}|\\d{1,2})"));
		list_episode2.add(Pattern.compile("(?i)Episode(\\d{1,2}\\-\\d{1,2}|\\d{1,2})"));
		list_episode2.add(Pattern.compile("(全集)"));
		list_episode2.add(Pattern.compile("(?i)(?<=\\d{1,2}x)(\\d{1,2})")); // 代码解释：反向（即需要获取的内容在？后边）获取
		list_episode2.add(Pattern.compile("(\\d{1,2}\\-\\d{1,2})"));
		list_episode2.add(Pattern.compile("(\\d{1,2})\\.")); // 优先级最低
		//
		// list_title.add(Pattern.compile("导(\\s|\\t|\\r|\\n| |
		// |(&nbsp;))*?演(\\s*[:：]\\s)?"));
		// list_title.add(Pattern.compile("主(\\s|\\t|\\r|\\n| |
		// |(&nbsp;))*?演(\\s*[:：]\\s)?"));
		// list_title.add(Pattern.compile("演(\\s|\\t|\\r|\\n| |
		// |(&nbsp;))*?员(\\s*[:：]\\s)?"));
	}

	/**
	 * 验证邮箱
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isMail(String str) {
		String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return match(regex, str);
	}

	/**
	 * 验证电话号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isTelephone(String str) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9])|(17[0-9]))\\d{8}$";
		return match(regex, str);
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	public static boolean match(String regex, String str) {
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	* 用户名、密码 长度(6-12位)
	* 
	* @param 待验证的字符串
	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	*/
	public static boolean IsLength(String str) {
		String regex = "^.{6,12}$";
		return match(regex, str);
	}
	
	/**
	* 用户名 格式( 用户名仅支持中英文、数字和下划线，且不能为纯数字,)
	* 
	* @param 待验证的字符串
	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	*/
	public static boolean checkUserNameFormat(String str) {
		 String  regex="^[a-zA-Z0-9_\u4e00-\u9fa5]+$";
		return match(regex, str);
	}
	
	/**
	* 密码 格式( 支持数字，字母，不允许有空格（开头、中间、结尾）)
	* 
	* @param 待验证的字符串
	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	*/
	
	public static boolean checkPwdFormat(String str) {
		String regex="^[A-Za-z0-9\u4e00-\u9fa5]+$";
		return match(regex, str);
	}
	
	
	/**
	* 用户昵称 、三证合一、三证三号、经办人姓名 长度(30个字符)
	* 
	* @param 待验证的字符串
	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	*/
	public static boolean nameLength(String str) {
		String regex = "^.{0,30}$";
		return match(regex, str);
	}
	
	/**
	* 公司营业名称  长度(50个字符)
	* 
	* @param 待验证的字符串
	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	*/
	public static boolean companyNameLength(String str) {
		String regex = "^\\d{1,50}$";
		return match(regex, str);
	}
}
