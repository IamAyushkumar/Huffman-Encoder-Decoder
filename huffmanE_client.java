package EncodingDecoding;

public class huffmanE_client {

	public static void main(String[] args) {

		String feeder = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbcccccdddaaabbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbcccccdddaaaaaaabbbbbbbbbbbcccccdddbbcccccddd";
		
		huffmannE h = new huffmannE(feeder);
		
		System.out.println(h.encoder("aabbcc"));
		System.out.println(h.decoder("001111101101"));
		
	}

}
