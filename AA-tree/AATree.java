import java.util.*;
import java.io.*;

class AANode {
    AANode left, right;
    int element, level;

    AANode(int theElement) {
        this(theElement, null, null);
    }

    AANode(int theElement, AANode lt, AANode rt) {
        left = lt;
        right = rt;
        element = theElement;
        level = 1;
    }
}

public class AATree {
    private AANode root;
    public int iterations;

    public AATree() {
        root = null;
        iterations = 0;
    }

    public void insert(int x) {
        root = insert(x, root);
    }

    private AANode insert(int x, AANode t) {
        iterations++;
        if (t == null)
            return new AANode(x, null, null);

        if (x < t.element)
            t.left = insert(x, t.left);
        else if (x > t.element)
            t.right = insert(x, t.right);
        else
            return t;

        t = skew(t);
        t = split(t);
        return t;
    }

    private AANode skew(AANode t) {
        iterations++;
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        else if (t.left.level == t.level) {
            AANode lt = t.left;
            t.left = lt.right;
            lt.right = t;
            return lt;
        } else
            return t;
    }

    private AANode split(AANode t) {
        iterations++;
        if (t == null)
            return null;
        else if (t.right == null || t.right.right == null)
            return t;
        else if (t.level == t.right.right.level) {
            AANode rt = t.right;
            t.right = rt.left;
            rt.left = t;
            rt.level = t.level + 1;
            return rt;
        } else
            return t;
    }

    public boolean contains(int x) {
        return contains(x, root);
    }

    private boolean contains(int x, AANode t) {
        iterations++;
        if (t == null) {
            iterations++;
            return false;
        }
        else if (x < t.element) {
            iterations++;
            return contains(x, t.left);
        }
        else if (x > t.element) {
            iterations++;
            return contains(x, t.right);
        }
        else {
            iterations++;
            return true;
        }
    }

    public void remove(int x) {

        root = remove(x, root);
    }

    private AANode remove(int x, AANode t) {
        iterations++;
        if (t == null) {

            return null;
        }

        if (x < t.element) {

            t.left = remove(x, t.left);
        }
        else if (x > t.element) {

            t.right = remove(x, t.right);
        }
        else if (t.left != null && t.right != null) {

            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {

            t = (t.left != null) ? t.left : t.right;
        }
        if (t != null) {

            t = skew(t);
            t.right = skew(t.right);
            if (t.right != null)
                t.right.right = skew(t.right.right);
            t = split(t);
            t.right = split(t.right);
        }

        return t;
    }

    private AANode findMin(AANode t) {
        iterations++;
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        else
            return findMin(t.left);
    }

    public int getIterations() {
        return iterations;
    }


    public void resetIterations() {
        iterations = 0;
    }
}
