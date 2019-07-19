package io.github.lr.robotblockworld.entities;

public class Block {
	
	private static final String SPACE = " ";
	private static final String EMPTY = "";
	
	private int number;
	private Block father;
	private Block son;
	
	public Block(int number) {
		this.number = number;
	}
	
	public void setFather(Block father) {
		this.father = father;
	}
	public void setSon(Block son) {
		this.son = son;
	}
	
	public boolean isRoot() {
		return father == null; 
	}
	public boolean isLeaf() {
		return son == null;
	}

	public void cleanFather() {
		if (father != null) {
			father.son = null;
		}
	}
	public void clean() {
		if (son != null) {
			son.clean();
		}
		father = null;
		son = null;
	}
	
	public boolean sameRow(Block blockFrom) {
		Block block = father;
		while (block != null) {
			if (block == blockFrom) {
				return true;
			}
			block = block.father;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return number + (son != null ? SPACE + son.toString() : EMPTY);
	}

}
