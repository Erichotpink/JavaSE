package javase01.t06.notepad;

/**
 * The auxiliary class to represent an entry in the notepad.
 * The class declares two public methods - toString and equals.
 *
 * Created by Andrey Ivanov on 2/13/2017.
 */

public class Entry {

    private final String str;

    public Entry(String str) {
        this.str = str;
    }

    /**
     * Returns a string representation of the entry.
     * @return a string representation of the entry
     */

    @Override
    public String toString() {
        return str;
    }


    /**
     * Compares the specified object with this object.
     * @param other the reference to the object to be compared with this object
     * @return true if the specfied object is equal to this object
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) { return false;}

        if (this == other) { return true;}

        if (this.getClass() != other.getClass()) { return false;}

        Entry obj = (Entry) other;

        return this.str.equals(obj.str);
    }
}
