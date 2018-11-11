package testSomething;

import vn.edu.saigontech.SpringMVCDemo.utils.fileUploadUtils;

public class testString {
	public static void main(String[] args) {
		String target = "jellyfish.jpg";

		System.out.println(fileUploadUtils.formatFileNameToServer(target));

	}

}
