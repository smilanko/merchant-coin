package com.smilanko.blockchain.model;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

	private List<Block> blocks = new ArrayList<Block>();

	public boolean needsGenesisBlock() {
		return blocks.isEmpty();
	}

	public void addBlock(final Block block) {
		blocks.add(block);
	}
	
	public Block getBlockByIdx(final int idx) {
		return blocks.get(idx);
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public void clear() {
		blocks.clear();
	}

}
