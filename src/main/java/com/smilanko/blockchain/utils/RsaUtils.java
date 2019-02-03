package com.smilanko.blockchain.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class RsaUtils {

	public static List<KeyPair> generateKeys(int count) throws Exception {
		final List<KeyPair> entries = new ArrayList<KeyPair>();
		for (int i = 0; i < count; i++) {
			entries.add(generateKeyPair());
		}
		return entries;
	}

	public static KeyPair generateKeyPair() throws Exception {
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}

	public static String rsaEnc(final PrivateKey privateKey, final String encryptMe) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return rsa(encryptMe, cipher);
	}

	public static String rsaEnc(final PublicKey publicKey, final String encryptMe) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return rsa(encryptMe, cipher);
	}

	public static String rsaDec(final PublicKey publicKey, final String encryptedData) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return rsa(encryptedData, cipher);
	}

	public static String rsaDec(final PrivateKey privateKey, final String encryptedData) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return rsa(encryptedData, cipher);
	}

	private static String rsa(final String encryptMe, final Cipher cipher)
			throws IllegalBlockSizeException, BadPaddingException {
		final byte[] encryptedData = cipher.doFinal(encryptMe.getBytes());
		return new String(encryptedData);
	}

}
