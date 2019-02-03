package com.smilanko.blockchain.model;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

import com.smilanko.blockchain.utils.RsaUtils;

public class Miner {

	private KeyPair keys;
	private BlockChain chain = new BlockChain();
	int blockIdx = 0;

	public void addBlockToChain(final Transaction trans) throws Exception {
		if (chain.needsGenesisBlock()) {
			chain.addBlock(new Block(getPrivateKey()));
		}
		// add transaction as block
		final Block blockToAdd = new Block(getPrivateKey(), chain.getBlockByIdx(++blockIdx - 1), trans);
		chain.addBlock(blockToAdd);
	}

	private PrivateKey getPrivateKey() throws Exception {
		return getKeys().getPrivate();
	}

	public void clearChain() {
		chain.clear();
		blockIdx = 0;
	}

	public KeyPair getKeys() throws Exception {
		if (keys == null) {
			keys = RsaUtils.generateKeyPair();
		}
		return keys;
	}

	public void showMeTheChain() {
		final List<Block> blocks = chain.getBlocks();
		for (final Block block : blocks) {
			System.out.println(block);
		}
	}

	public void showMeBlocksForCustomer(final PublicKey cusomterKey) {
		final List<Block> blocks = chain.getBlocks();
		for (final Block block : blocks) {
			if (block.getCustomersPublicKey() != null && block.getCustomersPublicKey().equals(cusomterKey)) {
				System.out.println(block);
			}
		}
	}

	public void showMeBlocksForMerchant(final PublicKey merchantKey) {
		final List<Block> blocks = chain.getBlocks();
		for (final Block block : blocks) {
			if (block.getMerchantsPublicKey() != null && block.getMerchantsPublicKey().equals(merchantKey)) {
				System.out.println(block);
			}
		}
	}

	public void showMeHashes() {
		final List<Block> blocks = chain.getBlocks();
		for (final Block block : blocks) {
			block.prettyPrintHashes();
		}
	}

}
