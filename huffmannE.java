package EncodingDecoding;

import java.util.HashMap;
import java.util.Set;

import Heaps.heapp;

public class huffmannE {

	private class Node implements Comparable<Node> {
		char data;
		int frequency;
		Node left;
		Node right;

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return o.frequency - this.frequency;
		}

	}

	private HashMap<Character, String> encoder = new HashMap<>();
	private HashMap<String, Character> decoder = new HashMap<>();

	public huffmannE(String feeder) {
		HashMap<Character, Integer> freqmap = new HashMap<>();

		for (int i = 0; i < feeder.length(); i++) {

			char ch = feeder.charAt(i);

			if (freqmap.containsKey(ch)) {

				int oldval = freqmap.get(ch);

				int newval = oldval + 1;

				freqmap.put(ch, newval);

			}

			else {
				freqmap.put(ch, 1);
			}

		}

		heapp<Node> hp = new heapp<>();

		Set<Character> keys = freqmap.keySet();

		for (char key : keys) {

			Node node = new Node();

			node.frequency = freqmap.get(key);

			node.data = key;

			hp.addHP(node);

		}

		while (hp.size() != 1) {
			Node removed1 = hp.removeHP();
			Node removed2 = hp.removeHP();

			Node tobeadded = new Node();

			tobeadded.frequency = removed1.frequency + removed2.frequency;
			tobeadded.data = ' ';
			tobeadded.left = removed1;
			tobeadded.right = removed2;

			hp.addHP(tobeadded);

		}

		Node finalnode = hp.removeHP();

		this.fillEncDec(finalnode, "");

	}

	private void fillEncDec(Node node, String osf) {
		if (node == null) {
			return;
		}

		if (node.left == null && node.right == null) {
			this.encoder.put(node.data, osf);
			this.decoder.put(osf, node.data);
		}

		this.fillEncDec(node.left, osf + "0");
		this.fillEncDec(node.right, osf + "1");
	}

	public String encoder(String str) {
		String rv = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			rv += this.encoder.get(ch);

		}

		return rv;
	}

	public String decoder(String str) {
		String rv = "";
		String key = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			key += ch;
			if (this.decoder.containsKey(key)) {
				rv += this.decoder.get(key);
				key = "";
			}

		}

		return rv;
	}

}
