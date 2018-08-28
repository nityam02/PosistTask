import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Scanner;
import javax.crypto.*;
import javax.xml.bind.DatatypeConverter;

// THE First Step of checking to confirm the root is always grether than the data value then we have to use maxheapfiy 




// code for creating the noddes and checking that heap is always the greatest

 class Record {
    Record genesisNode;
    private String password;

    private int[] Heap;
    private int size;
    private int maxsize;

    private static final int FRONT = 1;

    SecretKey secKey;
    byte[] cipherText;
    String decryptedText;
    private Node Node;


    public Record() throws Exception {
        if (genesisNode == null) {
         // the value of password has to insereed 
            System.out.println("Please create the password");
            Scanner input = new Scanner(System.in);
            String plainText = input.nextLine();
            password = plainText;
            secKey = getSecretEncryptionKey();
            cipherText = encryptText(plainText, secKey);
            decryptedText = decryptText(cipherText, secKey);

        } else {
            while (true) {
                // decryption is invloving the reverse process of code 
                System.out.println("Please enter the password");
                Scanner input = new Scanner(System.in);
                String plainText = input.nextLine();
                if (plainText.equals(decryptedText)) ;
                break;
            }

        }
    }
    // An object of Res is passed around so that the
// same value can be used by multiple recursive calls.


    private static class Node {
        private Date date;
        private Node rightchild;
        private Node leftchild;
        private int nodenumber;
        private String nodeid;
        private Data data;// here i used the concept of aggregation
        private int hashdata;

        public Node getRightchild() {
            return rightchild;
        }

        public void setRightchild(Node rightchild) {
            this.rightchild = rightchild;
        }

        public Node getLeftchildnode() {
            return leftchild;
        }

        public void setLeftchildnode(Node leftchild) {
            this.leftchild = leftchild;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getNodenumber() {
            return nodenumber;
        }

        public void setNodenumber(int nodenumber) {
            this.nodenumber = nodenumber;
        }

        public String getNodeid() {
            return nodeid;
        }

        public void setNodeid(String nodeid) {
            this.nodeid = nodeid;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public int getHashdata() {
            return hashdata;
        }

        public void setHashdata(int hashdata) {
            this.hashdata = hashdata;
        }
    }
// data consiste  of owner owner name hash set
    private class Data {
        private String ownerid;
        private float value;
        private String ownername;
        private String hash;

        public String getOwnerid() {
            return ownerid;
        }

        public void setOwnerid(String ownerid) {
            this.ownerid = ownerid;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public String getOwnername() {
            return ownername;
        }

        public void setOwnername(String ownername) {
            this.ownername = ownername;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }


    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos) {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (Heap[pos] < Heap[leftChild(pos)] || Heap[pos] < Heap[rightChild(pos)]) {
                if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    private void insert(int element) {
        Heap[++size] = element;
        int current = size;

        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }


    private void maxHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            maxHeapify(pos);
        }
    }

    private int remove() {
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        maxHeapify(FRONT);
        return popped;
    }
   
    // the node for merging not workingh right now
    
  /*  public Node mergeTwoLists(Node l1, Node l2) {
    Node head = new Node(0);
    Node p=head;
 
    Node p1=l1;
    Node p2=l2;
    while(p1!=null && p2!=null){
        if(p1.val < p2.val){
            p.next = p1;
            p1 = p1.next;
        }else{
            p.next = p2;
            p2 = p2.next;
        }
        p=p.next;
    }
 
    if(p1!=null){
        p.next = p1;
    }
 
    if(p2!=null){
        p.next = p2;
    }
 
    return head.next;
}*/
    int findMaxUtil(Node node, Res res)
    {
 
        // Base Case
        if (node == null)
            return 0;
 
        // l and r store maximum path sum going through left and
        // right child of root respectively
        int l = findMaxUtil(node.leftchild, res);
        int r = findMaxUtil(node.rightchild, res);
 
       int max_single = 0;
        
 
        return max_single;
 
    }
    private static SecretKey getSecretEncryptionKey() throws Exception {

        KeyGenerator generator = KeyGenerator.getInstance("AES");

        generator.init(128); 

        SecretKey secKey = generator.generateKey();

        return secKey;

    }

    private static byte[] encryptText(String plainText, SecretKey secKey) throws Exception {

      

        Cipher aesCipher = Cipher.getInstance("AES");

        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);

        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());

        return byteCipherText;

    }

    private static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {

       

        Cipher aesCipher = Cipher.getInstance("AES");

        aesCipher.init(Cipher.DECRYPT_MODE, secKey);

        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);

        return new String(bytePlainText);

    }

    private static String bytesToHex(byte[] hash) {

        return DatatypeConverter.printHexBinary(hash);
    }


    public void addUserData(String ownerID,float value, String ownername) {
        Data data=new Data();
        data.setOwnerid(ownerID);
        data.setValue(value);
        data.setOwnername(ownername);
        if(genesisNode==null)
        {
           Node node=new Node();
           node.data=data;
           node.date=new Date();
           node.leftchild=null;
           node.rightchild=null;
           node.nodenumber++;
           node.nodeid=String.valueOf(50);
           node.hashdata=this.hashCode();
        }
      
    }
}