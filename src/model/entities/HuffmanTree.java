package model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TableCell;

public class HuffmanTree {

	private HuffmanNode root;
	private ArrayList<HuffmanNode> list;
	private ArrayList<HuffmanNode> treeA;
	private HashMap<String, Character> codeTable;
	private ArrayList<HuffmanNode> treeB;

	public HuffmanTree(HuffmanNode node) {
		root = node;
		list = new ArrayList<>();
	}

	public HuffmanTree() {
		list = new ArrayList<>();
	}

	public HuffmanNode getRoot() {
		return root;
	}

	public void setRoot(HuffmanNode root) {
		this.root = root;
	}

	public ArrayList<HuffmanNode> getList() {
		return list;
	}

	public void setList(ArrayList<HuffmanNode> list) {
		this.list = list;
	}

	public HuffmanNode addNodeToTree(HuffmanNode current, HuffmanNode insert) {
		if (current == null) {
			return new HuffmanNode(insert);
		}

		if (insert.getFrequency() < current.getFrequency()) {
			current.setLeft(addNodeToTree(current.getLeft(), insert));
		} else if (insert.getFrequency() > current.getFrequency()) {
			current.setRight(addNodeToTree(current.getRight(), insert));
		} else {
			return current;
		}

		return current;
	}

	public void listFrequency(String testCase) {
		for (int i = 0; i < testCase.length(); i++) {
			boolean foundEqual = false;
			for (HuffmanNode node : list) {
				if (testCase.charAt(i) == node.getData()) {
					node.setFrequency(node.getFrequency() + 1);
					foundEqual = true;
				}
			}
			if (!foundEqual) {
				list.add(new HuffmanNode(1, testCase.charAt(i)));
			}
		}
	}

	public void sortList() {
		boolean sorted = false;
		HuffmanNode aux;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < list.size() - 1; i++) {
				if (list.get(i).getFrequency() > list.get(i + 1).getFrequency()) {
					aux = list.get(i);
					list.remove(i);
					list.add(i + 1, aux);
					sorted = false;
				}
			}
		}
	}

	public void printList(ArrayList<HuffmanNode> list) {
		System.out.println();
		for (HuffmanNode node : list) {
			System.out.println(
					list.indexOf(node) + ". " + node.getFrequency() + ", " + node.getData() + ", " + node.getCode());
		}
	}

	public void listToTree() {
		treeA = new ArrayList<>(list);
		while (treeA.size() != 1) {
			HuffmanNode newNode = new HuffmanNode(treeA.get(0).getFrequency() + treeA.get(1).getFrequency(), null,
					treeA.get(0), treeA.get(1));
			treeA.remove(0);
			treeA.remove(0);
			addToTree(newNode);
		}
		root = treeA.get(0);
	}

	public void addToTree(HuffmanNode newNode) {
		if (treeA.size() != 0) {
			for (int i = -1; i < treeA.size() - 1; i++) {
				System.out.println(newNode + " < " + treeA.get(i + 1) + " ?");
				if (newNode.getFrequency() < treeA.get(i + 1).getFrequency()) {
					treeA.add(i + 1, newNode);
					return;
				}
			}
			treeA.add(newNode);
			return;
		} else {
			treeA.add(newNode);
			return;
		}
	}

	public void printTree(HuffmanNode current) {
		System.out.println(current);
		if (current.getLeft() != null) {
			printTree(current.getLeft());
		}
		if (current.getRight() != null) {
			printTree(current.getRight());
		}
	}

	public void generateCodes() {
		this.codeTable = new HashMap<>();
		for (HuffmanNode node : list) {
			findNodeCode(node, root, "");
		}
	}
	
	public void printCodeTable() {
		for (Map.Entry<String, Character> pair : codeTable.entrySet()) {
			System.out.println(pair.getKey() + ": " + pair.getValue());
		}
	}

	public void findNodeCode(HuffmanNode searched, HuffmanNode current, String code) {
		if (searched.getData() == current.getData()) {
			searched.setCode(code);
			codeTable.put(code, current.getData());
		}
		if (current.getLeft() != null) {
			findNodeCode(searched, current.getLeft(), code + "0");
		}
		if (current.getRight() != null) {
			findNodeCode(searched, current.getRight(), code + "1");
		}
	}

	public HuffmanNode findNodeByData(Character c) {
		for (HuffmanNode node : list) {
			if (node.getData() == c.charValue()) {
				return node;
			}
		}
		return null;
	}

	public String textToCode(String testCase) {
		String huffmanCode = "";
		for (Character c : testCase.toCharArray()) {
			huffmanCode += findNodeByData(c).getCode();
		}
		return huffmanCode;
	}

	public void codeToText(String code, Integer index, HuffmanNode current, String text) {
		if (current != null) {
			if (current.getCode() != null) {
				text += current.getData();
				//System.out.println(text);
				if (index == code.toCharArray().length) {
					index++;
					System.out.println(text);
					return;
				} else {
					codeToText(code, index, root, text);
				}
			}
		}
		if (current != null && code.toCharArray()[index] == '0') {
			codeToText(code, index + 1, current.getLeft(), text);
		}
		if (current != null && code.toCharArray()[index] == '1') {
			codeToText(code, index + 1, current.getRight(), text);
		}
		return;
	}

	public void doAll(String testCase) {

		testCase = testCase.toLowerCase();
		
		System.out.println(testCase);

		listFrequency(testCase);

		sortList();

		printList(list);
		System.out.println();

		listToTree();

		System.out.println();
		printTree(root);

		System.out.println();
		printList(list);

		generateCodes();

		System.out.println();
		printList(list);

		System.out.println();
		System.out.println(textToCode(testCase));

		System.out.println();
		printTree(root);
		
		String code = textToCode(testCase);

		System.out.println();
		codeToText(code, 0, root, "");
	}
}
