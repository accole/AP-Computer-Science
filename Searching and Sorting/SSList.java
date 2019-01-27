import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * SSList.java
 *
 * Adam Cole 03/09/17
 *
 * This Data Class implements a SSList - a list of Strings with operations on
 * the strings like searching, sorting, ...
 */
public class SSList {
    //-------------------- Private GLOBAL Data -----------------

    private String[] myList;
    private final int WORDSIZE = 5;

    //-------------------- Constructors ------------------------
    public SSList() {
        // Creates an empty list
        myList = new String[0];
    }

    public SSList(int n) {
        // Creates a list of n random words of length WORDSIZE
        // -- Call createword(int) to create the words
        myList = new String[n];
        for (int i = 0; i < n; i++) {
            myList[i] = CreateWord(WORDSIZE);
        }
    }

    //-------------------- Public Methods-----------------------
    public int size() {
        // returns the size of the list
        return myList.length;
    }

    public void add(String s) {
        // Adds an element to the list by increasing the size of the list
        // by one, copying all the elements over, and adding s
        String[] temp = new String[myList.length + 1];
        for (int i = 0; i < myList.length; i++) {
            temp[i] = myList[i];
        }
        temp[myList.length] = s;
        myList = temp;
    }

    public String get(int i) {
        // Returns the string at index i or "" if i is invalid
        if (i >= 0 && i < size()) {
            return myList[i];
        }
        return "";
    }

