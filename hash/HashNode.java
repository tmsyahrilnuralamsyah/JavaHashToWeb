import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HashNode {
	public static void main(String[] args) {
		Map<String, ArrayList<String>> map = new Map<>();
        ArrayList<String> all = new ArrayList<>();
    	String strLine = "";
    	try {
            BufferedReader br = new BufferedReader(new FileReader("../file/sda lab.txt"));
            while (strLine != null) {
				strLine = br.readLine();
				if (strLine != null) {
					String[] data = strLine.split("::",2);
					
					// Simpan ke dalam hash
					if (map.get(data[0]) == null) { // Jika data belum ada, simpan ke dalam elemen hash baru
						ArrayList<String> temp =  new ArrayList<>();
						temp.add(data[1]);
						map.add(data[0], temp);
						all.add(data[0]);
					} else { // Jika datanya ada, maka masuk ke dalam elemen hash yang ada
						map.get(data[0]).add(data[1]);
					}
				}
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }

        if(args[0].equals("show")) {
            Collections.sort(all);
			System.out.println("<table class='table table-dark table-bordered'><tr><th scope='col'>No</th><th scope='col'>Editor's Name</th><th scope='col'>Number Of Articles</th></tr>");
            for(int i =0; i<all.size(); i++) {
				ArrayList<String> e = map.get(all.get(i));
				System.out.println("<tr><td>"+(i+1)+"</td><td class='text-left'>"+all.get(i)+"</td><td>"+e.size()+"</td></tr>");
            }
            System.out.println("</table>");
        } else {
			ArrayList<String> result = map.get(args[0]);
			if(result != null) {
                Collections.sort(result);
                System.out.println("<h5 class='text-left mb-3'>Daftar artikel "+args[0]+" </h5>");
                System.out.print("<ul class='list-group text-left mb-5'>");
                result.forEach((e)->System.out.print("<li class='list-group-item list-group-item-dark'>"+e+"</li>"));
                System.out.print("</ul>");
            } else { // Status: 404
                System.out.println("Data For "+args[0]+" Not Found");
            }
        }
	}
}

class Node<K, V> {
	K key;
	V value;
	Node<K, V> next; // Untuk node selanjutnya

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

class Map<K, V> {
	private ArrayList<Node<K, V>> bucketArray;
	private int numBuckets;
	private int size;

	public Map() {
		bucketArray = new ArrayList<>();
		numBuckets = 10;
		size = 0;

		for (int i = 0; i < numBuckets; i++) {
			bucketArray.add(null);
		}
	}

	public int size() { 
		return size; 
	}

	public boolean isEmpty() { 
		return size() == 0; 
	}

	// Implementasi fungsi hash untuk menemukan indeks untuk kunci
	private int getBucketIndex(K key) { 
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
	}

	// Method untuk menghapus kunci
	public V remove(K key) {
		int bucketIndex = getBucketIndex(key); // Untuk cari kunci
		Node<K, V> head = bucketArray.get(bucketIndex); // Untuk dapatkan head
		Node<K, V> prev = null; // Cari kunci dalam rantainya

		while (head != null) {
			if (head.key.equals(key)) {
				break;
			}
			prev = head;
			head = head.next;
		}

		if (head == null) {
			return null;
		}
		size--;

		if (prev != null) {
			prev.next = head.next;
		} else {
			bucketArray.set(bucketIndex, head.next);
		}
		return head.value;
	}

	// Method yang mengembalikan nilai kunci
	public V get(K key) {
		int bucketIndex = getBucketIndex(key); // Untuk cari kunci
		Node<K, V> head = bucketArray.get(bucketIndex); // Untuk dapatkan head

		while (head != null) {
			if (head.key.equals(key)) {
				return head.value;
			}
			head = head.next;
		}
		return null;
	} 

	// Method yang menambahkan kunci ke hash 
	public void add(K key, V value) {
		int bucketIndex = getBucketIndex(key); // Untuk cari kunci
		Node<K, V> head = bucketArray.get(bucketIndex); // Untuk dapatkan head

		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}
		size++;
		head = bucketArray.get(bucketIndex);
		Node<K, V> newNode = new Node<K, V>(key, value);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);

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