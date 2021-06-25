package com.exam.cbt.azure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.models.BlobProperties;
import com.exam.cbt.entity.ImageUploadStatus;
import com.exam.cbt.service.impl.ImageUploadStatusServiceImpl;

@Service
public class AzureBlobAdapter {

	@Autowired
	ImageUploadStatusServiceImpl imageUploadStatusServiceImpl;

	@Autowired
	BlobClientBuilder client;

	@Autowired
	ThreadPoolTaskExecutor executorPool;

	int counter = 0;

	public List<String> upload(File[] files) {
		
		

		List<String> fileNames = new ArrayList<>();
		List<CompletableFuture<Void>> completableFutures = new LinkedList<>();

//       for (File file:files) {
//    	   if(file != null && file.length() > 0) {
//    		   counter++;
//               //implement your own file name logic.
//			   String fileName = file.getName();
//			  // if (fileName.matches("[0-9]+")) {
//				   client.blobName(fileName).buildClient().uploadFromFile(file.getPath(), true);
//				   System.out.println(fileName + " is uploaded in Azure Cloud.");
//				   System.out.println(counter + " file is uploaded.");
//			  // }
//			 //  else {
//				//  System.out.println("Input File contains alphabets.Skipping this file.");
//			 //  }
//			  // BlobClient str = client.blobName(fileName).buildClient();
//			   //if (!str.exists()) {
//				   
//			   //}
//			 
//			  
//			   fileNames.add(fileName);
//           }
//    	   
//       }
		List<ImageUploadStatus> impList = new ArrayList<>();
		Arrays.asList(files).forEach(file -> {

			if (file != null && file.length() > 0) {
				// counter++;
				// implement your own file name logic.
				String fileName = file.getName();
				// if (fileName.matches("[0-9]+")) {

				// client.blobName(fileName).buildClient().uploadFromFile(file.getPath(), true);
				ImageUploadStatus imp = new ImageUploadStatus();
				imp.setImageName(fileName);

				// System.out.println(fileName + " is uploaded in Azure Cloud.");
				// System.out.println(counter + " file is uploaded.");
				// }
				// else {
				// System.out.println("Input File contains alphabets.Skipping this file.");
				// }
				// BlobClient str = client.blobName(fileName).buildClient();
				// if (!str.exists()) {

				// }

				fileNames.add(fileName);
				impList.add(imp);
//				completableFutures.add(CompletableFuture.runAsync(()->{
//					test(fileName,file);
//				}));

				executorPool.submit(() -> test(fileName, file));

			}

		});

		// CompletableFuture.allOf(completableFutures.toArray(new
		// CompletableFuture[completableFutures.size()])).join();
		// System.out.println("All files are added successfully!");
//		if(impList.size() > 0) {
//			System.out.println("Inserting Records in db : " +impList.size());
//			//imageUploadStatusServiceImpl.saveAllImagesDetails(impList);
//			System.out.println("Inserted Records in db : " +impList.size());
//		}
		
		
		return fileNames;
	}

	private void test(String fileName, File file) {

		client.blobName(fileName).buildClient().uploadFromFile(file.getPath(), true);
		//if (getFile(fileName) != null) {
			counter++;
			System.out.println("Counter : " + counter);
			System.out.println(Thread.currentThread().getName() + "uploaded " + fileName);

		//}
		
		
	}

	public void del(File[] files) {

		Arrays.asList(files).parallelStream().forEach(file -> {
			System.out.println("Deleting File: " + file.getName());

			deleteFile(file.getName());
		});

		System.out.println("Files are deleted from Azure");
	}

	public byte[] getFile(String name) {
		try {
			File temp = new File("https://stcbt.blob.core.windows.net/images/folder1/" + name);
			BlobProperties properties = client.blobName(name).buildClient().downloadToFile(temp.getPath());
			byte[] content = Files.readAllBytes(Paths.get(temp.getPath()));
			//temp.delete();
			return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteFile(String name) {
		try {

			if (client.blobName(name).buildClient().exists()) {
				client.blobName(name).buildClient().delete();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}