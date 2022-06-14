package model.entities;

public class HuffmanNode {
	
	private Integer frequency;
	private Character data;
	private HuffmanNode left, right;
	private String code;
	
	public HuffmanNode() {}
	
	public HuffmanNode(Integer frequency, Character data) {
		this.frequency = frequency;
		this.data = data;
		left = right = null;
		code = null;
	}
	
	public HuffmanNode(Integer frequency, Character data, HuffmanNode left, HuffmanNode right) {
		this.frequency = frequency;
		this.data = data;
		this.left = left;
		this.right = right;
		code = null;
	}
	
	public HuffmanNode(HuffmanNode node) {
		this.frequency = node.frequency;
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
		code = null;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Character getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}

	public HuffmanNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanNode left) {
		this.left = left;
	}

	public HuffmanNode getRight() {
		return right;
	}

	public void setRight(HuffmanNode right) {
		this.right = right;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Frequency: " + this.frequency + ", Data: " + this.data + ", Code: " + this.code;
	}

}
