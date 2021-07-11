package com.exam.cbt.azure;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClientBuilder;
import com.exam.cbt.service.impl.CandidateMasterDataServiceImpl;
import com.exam.cbt.service.impl.ImageUploadStatusServiceImpl;

@Service
public class AzureBlobAdapter {

	@Autowired
	ImageUploadStatusServiceImpl imageUploadStatusServiceImpl;

	@Autowired
	BlobClientBuilder client;

	@Autowired
	CandidateMasterDataServiceImpl candidateMasterDataServiceImpl;

	@Autowired
	ThreadPoolTaskExecutor executorPool;
	
	@Value("${azure.storage.blob-endpoint}")
    String photoUrlPrefix;
	
	@Value("${azure.token}")
    String photoUrlToken;
	
	public void upload(String dir) throws IOException{

		uploadFilesAZCopy(dir);

		List<String> fileNames;
		Map<String,String> finalFiles = new HashMap<String,String>();
		
		
		try {
			fileNames = listFilesUsingFilesList(dir).stream().map(f->photoUrlPrefix.concat("/").concat(f).
					concat("?").concat(photoUrlToken)).filter(file -> (checkIfFileExists(file) == 200))
					.collect(Collectors.toList());
			
			for (String fileName: listFilesUsingFilesList(dir)) {
				finalFiles.put(fileName, photoUrlPrefix.concat("/").concat(fileName).concat("?").concat(photoUrlToken));
				
			}
			finalFiles.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,entry->photoUrlPrefix.concat("/").concat(entry.getValue()).concat("?").concat(photoUrlToken)));
			candidateMasterDataServiceImpl.updatePhotoUrl(finalFiles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private void test(String fileName, File file) {
//
//		client.blobName(fileName).buildClient().uploadFromFile(file.getPath(), true);
//		//if (getFile(fileName) != null) {
//			counter++;
//			System.out.println("Counter : " + counter);
//			System.out.println(Thread.currentThread().getName() + "uploaded " + fileName);
//
//		//}
//		
//		
//	}

//	public void del(File[] files) {
//
//		Arrays.asList(files).parallelStream().forEach(file -> {
//			System.out.println("Deleting File: " + file.getName());
//
//			deleteFile(file.getName());
//		});
//
//		System.out.println("Files are deleted from Azure");
//	}

	public int checkIfFileExists(String fileName){
		fileName="https://stcbt.blob.core.windows.net/images/images12/2.jpg?sv=2020-04-08&st=2021-07-03T14%3A22%3A34Z&se=2050-12-31T14%3A22%3A00Z&sr=c&sp=racwdl&sig=y5kMepXkTzi10Vc2B8wsHxmVUonhtfPWAtdtWF4VK4Q%3D";
		 URL u=null;
		try {
			u = new URL(fileName);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		    HttpURLConnection huc=null;
			try {
				huc = (HttpURLConnection)  u.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    try {
				huc.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    try {
				huc.connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    try {
				return huc.getResponseCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return 0;
	}

//	public boolean deleteFile(String name) {
//		try {
//
//			if (client.blobName(name).buildClient().exists()) {
//				client.blobName(name).buildClient().delete();
//			}
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//	}

	private void uploadFilesAZCopy(String folderPath) {
		String command = "C:\\Users\\admin\\Downloads\\azcopy\\azcopy copy " + folderPath
				+ " https://stcbt.blob.core.windows.net/images?sv=2020-04-08&st=2021-07-03T14%3A22%3A34Z&se=2050-12-31T14%3A22%3A00Z&sr=c&sp=racwdl&sig=y5kMepXkTzi10Vc2B8wsHxmVUonhtfPWAtdtWF4VK4Q%3D --recursive";
		try {
			Process process = Runtime.getRuntime().exec(command);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			String numberOfFileTransfers = null;
			String numberOfTransfersCompleted = null;
			String finalStatus = null;

			while ((line = reader.readLine()) != null) {
				if (line.contains("Number of File Transfers:")) {
					numberOfFileTransfers = line;

				}
				if (line.contains("Number of Transfers Completed:")) {
					numberOfTransfersCompleted = line;
				}
				if (line.contains("Final Job Status:")) {
					finalStatus = line;
				}
				System.out.println(line);
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> listFilesUsingFilesList(String dir) throws IOException {

		return Stream.of(new File(dir).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
				.collect(Collectors.toList());

	}

}