package com.ucar.qtc.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: cong.zhou01
 * @description: 访问ip工具类
 * @date: 2018/8/7 10:19
 */
public class IPUtils {
	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

	/**
	 * 获取客户端IP地址
	 * @param request 请求
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
			InetAddress inet = null;
			try {
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				logger.error("api-admin IPUtils ERROR ", e);
			}
			ip = inet.getHostAddress();
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}

}
