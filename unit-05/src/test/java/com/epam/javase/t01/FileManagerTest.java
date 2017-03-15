package com.epam.javase.t01;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/15/2017.
 */
public class FileManagerTest {

    String path = "src/main/java/com/epam/javase/t01/";
    File start = new File(path);
    FileManager fm;

    @Before
    public void initialization() throws Exception {
        fm = new FileManager(start);
    }

    @Test(expected = IOException.class)
    public void shouldReturnErrorIfStartPathDoesNotExist() throws Exception {
        FileManager manager = new FileManager(new File(""));
    }

    @Test (expected = IOException.class)
    public void shouldThrowErrorIfParentDirectoryDoesntExist() throws Exception {
        FileManager manager = new FileManager(File.listRoots()[0]);
        manager.up();
    }

    @Test
    public void shouldKeepSameParentIfWeOpenFile() throws Exception {
        String current = fm.getCurrentPath();
        fm.open(new File(path +"FileManager.java"));
        assertTrue(current.equals(fm.getCurrentPath()));
    }

    @Test (expected = IOException.class)
    public void shouldThrowErrorIfWeOpenNullOrNonExistFileOrDirectory() throws Exception {
        fm.open(new File(""));
    }

    @Test
    public void checkIfSeeValidDirectoryContent() throws Exception {
        FileManager manager = new FileManager(start);
        manager.up();
        String parent = manager.getCurrentPath();
        File[] current = manager.getDirectoryContent();
        assertTrue(current.length == 3);
        assertTrue(current[0].getAbsolutePath().replace(parent, "").equals("\\t01"));
        assertTrue(current[1].getAbsolutePath().replace(parent, "").equals("\\t02"));
        assertTrue(current[2].getAbsolutePath().replace(parent, "").equals("\\t10"));
    }

    @Test
    public void emptyDirectoryShouldContainsNothing() throws Exception {
        FileManager manager = new FileManager(start);
        manager.up();
        File t10 = manager.getDirectoryContent()[2];
        manager.open(t10);
        assertTrue(manager.getDirectoryContent().length == 0);
    }

    @Test (expected = NotTextFileException.class)
    public void shouldNotCreateNonTxtFiles() throws Exception {
        fm.createNewFile("Test.exe");
    }

    @Test (expected = FileAlreadyExistsException.class)
    public void shouldNotCreateDuplicateFileInOneDirectory() throws Exception {
        fm.createNewFile("Text.txt");
    }

    @Test
    public void canCreateTxtFile() throws Exception {
        String newFile = "Tttt.txt";
        File objFile = new File(start + "\\" + newFile);
        fm.createNewFile(newFile);
        assertTrue(objFile.exists());
        objFile.delete();
    }

    @Test (expected = NotTextFileException.class)
    public void shouldNotDeleteNonTxtFile() throws  Exception {
        String newFile = "Tttt.exe";
        fm.removeFile(newFile);
    }

    @Test (expected = FileNotFoundException.class)
    public void shouldNotDeleteNonExistentFile() throws Exception {
        String newFile = "Tttttttttt.txt";
        fm.removeFile(newFile);
    }

    @Test
    public void shouldAbleToDeleteTxtFile() throws Exception {
        String newFile = "Tttttttttt.txt";
        fm.createNewFile(newFile);
        File objFile = new File(path + "\\" + newFile);
        assertTrue(objFile.exists());
        fm.removeFile(newFile);
        assertFalse(objFile.exists());
    }

    @Test (expected = NotTextFileException.class)
    public void cannotAddDataToNonTxtFile() throws  Exception {
        String newFile = "Tttt.exe";
        fm.addToFile(newFile, newFile);
    }

    @Test
    public void canAppendDataToTXT() throws Exception {


        String newFile = "NEWFILE.txt";
        File objFile = new File(path + "\\" + newFile);
        fm.addToFile(newFile, newFile);

        BufferedReader in = new BufferedReader(new FileReader(objFile));

        String str = "";
        str += in.readLine();

        in.close();
        objFile.delete();

        assertTrue(str.equals(newFile));

    }
}
