package com.smilanko.blockchain;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import com.smilanko.blockchain.model.Miner;
import com.smilanko.blockchain.model.Transaction;
import com.smilanko.blockchain.utils.RsaUtils;
import com.smilanko.blockchain.utils.TransactionUtils;

public class App {
	public static void main(String[] args) throws Exception {

		final List<KeyPair> customers = RsaUtils.generateKeys(5);
		final List<KeyPair> merchants = RsaUtils.generateKeys(2);

		final List<Transaction> cusomterOneTransactions = TransactionUtils.generateRandomTransactions(2,
				customers.get(0).getPrivate(), customers.get(0).getPublic(), merchants.get(0).getPrivate(),
				merchants.get(0).getPublic());
		final List<Transaction> cusomterTwoTransactions = TransactionUtils.generateRandomTransactions(10,
				customers.get(1).getPrivate(), customers.get(1).getPublic(), merchants.get(0).getPrivate(),
				merchants.get(0).getPublic());
		final List<Transaction> cusomterThreeTransactions = TransactionUtils.generateRandomTransactions(2,
				customers.get(2).getPrivate(), customers.get(2).getPublic(), merchants.get(0).getPrivate(),
				merchants.get(0).getPublic());
		final List<Transaction> cusomterFourTransactions = TransactionUtils.generateRandomTransactions(10,
				customers.get(3).getPrivate(), customers.get(3).getPublic(), merchants.get(0).getPrivate(),
				merchants.get(0).getPublic());
		final List<Transaction> cusomterFiveTransactions = TransactionUtils.generateRandomTransactions(1,
				customers.get(4).getPrivate(), customers.get(4).getPublic(), merchants.get(1).getPrivate(),
				merchants.get(1).getPublic());

		final List<Transaction> transactions = new ArrayList<Transaction>(cusomterOneTransactions);
		transactions.addAll(cusomterTwoTransactions);
		transactions.addAll(cusomterThreeTransactions);
		transactions.addAll(cusomterFourTransactions);
		transactions.addAll(cusomterFiveTransactions);

		final Miner miner = new Miner();
		for (final Transaction transaction : transactions) {
			miner.addBlockToChain(transaction);
		}
		miner.showMeTheChain();
		System.out.println("### transactions for customer 3");
		miner.showMeBlocksForCustomer(customers.get(2).getPublic());
		System.out.println("### transactions for merchant 2");
		miner.showMeBlocksForMerchant(merchants.get(1).getPublic());

		final Miner minerTwo = new Miner();
		final Transaction transOne = TransactionUtils.generateRandomTransaction(customers.get(1).getPrivate(),
				customers.get(1).getPublic(), merchants.get(0).getPrivate(), merchants.get(0).getPublic());
		minerTwo.addBlockToChain(transOne);
		final Transaction transTwo = TransactionUtils.generateRandomTransaction(customers.get(1).getPrivate(),
				customers.get(1).getPublic(), merchants.get(0).getPrivate(), merchants.get(0).getPublic());
		minerTwo.addBlockToChain(transTwo);
		final Transaction transThree = TransactionUtils.generateRandomTransaction(customers.get(1).getPrivate(),
				customers.get(1).getPublic(), merchants.get(0).getPrivate(), merchants.get(0).getPublic());
		minerTwo.addBlockToChain(transThree);
		System.out.println("###### Hashes after regular miner processing");
		minerTwo.showMeHashes();

		// clear the chain to demonstrate issue
		minerTwo.clearChain();
		minerTwo.addBlockToChain(transOne);
		transTwo.setTransactionAmnt("20.00");
		minerTwo.addBlockToChain(transTwo);
		minerTwo.addBlockToChain(transThree);
		System.out.println("###### Hashes after miner tampering processing");
		minerTwo.showMeHashes();

	}
}
