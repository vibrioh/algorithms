package plt;

/*
 *  Java Program to Implement VList
 */

import java.util.Scanner;

/*  Class Node  */
class VListNode {
    VListNode next, prev;
    int numElements;
    int array[];

    /* Constructor */
    public VListNode(int n) {
        next = null;
        prev = null;
        numElements = 0;
        array = new int[n];
    }
}

/* Class VList */
class VList {
    private VListNode start;
    private VListNode end;
    private int nodeNumber;
    private int size;

    /* Constructor */
    public VList() {
        start = null;
        end = null;
        nodeNumber = 0;
        size = 0;
    }

    /*  Function to check if list is empty  */
    public boolean isEmpty() {
        return start == null;
    }

    /*  Function to get size of list  */
    public int getSize() {
        return size;
    }

    /* Function to clear list */
    public void makeEmpty() {
        start = null;
        end = null;
        nodeNumber = 0;
        size = 0;
    }

    /* Function to insert element */
    public void insert(int x) {
        size++;
        int n = (int) Math.pow(2, nodeNumber);
        if (start == null) {
            start = new VListNode(n);
            start.array[0] = x;
            start.numElements++;
            end = start;
            return;
        }
        if (end.numElements + 1 <= n) {
            end.array[end.numElements] = x;
            end.numElements++;
        } else {
            nodeNumber++;
            n = (int) Math.pow(2, nodeNumber);
            VListNode nptr = new VListNode(n);
            nptr.array[0] = x;
            nptr.numElements++;
            nptr.prev = end;
            end.next = nptr;
            end = nptr;
        }
    }

    /* Function to get kth element */
    public int kthElement(int k) {
        if (k < 1 || k > size)
            throw new IndexOutOfBoundsException("No element present");
        k--;
        VListNode ptr = start;
        while (k >= ptr.numElements) {
            k -= ptr.numElements;
            ptr = ptr.next;
        }
        return ptr.array[k];
    }

    /* Function to display list */
    public void display() {
        System.out.print("\nVList = ");
        if (size == 0) {
            System.out.print("empty\n");
            return;
        }
        System.out.println();
        VListNode ptr = start;
        int num = 0;
        while (ptr != null) {
            for (int i = 0; i < ptr.numElements; i++)
                System.out.print(ptr.array[i] + " ");
            System.out.println();
            ptr = ptr.next;
            num++;
        }
    }

}

/*  Class VListTest */
class VListTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("VList Test\n");

        /* Creating object of class VList */
        VList vl = new VList();

        char ch;
        /*  Perform list operations */
        do {
            System.out.println("\nVList Operations\n");
            System.out.println("1. insert");
            System.out.println("2. kth element");
            System.out.println("3. check empty");
            System.out.println("4. get size");
            System.out.println("5. clear");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter integer element to insert");
                    vl.insert(scan.nextInt());
                    break;
                case 2:
                    try {
                        System.out.println("Enter postion");
                        System.out.println("\nK-th element = " + vl.kthElement(scan.nextInt()));
                    } catch (Exception e) {
                        System.out.println("\nError : " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Empty status = " + vl.isEmpty());
                    break;
                case 4:
                    System.out.println("Size = " + vl.getSize() + " \n");
                    break;
                case 5:
                    System.out.println("List Cleared\n");
                    vl.makeEmpty();
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            /*  Display List */
            vl.display();

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
