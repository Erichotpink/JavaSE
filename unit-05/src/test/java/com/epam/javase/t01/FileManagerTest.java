package com.epam.javase.t01;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/15/2017.
 */
public class FileManagerTest {

    String path = "src/main/java/com/epam/javase/t01/";
    File start = new File(path);

    @Test(expected = IOException.class)
    public void shouldReturnErrorIfStartPathDoesNotExist() throws Exception {
        FileManager fm = new FileManager(new File(""));
    }

    @Test (expected = IOException.class)
    public void shouldThrowErrorIfParentDirectoryDoesntExist() throws Exception {
        FileManager fm = new FileManager(File.listRoots()[0]);
        fm.up();
    }

    @Test
    public void shouldKeepSameParentIfWeOpenFile() throws Exception {
        FileManager fm = new FileManager(start);
        String current = fm.getCurrentPath();
        fm.open(new File(path +"FileManager.java"));
        assertTrue(current.equals(fm.getCurrentPath()));
    }

    @Test (expected = IOException.class)
    public void shouldThrowErrorIfWeOpenNullOrNonExistFileOrDirectory() throws Exception {
        FileManager fm = new FileManager(start);
        fm.open(new File(""));
    }

    @Test
    public void checkIfSeeValidDirectoryContent() throws Exception {
        FileManager fm = new FileManager(start);
        fm.up();
        String parent = fm.getCurrentPath();
        File[] current = fm.getDirectoryContent();
        assertTrue(current.length == 3);
        assertTrue(current[0].getAbsolutePath().replace(parent, "").equals("\\t01"));
        assertTrue(current[1].getAbsolutePath().replace(parent, "").equals("\\t02"));
        assertTrue(current[2].getAbsolutePath().replace(parent, "").equals("\\t10"));
    }

    @Test
    public void emptyDirectoryShouldContainsNothing() throws Exception {
        FileManager fm = new FileManager(start);
        fm.up();
        File t10 = fm.getDirectoryContent()[2];
        fm.open(t10);
        assertTrue(fm.getDirectoryContent().length == 0);
    }

    @Test (expected = NotTextFileException.class)
    public void shouldNotCreateNonTxtFiles() throws Exception {
        FileManager fm = new FileManager(start);
        fm.createNewFile("Test.exe");
    }

    @Test (expected = FileAlreadyExistsException.class)
    public void shouldNotCreateDuplicateFileInOneDirectory() throws Exception {
        FileManager fm = new FileManager(start);
        fm.createNewFile("Text.txt");
    }

    @Test
    public void canCreateTxtFile() throws Exception {
        FileManager fm = new FileManager(start);
        String newFile = "Tttt.txt";
        File objFile = new File(start + "\\" + newFile);
        fm.createNewFile(newFile);
        assertTrue(objFile.exists());
        objFile.delete();
    }
}