    @Override
    public String toString() {
        // Returns a string representation of the list in a nice columned
        // chart to be used by System.out.println()
        String display = "";
        for (int i = 0; i < myList.length; i++) {
            display += myList[i] + "  ";
            if ((i + 1) % 5 == 0) {
                display += "\n";
            }
        }
        return display;
    }

// -----------------------------------------------------------------------------
    public int LinearSearch(String s) {
        // Searches for s in the list and returns the index or -1
        // Remember that you cannot use == with strings since they are objects
        // - you need to use .equals() or .compareTo()
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public int BinarySearch(String s) {
        // Searches for s in a Sorted list and returns the index or -1
        // Remember that you cannot use == with strings since they are objects
        // - you need to use .equals() or .compareTo()
        int f = 0;
        int l = size();
        while (f < l) {
            int m = (f + l) / 2;
            int v = myList[m].compareTo(s);
            if (v == 0) {
                return m;
            } else if (v < 0) {
                f = m + 1;
            } else {
                l = m - 1;
            }
        }
        return -1;
    }

// -----------------------------------------------------------------------------
    public void BubbleSort() {
        //Precondition: myList is initialized and not null
        //Postcondition: myList is sorted in Alphabetical order
        // Sorts the list using the Bubble Sort algorithm
        // Remember that you cannot use == with strings since they are objects
        // - you need to use .equals() or .compareTo()
        int last = myList.length - 2;
        boolean swapped = false;
        do {
            swapped = false;
            for (int i = 0; i <= last; i++) {
                if (myList[i].compareTo(myList[i + 1]) > 0) {
                    Swap(i, i + 1);
                    swapped = true;
                }
            }
            last--;
        } while (swapped);
    }

// -----------------------------------------------------------------------------
    public void InsertionSort() {
        //Precondition: myList is initialized and not null
        //Postcondition: myList is sorted in Alphabetical order
        // Sorts the list using the Insertion Sort algorithm
        // Remember that you cannot use == with strings since they are objects
        // - you need to use .equals() or .compareTo()
        //
        // This should call the private insert() method below
        for (int i = 1; i < myList.length; i++) {
            Insert(myList[i], i - 1);
        }
    }

    private void Insert(String w, int last) {
        // This will insert the string w into myList in the already sorted
        // top of the list from 0 to last. There should not be a call to swap
        // Used by InsertionSort()
        while (last >= 0 && (myList[last].compareTo(w) > 0)) {
            myList[last + 1] = myList[last];
            last--;
        }
        myList[last + 1] = w;
    }

// -----------------------------------------------------------------------------
    public void SelectionSort() {
        //Precondition: myList is initialized and not null
        //Postcondition: myList is sorted in Alphabetical order
        // Sorts the list using the Selection Sort algorithm
        // Remember that you cannot use == with strings since they are objects
        // - you need to use .equals() or .compareTo()
        //
        // This should call the private Smallest method below
        for (int i = 0; i < myList.length; i++) {
            int small = Smallest(i);
            Swap(i, small);
        }
    }

    private int Smallest(int start) {
        // find the index of the smallest value in myList between start and the
        // end of myList. Used by SelectionSort()
        int small = start;
        for (int i = (start + 1); i < myList.length; i++) {
            if (myList[i].compareTo(myList[small]) < 0) {
                small = i;
            }
        }
        return small;
    }

// -----------------------------------------------------------------------------
    public void QuickSort() {
        //PreCondition: myList is not null
        //PostCondition: myList is sorted
        if (myList.length > 1) {
            QSort(0, myList.length - 1);
        }
    }

    private void QSort(int start, int end) {
        int low = start;
        int high = end;
        //middle value to base the sort off of
        String pivot = myList[(start + end) / 2];
        //move elements to the left of the pivot if smaller and
        //    to the right if bigger
        do {
            while (myList[low].compareTo(pivot) < 0) {
                low++;
            }
            while (myList[high].compareTo(pivot) > 0) {
                high--;
            }
            if (low <= high) {
                Swap(low, high);
                low++;
                high--;
            }
        } while (low <= high);
        //if there is more than one value in each sub list created,
        //    recursively call QSort
        if (start < high) {
            QSort(start, high);
        }
        if (end > low) {
            QSort(low, end);
        }
    }

// -----------------------------------------------------------------------------
    public void MergeSort() {
        //Precondition: myList is initialized and not null
        //Postcondition: myList is sorted in Alphabetical order
        MSort(0, myList.length - 1);
    }

    public void MSort(int s, int e) {
        //if there is more than one item in the list, sort the first half, sort
        //      the second half, and merge them together.
        if (s < e) {
            int m = (s + e) / 2;
            MSort(s, m);
            MSort(m + 1, e);
            Merge(s, m, m + 1, e);
        }
    }

    private void Merge(int s1, int e1, int s2, int e2) {
        // Precondition: 0 <= s1 <= e1 <= s2 <= e2 < myList.size and e1 = s2 - 1
        //                the elements from [s1,e1] and [s2, e2] are sorted
        // Postcondition: myList[s1] to myList[e2] is sorted

        //create a temporary list
        String[] temp = new String[e2 - s1 + 1];
        //remember the beginning index where sorted and an index for the
        //      temporary array
        int start = s1;
        int i = 0;
        //go through each of the first elements and merge the two together into
        //      the temporary array
        while (s1 <= e1 && s2 <= e2) {
            if (myList[s1].compareTo(myList[s2]) < 0) {
                temp[i] = myList[s1];
                i++;
                s1++;
            } else {
                temp[i] = myList[s2];
                i++;
                s2++;
            }
        }
        //add the remaining elements to the temporary list as one of the lists
        //         has no elements left
        for (; s1 <= e1; s1++) {
            temp[i] = myList[s1];
            i++;
        }
        for (; s2 <= e2; s2++) {
            temp[i] = myList[s2];
            i++;
        }
        //copy the temporary array back into myList at the correct indexes
        for (int j = 0; j < temp.length; j++) {
            myList[start + j] = temp[j];
        }
    }

// -----------------------------------------------------------------------------
    public boolean WritetoDisk(String filename) {
        // creates a text file named filename and writes the words
        // in myList to the text file one per line
        try {
            FileWriter out = new FileWriter(filename);
            BufferedWriter buffer = new BufferedWriter(out);

            //read the words in one per line
            for (int i = 0; i < size(); i++) {
                buffer.write(get(i));
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException ex) {
            //an exception was found trying to create the file or write it
            return false;
        }
        return true;
    }

    public boolean ReadfromDisk(String filename) {
        // opens a text file named filename and reads the words one per line
        // and adds them to an empty list (use add())
        SSList tmp = new SSList();
        try {
            FileReader in = new FileReader(filename);

            //read the words in one per line
            BufferedReader buffer = new BufferedReader(in);
            String word = buffer.readLine();
            while (word != null) {
                //read in a word - one per line - and return if an error occurs
                tmp.add(word);
                word = buffer.readLine();
            }
            buffer.close();
        } catch (IOException ex) {
            //error in opening or reading the file
            return false;
        }
        myList = tmp.myList;
        return true;
    }

    //-------------------- Private Utility Methods------------------------------
    private void Swap(int a, int b) {
        // swaps the ath and bth values in myList
        String temp = myList[a];
        myList[a] = myList[b];
        myList[b] = temp;
    }

    private String CreateWord(int len) {
        // Creates a Random Capitalized Word of length len
        String tmp = "";
        for (int i = 0; i < len; i++) {
            tmp += (char) ('A' + (int) (Math.random() * 26));
        }
        return tmp;
    }

// -----------------------------------------------------------------------------
    public void BucketSort() {
        //Precondition: myList is initialized and not null
        //Postcondition: myList is sorted in Alphabetical order

        //create an arraylist with 26 elements for each letter of alphabet
        ArrayList[] buckets = new ArrayList[26];
        //create an arraylist for each letter in buckets
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList(myList.length / 13);
        }
        //add each word to the arraylist correponding to the last letter in
        //      the word

        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < myList.length; j++) {
                buckets[(int) (myList[j].charAt(i) - 'A')].add(myList[j]);
            }
            int index = 0;
            //copy the words in buckets back to myList in order, then clear the
            //      bucket
            for (int k = 0; k < buckets.length; k++) {
                if (buckets[k].size() > 0) {
                    for (int l = 0; l < buckets[k].size(); l++) {
                        myList[index] = (String) buckets[k].get(l);
                        index++;
                    }
                }
                buckets[k].clear();
            }
        }
    }
}
