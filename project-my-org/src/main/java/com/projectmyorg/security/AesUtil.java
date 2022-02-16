/**
 * 
 */
package com.projectmyorg.security;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Shankar D
 *
 */
@Component
public class AesUtil {

	private static final int IV_LENGTH_BYTE = 16;

	private static final String ALGORITHM = "AES";

	private static final String ALGORITHM_PADDING = "AES/CBC/PKCS5Padding";

	public static final String SECRET_KEY = "fXGJ56sXijhzyED6EHsJWgdwM3y46ItvrZ1F/73ohMs=";

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	private static final Logger LOGGER = LoggerFactory.getLogger(AesUtil.class);

	/**
	 * Method to encode the byte array using Base64 getEncoder().
	 * 
	 * @param byte[]
	 * @return String - encoded string
	 */
	public static String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	/**
	 * Method to decode the string using Base64 getDecoder().
	 * 
	 * @param String
	 * @return byte[] - decoded value
	 */
	public static byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}

	/**
	 * Method to generate IvParameterSpec.
	 * 
	 * @return IvParameterSpec
	 */
	public static IvParameterSpec generateIv() {
		byte[] iv = new byte[IV_LENGTH_BYTE];
		SECURE_RANDOM.nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	/**
	 * Method to generate IvParameterSpec.
	 * 
	 * @param byte[] iv
	 * @return IvParameterSpec
	 */
	public static IvParameterSpec generateIv(byte[] iv) {
		return new IvParameterSpec(iv);
	}

	/**
	 * Method to generate the SecretKey using SecretKeySpec with
	 * {@link #SECRET_KEY}.
	 * 
	 * @return SecretKey
	 */
	public static SecretKey getSecretKey() {
		return new SecretKeySpec(decode(SECRET_KEY), ALGORITHM);
	}

	/**
	 * Method to encrypt the string value. Calling {@link #generateIv()} to generate
	 * Initialization vector. Calling {@link #encrypt(byte[], byte[])} method to
	 * encrypt the given value with IV. Concatenating the IV and encrypted value.
	 * Calling {@link #encode(byte[])} method to code the Concatenated value.
	 * 
	 * @param String - value which is needs to be encrypt.
	 * @return String - encrypted string.
	 */
	public static String encrypt(String value) {
		try {
			if (value != null && !value.isEmpty()) {
				byte[] iv = generateIv().getIV();
				byte[] cipherText = encrypt(value.getBytes(), iv);
				byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length).put(iv).put(cipherText)
						.array();
				return encode(cipherTextWithIv);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;

	}

	/**
	 * Method to encrypt the byte[] value. Creating Cipher object for
	 * {@link #ALGORITHM_PADDING}. Initializing the cipher object in ENCRYPT_MODE
	 * with sercretKey and GCMParameterSpec. Encrypting the value.
	 * 
	 * @param byte[] - text in byte[], which is needs to be encrypt.
	 * @param byte[] - IV in byte[]
	 * 
	 * @return byte[] - encrypted value
	 */
	public static byte[] encrypt(byte[] text, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), generateIv(iv));
			return cipher.doFinal(text);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new byte[0];
		}
	}

	/**
	 * Method to decrypt the string value. Creating ByteBuffer object for
	 * encryptedtext. Splitting the IV and cipher text. Calling
	 * {@link #decrypt(byte[], byte[])} to decrypt the value.
	 * 
	 * @param String - value which is needs to be decrypt.
	 * @return String - decrypted string.
	 */
	public static String decrypt(String encryptedText) {
		try {
			if (encryptedText != null && !encryptedText.isEmpty()) {
				ByteBuffer byteBuffer = ByteBuffer.wrap(decode(encryptedText));

				byte[] iv = new byte[IV_LENGTH_BYTE];
				byteBuffer.get(iv);

				byte[] cipherText = new byte[byteBuffer.remaining()];
				byteBuffer.get(cipherText);

				return decrypt(cipherText, iv);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Method to decrypt the byte[] value. Creating Cipher object for
	 * {@link #ALGORITHM_PADDING}. Initializing the cipher object in DECRYPT_MODE
	 * with sercretKey and GCMParameterSpec. Decrypting the value. Converting the
	 * decrypted byte[] to String.
	 * 
	 * @param byte[] - text in byte[], which is needs to be decrypt.
	 * @param byte[] - IV in byte[]
	 * 
	 * @return String - decrypted value
	 */
	public static String decrypt(byte[] cText, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), generateIv(iv));
			byte[] plainText = cipher.doFinal(cText);
			return new String(plainText);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	public static String generate() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom secureRandom = new SecureRandom();
		int keyBitSize = 256;

		keyGenerator.init(keyBitSize, secureRandom);

		SecretKey secretKey = keyGenerator.generateKey();
		return convertSecretKeyToString(secretKey);
	}

	public static String convertSecretKeyToString(SecretKey secretKey) {
		byte[] rawData = secretKey.getEncoded();
		return Base64.getEncoder().encodeToString(rawData);
	}

}
