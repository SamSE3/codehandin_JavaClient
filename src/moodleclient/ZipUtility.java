/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author SuperNova
 */
public class ZipUtility {

    public static boolean includeBaseFolder = false;

    /**
     * zips up a folder (includes the folder itself) and saves it in a user
     * defined location
     *
     * @param folderToZip the path to the file or folder on the system to zip
     * @param folderToPutZipIn the root path to save the zip in temporarily.
     * @param zipName the name of the zip file
     * @return the zipped up folder
     */
    public static File zipFileOrFolder(String folderToZip, String folderToPutZipIn, String zipName) {
        ArrayList<String> fileNameList = new ArrayList<>();
        ArrayList<String> filePathNameList = new ArrayList<>();
        String zipFilePath = folderToPutZipIn + zipName + ".zip";
        System.out.println("zipFilePath " + zipFilePath);
        System.out.println("folderToPutZipIn " + folderToPutZipIn);
        System.out.println("folderToZip " + folderToZip);
        File initialNode = new File(folderToZip);
        if (initialNode.isFile()) {
            fileNameList.add(initialNode.getName());
            filePathNameList.add(initialNode.getPath());
        } else { //if (node.isDirectory())
            String[] subNote = initialNode.list();
            //System.out.println("initialNode.getName() " + initialNode.getName());
            String parentPath = includeBaseFolder ? initialNode.getName() : "";
            for (String filename : subNote) {
                generateFileList(fileNameList, filePathNameList, parentPath, new File(initialNode.getPath(), filename));//start with an empty subpath
            }
        }
        System.out.println("file list " + fileNameList);
        //ZipFile aZF = new ZipFile(zipPath);        
        return zipIt(fileNameList, filePathNameList, zipName);
    }

    /**
     * recursively lists files adding them to the file list, if a file is
     * supplied, only that file will be added to the list of files
     *
     * @param fileNameList a list of the file paths under the zipped directory
     * @param filePathNameList a list of the full file paths
     * @param parentPath the path of the parent folder under the zipped
     * directory
     * @param node the folder to zip
     */
    private static void generateFileList(ArrayList<String> fileNameList, ArrayList<String> filePathNameList, String parentPath, File node) {
        // the path of the parent folder under the zip (if folder) or the path of the file under the zip
        parentPath += node.getName();
        if (node.isFile()) {
            //System.out.println("parentPath " + parentPath + " npath " + node.getPath());
            fileNameList.add(parentPath);
            filePathNameList.add(node.getPath());
        } else { //if (node.isDirectory())
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(fileNameList, filePathNameList, parentPath + File.separator, new File(node, filename));
            }
        }
    }

    /**
     * Zips the files specified by the fileNameList/filePathNameList lists
     *
     * @param fileList a list of the file paths under the zipped directory
     * @param filePathNameList a list of the full file paths
     * @param zipFilePath
     * @return
     */
    public static File zipIt(ArrayList<String> fileList, ArrayList<String> filePathNameList, String zipFilePath) {
        File zipFile = new File(zipFilePath);
        if (zipFile.exists()) {
            zipFile.delete();
            try {
                zipFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ZipUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            zipFile.getParentFile().mkdirs();
        }

        byte[] buffer = new byte[1024];
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);
            FileInputStream in;
            for (int i = 0; i < fileList.size(); i++) {
                //System.out.println("File Added : " + fileList.get(i));
                ZipEntry ze = new ZipEntry(fileList.get(i));
                zos.putNextEntry(ze);
                in = new FileInputStream(filePathNameList.get(i));
                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
            }
            zos.closeEntry();
            System.out.println("Folder successfully compressed");
        } catch (IOException ex) {
            Logger.getLogger(MoodleClient.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MoodleClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zipFile;
    }

    /**
     * unzip a zip file into specified output folder
     *
     * @param zipFile the zip file to unzip/decompress
     * @param outputFolder the folder to place the extracted files into
     * @return
     */
    public static boolean unZipIt(File zipFile, String outputFolder) {

        byte[] buffer = new byte[1024];

        //create output directory is not exists
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }

        //get the zipped file list entry
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) { //get the zip file content
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("file unzip : " + newFile.getAbsoluteFile());

                    //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ZipUtility.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ZipUtility.class.getName()).log(Level.SEVERE, null, ex);
                }
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
        } catch (IOException ex) {            
            Logger.getLogger(ZipUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Done");

        return true;
    }

}
