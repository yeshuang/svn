package com.shuang.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical,
 * photocopying, recording, or otherwise, without the prior written permission
 * of 3KW.
 *
 * Created By: shuang Created On: 2014年5月9日
 *
 * Amendment History:
 * 
 * Amended By Amended On Amendment Description ------------ -----------
 * ---------------------------------------------
 *
 *
 */
public class CreateSvnPluginConfig {
	public CreateSvnPluginConfig() {
	}

	public void print(String path) {
		List<String> list = getFileList(path);
		if (list == null) {
			return;
		}
		int length = list.size();
		for (int i = 0; i < length; i++) {
			String result = "";
			String thePath = getFormatPath(getString(list.get(i)));
			File file = new File(thePath);
			if (file.isDirectory()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0) {
					print(thePath);
					continue;
				}
				String[] filenames = fileName.split("_");
				String filename1 = filenames[0];
				String filename2 = filenames[1];
				result = filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + "\\,4,false";
				System.out.println(result);
			} else if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0) {
					continue;
				}
				int last = fileName.lastIndexOf("_");
				String filename1 = fileName.substring(0, last);
				String filename2 = fileName.substring(last + 1, fileName.length() - 4);
				result = filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + ",4,false";
				System.out.println(result);
			}
		}
	}

	public List<String> getFileList(String path) {
		path = getFormatPath(path);
		path = path + "/";
		File filePath = new File(path);
		if (!filePath.isDirectory()) {
			return null;
		}
		String[] filelist = filePath.list();
		List<String> filelistFilter = new ArrayList<String>();
		for (int i = 0; i < filelist.length; i++) {
			String tempfilename = getFormatPath(path + filelist[i]);
			filelistFilter.add(tempfilename);
		}
		return filelistFilter;
	}

	public String getString(Object object) {
		if (object == null) {
			return "";
		}
		return String.valueOf(object);
	}

	public String getFormatPath(String path) {
		path = path.replaceAll("\\\\", "/");
		path = path.replaceAll("//", "/");
		return path;
	}

	/**
	 * configuration\org.eclipse.equinox.simpleconfigurator\bundles.info
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		String plugin = "/Users/shuang/ProgramFile/Eclipse.app/Contents/Eclipse/svn";
		new CreateSvnPluginConfig().print(plugin);
	}
}
