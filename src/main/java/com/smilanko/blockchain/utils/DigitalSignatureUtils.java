package com.smilanko.blockchain.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.smilanko.blockchain.model.Block;

public class DigitalSignatureUtils {

	private DigitalSignatureUtils() {
	}

	public static String generateMinersDigitalSignature(final PrivateKey privateKey, final String merchangesDigitalSignature, final int blockSeqNum,
			final String previousHash) throws Exception {
		return shaHash(merchangesDigitalSignature + blockSeqNum + previousHash);
	}

	public static String generateCustomerDigitalSignature(final PrivateKey customersPrivateKey,
			final PublicKey customersPublicKey, final PublicKey merchantsPublicKey, final String transactionDate,
			final String transactionAmount) throws Exception {
		final String toHash = new String(customersPublicKey.getEncoded()) + new String(merchantsPublicKey.getEncoded())
				+ transactionDate + transactionAmount;
		final String hash = shaHash(toHash);
		return RsaUtils.rsaEnc(customersPrivateKey, hash);
	}

	public static String generateMerchantDigitalSignature(final PrivateKey merchantPrivateKey,
			final PublicKey merchantsPublicKey, final PublicKey customersPublicKey, final String transactionDate,
			final String transactionAmount, final String customerDigitalSignature) throws Exception {
		final String toHash = new String(customersPublicKey.getEncoded()) + new String(merchantsPublicKey.getEncoded())
				+ transactionDate + transactionAmount + customerDigitalSignature;
		final String hash = shaHash(toHash);
		return RsaUtils.rsaEnc(merchantPrivateKey, hash);
	}

	public static String genHashForBlock(final Block block) throws Exception {
		final String customersPublicKey = block.getCustomersPublicKey() == null ? "0"
				: new String(block.getCustomersPublicKey().getEncoded());
		final String merchantsPublicKey = block.getMerchantsPublicKey() == null ? "0"
				: new String(block.getMerchantsPublicKey().getEncoded());
		final String toHash = customersPublicKey + merchantsPublicKey + block.getTransactionDate()
				+ block.getTransactionAmnt() + block.getCutomerSignature() + block.getMerchangeSignature()
				+ block.getBlockIdx() + block.getPreviousHash() + block.getMinersDigitalSig();
		return shaHash(toHash);
	}

	private static String shaHash(final String message) throws Exception {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
		return new String(hash);
	}

}
