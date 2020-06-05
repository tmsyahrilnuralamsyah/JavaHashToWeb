import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HashNode {
	public static void main(String[] args) {
		Map<String, ArrayList<String>> map = new Map<>();
        ArrayList<String> all = new ArrayList<>();
    	String strLine = "";
        // Open the File which contains data
    	try {
            BufferedReader br = new BufferedReader(new FileReader("../file/sda lab.txt"));
            while (strLine != null) {
                //Read data line by line 
				strLine = br.readLine();
				if (strLine != null) {
					String[] data = strLine.split("::",2);
					
					// Slice data and store into hash map
					if (map.get(data[0]) == null) { //if data doesn't exist yet, store into new hash element
						ArrayList<String> temp =  new ArrayList<>();
						temp.add(data[1]);
						map.add(data[0], temp);
						all.add(data[0]);
					} else { //if data exist, append the existing hash element
						map.get(data[0]).add(data[1]);
					}
				}
            }
            br.close();
        // Handling the exception
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }

        // if argument is 'show',  print all value in HTML table format
        if(args[0].equals("show")) {
            Collections.sort(all);
            System.out.println("<table class='table'>");
            System.out.println("<tr><th>No</th>");
            System.out.println("<th>Nama Editor </th>");
            System.out.println("<th>Jumlah Artikel </th></tr>");
            
            for(int i =0; i<all.size(); i++) {
                ArrayList<String> e = map.get(all.get(i));
                System.out.println("<tr><td>"+(i+1)+"</td>");
                System.out.println("<td>"+all.get(i)+"</td>");
                System.out.println("<td>"+e.size()+"</td></tr>");
            }
            System.out.println("</table>");
        
        //if argument was not 'show', so that was an editor name then get its data from hash
        // and print into HTML format
        } else {
			ArrayList<String> result = map.get(args[0]);
			if(result != null) { //if data found print it
                Collections.sort(result);
                System.out.println("<h5 class='mb-3'>Daftar artikel dengan editor "+args[0]+" </h5>");
                System.out.print("<ul class='list-group'>");
                result.forEach((e)->System.out.print("<li class='list-group-item list-group-item-action'>"+e+"</li>"));
                System.out.print("</ul>");
            } else { // status: 404
                System.out.println("Data For "+args[0]+" Not Found");
            }
        }
	}
}

class Node<K, V> {
	K key;
	V value;

	// Reference to next node 
	Node<K, V> next;

	// Constructor 
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

class Map<K, V> {
	// bucketArray is used to store array of chains 
	private ArrayList<Node<K, V>> bucketArray;

	// Current capacity of array list 
	private int numBuckets;

	// Current size of array list 
	private int size;

	// Constructor (Initializes capacity, size and 
	// empty chains. 
	public Map() {
		bucketArray = new ArrayList<>();
		numBuckets = 10;
		size = 0;

		// Create empty chains 
		for (int i = 0; i < numBuckets; i++) {
			bucketArray.add(null);
		}
	}

	public int size() { return size; }
	public boolean isEmpty() { return size() == 0; }

	// This implements hash function to find index 
	// for a key 
	private int getBucketIndex(K key) { 
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
	}

	// Method to remove a given key 
	public V remove(K key) {
		// Apply hash function to find index for given key 
		int bucketIndex = getBucketIndex(key);

		// Get head of chain 
		Node<K, V> head = bucketArray.get(bucketIndex);

		// Search for key in its chain 
		Node<K, V> prev = null;
		while (head != null) {
			// If Key found 
			if (head.key.equals(key)) {
				break;
			}

			// Else keep moving in chain 
			prev = head;
			head = head.next;
		}

		// If key was not there 
		if (head == null) {
			return null;
		}

		// Reduce size 
		size--;

		// Remove key 
		if (prev != null) {
			prev.next = head.next;
		} else {
			bucketArray.set(bucketIndex, head.next);
		}
		return head.value;
	}

	// Returns value for a key 
	public V get(K key) {
		// Find head of chain for given key 
		int bucketIndex = getBucketIndex(key);
		Node<K, V> head = bucketArray.get(bucketIndex);

		// Search key in chain 
		while (head != null) {
			if (head.key.equals(key)) {
				return head.value;
			}
			head = head.next;
		} 

		// If key not found 
		return null;
	} 

	// Adds a key value pair to hash 
	public void add(K key, V value) {
		// Find head of chain for given key 
		int bucketIndex = getBucketIndex(key);
		Node<K, V> head = bucketArray.get(bucketIndex);

		// Check if key is already present 
		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		// Insert key in chain 
		size++;
		head = bucketArray.get(bucketIndex);
		Node<K, V> newNode = new Node<K, V>(key, value);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);

		// If load factor goes beyond threshold, then 
		// double  table size 
		if ((1.0*size)/numBuckets >= 0.7) {
			ArrayList<Node<K, V>> temp = bucketArray;
			bucketArray = new ArrayList<>();
			numBuckets = 2 * numBuckets;
			size = 0;
			for (int i = 0; i < numBuckets; i++) {
				bucketArray.add(null);
			}

			for (Node<K, V> headNode : temp) {
				while (headNode != null) {
					add(headNode.key, headNode.value);
					headNode = headNode.next;
				}
			}
		}
	}
}