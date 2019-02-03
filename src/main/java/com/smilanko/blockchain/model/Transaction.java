package com.smilanko.blockchain.model;

import java.security.PublicKey;

public class Transaction {

	private PublicKey customersPublicKey;
	private PublicKey merchantsPublicKey;
	private String transactionDate;
	private String transactionAmnt;
	private String cutomerSignature;
	private String merchangeSignature;

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

}
