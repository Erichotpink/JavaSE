package com.epam.javase.t01;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

/**
 * Represents a simple implementation of file manager.
 *
 * Created by aivanov on 3/15/2017.
 */
public class FileManager {

    private File file;

    public FileManager(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("No such file or directory");
        }
        this.file = file;
    }

    /**
     * Open parent directory.
     *
     * @throws IOException if the parent directory doesn't exist
     */
    public void up() throws IOException {
        if(file.getParentFile() == null) {
            throw new IOException("No such file or directory.");
        }

        file = file.getParentFile();
    }


    public String getCurrentPath() {
        return file.getAbsolutePath();
    }

    public File[] getDirectoryContent() {
        return file.listFiles();
    }

    /**
     * Open selected file or directory.
     *
     * @param file file or directory to be opened
     * @throws IOException if the path doesn't exist
     */
    public void open(File file) throws IOException {
        if(!file.exists()) {
            throw new IOException("No such file or directory.");
        }

        if (file.isFile()) {
            System.out.println("File opened: " + file.getCanonicalPath());
        } else {
            this.file = file;
        }
     }

    /**
     * Create a new file in the current directory.
     *
     * @param name the file name
     * @throws NotTextFileException if you try to create a file with extension other then TXT
     * @throws IOException if the file already exist or due to some I/O issues
     */
    public void createNewFile(String name) throws NotTextFileException, IOException {
        if (!name.endsWith(".txt")) {
            throw new NotTextFileException("You can modify only TXT files");
        }

        File newFile = new File(this.getCurrentPath() + "\\" + name);

        if (newFile.exists()) {
            throw new FileAlreadyExistsException("The file already exists.");
        }

        newFile.createNewFile();
    }

    /**
     * Remove the specified file.
     *
     * @param name the file to be removed
     * @throws NotTextFileException if you try to remove non txt file
     * @throws FileNotFoundException if the file doesn't exist
     */
    public void removeFile(String name) throws NotTextFileException, FileNotFoundException {
        if (!name.endsWith(".txt")) {
            throw new NotTextFileException("You can modify only TXT files");
        }

        File newFile = new File(this.getCurrentPath() + "\\" + name);

        if (!newFile.exists()) {
            throw new FileNotFoundException("No such a file.");
        }

        newFile.delete();
    }

    /**
     * Append the data to the file.
     *
     * @param name the file to be modified
     * @param in input stream which content to be added
     * @throws NotTextFileException if the file isn't non-txt
     * @throws IOException will be thrown due to I/O issues
     */
    public void addToFile(String name, Reader in) throws NotTextFileException, IOException {
        if (!name.endsWith(".txt")) {
            throw new NotTextFileException("You can modify only TXT files");
        }

        File newFile = new File(this.getCurrentPath() + "\\" + name);

        if (!newFile.exists()) {
            newFile.createNewFile();
        }

        try(BufferedWriter out = new BufferedWriter(new FileWriter(newFile, true));
            BufferedReader reader = new BufferedReader(in)) {

            char[] buffer = new char[1024];
            while(reader.read(buffer) != -1) {
                out.write(buffer);
            }
        }
    }
}
