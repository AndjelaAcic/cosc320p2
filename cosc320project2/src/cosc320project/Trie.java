package cosc320project;

public class Trie {
	
	char c;
	Trie[] next;
	public Trie(char c) {
		super();
		this.c = c;
		next = new Trie[26];
	}
	
	public Trie()
	{
		this.c = '?';
		this.next = new Trie[26];
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public Trie[] getNext() {
		return next;
	}

	public void setNext(Trie[] next) {
		this.next = next;
	}
	
	

}
