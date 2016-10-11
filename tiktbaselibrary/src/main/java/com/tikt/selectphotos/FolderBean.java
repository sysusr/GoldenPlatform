package com.tikt.selectphotos;

/**
 * Created by tikt on 15-12-17.
 */
public class FolderBean {
	//当前文件夹的路径
	private String dir;
	private String firstImgPath;
	private String name;
	private int count;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;

		int lastIndexOf = this.dir.lastIndexOf("/")+1;
		this.name = this.dir.substring(lastIndexOf);
	}

	public String getFirstImgPath() {
		return firstImgPath;
	}

	public void setFirstImgPath(String firstImgPath) {
		this.firstImgPath = firstImgPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
