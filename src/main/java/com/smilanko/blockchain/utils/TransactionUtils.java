package com.smilanko.blockchain.utils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.smilanko.blockchain.model.Transaction;

public class TransactionUtils {

	private TransactionUtils() {
	}

	public static List<Transaction> generateRandomTransactions(final int count, PrivateKey customersPrivateKey,
			PublicKey customersPublicKey, PrivateKey merchantPrivateKey, PublicKey merchantsPublicKey)
			throws Exception {
		final List<Transaction> transactions = new ArrayList<Transaction>();

		for (int i = 0; i < count; i++) {
			transactions.add(generateRandomTransaction(customersPrivateKey, customersPublicKey, merchantPrivateKey,
					merchantsPublicKey));
		}

		return transactions;
	}

	public static Transaction generateRandomTransaction(PrivateKey customersPrivateKey, PublicKey customersPublicKey,
			PrivateKey merchantPrivateKey, PublicKey merchantsPublicKey) throws Exception {
		final Transaction trans = new Transaction();
		trans.setCustomersPublicKey(customersPublicKey);
		trans.setMerchantsPublicKey(merchantsPublicKey);
		trans.setTransactionDate(randomTransactionDate());
		trans.setTransactionAmnt(randomTransactionAmnt());
		trans.setCutomerSignature(DigitalSignatureUtils.generateCustomerDigitalSignature(customersPrivateKey,
				customersPublicKey, merchantsPublicKey, trans.getTransactionDate(), trans.getTransactionAmnt()));
		trans.setMerchangeSignature(DigitalSignatureUtils.generateMerchantDigitalSignature(merchantPrivateKey,
				merchantsPublicKey, customersPublicKey, trans.getTransactionDate(), trans.getTransactionAmnt(),
				trans.getCutomerSignature()));
		return trans;
	}

	private static String randomTransactionAmnt() {
		final int randomNumber = new Random().nextInt((10 - 5) + 1) + 5;
		return randomNumber + ".00";
	}

	private static String randomTransactionDate() {
		final int randomDay = new Random().nextInt((31 - 1) + 1) + 1;
		final int randomMonth = new Random().nextInt((12 - 1) + 1) + 1;
		final int randomYear = new Random().nextInt((2000 - 1990) + 1) + 1990;
		return randomMonth + "" + randomDay + "" + randomYear;
	}

}
