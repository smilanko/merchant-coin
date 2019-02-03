package com.smilanko.blockchain.model;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

import com.smilanko.blockchain.utils.DigitalSignatureUtils;

public class Block {

	private PublicKey customersPublicKey;
	private PublicKey merchantsPublicKey;
	private String transactionDate;
	private String transactionAmnt;
	private String cutomerSignature;
	private String merchangeSignature;
	private int blockIdx;
	private String previousHash;
	private String hash;
	private String minersDigitalSig;

	public Block(final PrivateKey minersPrivateKey) throws Exception {
		// genesis block
		this.customersPublicKey = null;
		this.merchantsPublicKey = null;
		this.transactionDate = "0";
		this.transactionAmnt = "0";
		this.cutomerSignature = "0";
		this.minersDigitalSig = "0";
		this.blockIdx = 0;
		this.previousHash = "0";
		this.minersDigitalSig = (DigitalSignatureUtils.generateMinersDigitalSignature(minersPrivateKey,
				this.merchangeSignature, this.blockIdx, this.previousHash));
		this.hash = DigitalSignatureUtils.genHashForBlock(this);
	}

	public Block(final PrivateKey minersPrivateKey, final Block previousBlock, final Transaction trans)
			throws Exception {
		// regular block
		this.customersPublicKey = trans.getCustomersPublicKey();
		this.merchantsPublicKey = trans.getMerchantsPublicKey();
		this.transactionDate = trans.getTransactionDate();
		this.transactionAmnt = trans.getTransactionAmnt();

		this.cutomerSignature = trans.getCutomerSignature();
		this.minersDigitalSig = trans.getMerchangeSignature();
		this.blockIdx = previousBlock.getBlockIdx() + 1;
		this.previousHash = previousBlock.getHash();
		this.minersDigitalSig = DigitalSignatureUtils.generateMinersDigitalSignature(minersPrivateKey,
				this.merchangeSignature, this.blockIdx, this.previousHash);
		this.hash = DigitalSignatureUtils.genHashForBlock(this);
	}

	public PublicKey getCustomersPublicKey() {
		return customersPublicKey;
	}

	public void setCustomersPublicKey(PublicKey customersPublicKey) {
		this.customersPublicKey = customersPublicKey;
	}

	public PublicKey getMerchantsPublicKey() {
		return merchantsPublicKey;
	}

	public void setMerchantsPublicKey(PublicKey merchantsPublicKey) {
		this.merchantsPublicKey = merchantsPublicKey;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionAmnt() {
		return transactionAmnt;
	}

	public void setTransactionAmnt(String transactionAmnt) {
		this.transactionAmnt = transactionAmnt;
	}

	public String getCutomerSignature() {
		return cutomerSignature;
	}

	public void setCutomerSignature(String cutomerSignature) {
		this.cutomerSignature = cutomerSignature;
	}

	public String getMerchangeSignature() {
		return merchangeSignature;
	}

	public void setMerchangeSignature(String merchangeSignature) {
		this.merchangeSignature = merchangeSignature;
	}

	public int getBlockIdx() {
		return blockIdx;
	}

	public void setBlockIdx(int blockIdx) {
		this.blockIdx = blockIdx;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMinersDigitalSig() {
		return minersDigitalSig;
	}

	public void setMinersDigitalSig(String minersDigitalSig) {
		this.minersDigitalSig = minersDigitalSig;
	}

	private String prettyPrintKey(final PublicKey key) {
		if (key == null) {
			return "null";
		}
		final RSAPublicKey rsa = (RSAPublicKey) key;
		final String mod = rsa.getModulus().toString();
		return new String("mod ( last 5 ):" + mod.substring(mod.length() - 5) + "-" + rsa.getPublicExponent());
	}

	public void prettyPrintHashes() {
		System.out.println("## Block:: " + this.getBlockIdx());
		System.out.print("Previous hash:: ");
		if (!previousHash.equals("0")) {
			prettyPrintSpecificHash(previousHash.getBytes());
		} else {
			System.out.print("0");
		}
		System.out.println("");
		System.out.print("Current hash:: ");
		if (hash != null) {
			prettyPrintSpecificHash(hash.getBytes());
		} else {
			System.out.print("null");
		}
		System.out.println("");
	}

	private void prettyPrintSpecificHash(final byte[] hashToProcess) {
		final String hex = String.format("%064x", new BigInteger(1, hashToProcess));
		System.out.print(hex);
	}

	@Override
	public String toString() {
		return "Block [customersPublicKey=" + prettyPrintKey(customersPublicKey) + ", merchantsPublicKey="
				+ prettyPrintKey(merchantsPublicKey) + ", transactionDate=" + transactionDate + ", transactionAmnt="
				+ transactionAmnt + "]";
	}

}
