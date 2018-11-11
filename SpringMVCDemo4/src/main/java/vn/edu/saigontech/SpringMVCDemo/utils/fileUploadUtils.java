package vn.edu.saigontech.SpringMVCDemo.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class fileUploadUtils {
	public static void saveUploadedFiles(List<MultipartFile> files, String saveLocationPath) throws IOException {


		File saveLocation = new File(saveLocationPath);

		if (saveLocation.exists() == false)
			saveLocation.mkdirs();

		File[] fileExist = saveLocation.listFiles();

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; 
			}
			String finalFileName = file.getOriginalFilename();

			for (File file1 : fileExist) {
				if (file1.getName().contains(getNameBe4Point(file.getOriginalFilename()))) {

					finalFileName = fileUploadUtils.formatFileNameToServer(file1.getName());
				}
			}

			byte[] bytes = file.getBytes();

			Path path = Paths.get(saveLocationPath + File.separator + finalFileName);
			Files.write(path, bytes);

		}

	}

	public static String saveUploadedFile(MultipartFile targetFile, String saveLocationPath) throws IOException {


		File saveLocation = new File(saveLocationPath);

		if (saveLocation.exists() == false)
			saveLocation.mkdirs();

		File[] fileExist = saveLocation.listFiles();

		if (targetFile.isEmpty()) {
			return "";
		}
		String finalFileName = targetFile.getOriginalFilename();

		for (File file1 : fileExist) {
			if (file1.getName().contains(getNameBe4Point(targetFile.getOriginalFilename()))) {

				finalFileName = fileUploadUtils.formatFileNameToServer(file1.getName());
			}
		}

		byte[] bytes = targetFile.getBytes();

		Path path = Paths.get(saveLocationPath + File.separator + finalFileName);
		Files.write(path, bytes);
		return finalFileName;

	}
	
	public static boolean deleteUploadFile(String fileName, String saveLocationPath) {
		File saveLocation = new File(saveLocationPath);
		
		if (saveLocation.exists() == false) 
			return false;
		
		File[] filesInsideThisLocation = saveLocation.listFiles();
		
		for (File file: filesInsideThisLocation) {
			if (file.getName().equals(fileName)) 
				return file.delete();
		}
		
		return false;
		
	}

	public static String formatFileNameToServer(String target) {

		int indexOfPoint = target.lastIndexOf(".");
		String nameBe4Point = target.substring(0, indexOfPoint);
		String nameAfterPoint = target.substring(indexOfPoint);

		int indexOfSpace = target.lastIndexOf("_");

		String result = "";

		if (indexOfSpace != -1) {
			try {
				int duplicateTime = Integer.parseInt(target.substring(indexOfSpace + 1, indexOfPoint));
				result = target.substring(0, indexOfSpace) + "_" + (duplicateTime + 1) + target.substring(indexOfPoint);
			} catch (Exception e) {
				result = nameBe4Point + "_1" + nameAfterPoint;
			}
		} else {
			result = nameBe4Point + "_1" + nameAfterPoint;
		}

		return result;
	}

	public static String getNameBe4Point(String target) {
		int indexOfPoint = target.lastIndexOf(".");
		return target.substring(0, indexOfPoint);
	}

}
