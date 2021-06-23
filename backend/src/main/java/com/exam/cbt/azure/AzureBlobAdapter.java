package com.exam.cbt.azure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.models.BlobProperties;

@Service
public class AzureBlobAdapter {

    @Autowired
    BlobClientBuilder client;

    
    public List<String> upload(File[] files) {
    	
    	List<String> fileNames = new ArrayList<>();
       for (File file:files) {
    	   if(file != null && file.length() > 0) {
               //implement your own file name logic.
			   String fileName = file.getName();
			  // BlobClient str = client.blobName(fileName).buildClient();
			   //if (!str.exists()) {
				   client.blobName(fileName).buildClient().uploadFromFile(file.getPath(), true);
			   //}
			  
			   fileNames.add(fileName);
           }
    	   
       }
    	
        return fileNames;
    }

    public byte[] getFile(String name) {
        try {
            File temp = new File("/temp/"+name);
            BlobProperties properties = client.blobName(name).buildClient().downloadToFile(temp.getPath());
            byte[] content = Files.readAllBytes(Paths.get(temp.getPath()));
            temp.delete();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteFile(String name) {
        try {
            client.blobName(name).buildClient().delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}