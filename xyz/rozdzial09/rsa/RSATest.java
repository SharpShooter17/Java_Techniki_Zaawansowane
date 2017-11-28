package rsa;

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * Program wykorzystujący algorytm szyfrowania RSA.
 * Uruchamianie:
 * java RSATest -genkey public private
 * java RSATest -encrypt plaintext encrypted public
 * java RSATest -decrypt encrypted decrypted private
 * @author Cay Horstmann
 * @version 1.01 2012-06-10 
 */
public class RSATest
{
   private static final int KEYSIZE = 512;

   public static void main(String[] args) 
      throws IOException, GeneralSecurityException, ClassNotFoundException
   {
      if (args[0].equals("-genkey"))
      {
         KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
         SecureRandom random = new SecureRandom();
         pairgen.initialize(KEYSIZE, random);
         KeyPair keyPair = pairgen.generateKeyPair();
         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1])))
         {
            out.writeObject(keyPair.getPublic());
         }
         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[2])))
         {
            out.writeObject(keyPair.getPrivate());
         }
      }
      else if (args[0].equals("-encrypt"))
      {
         KeyGenerator keygen = KeyGenerator.getInstance("AES");
         SecureRandom random = new SecureRandom();
         keygen.init(random);
         SecretKey key = keygen.generateKey();

         // szyfruje klucz AES za pomocą klucza publicznego RSA
         try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(args[2]));
            InputStream in = new FileInputStream(args[1]) )
         {
            Key publicKey = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE, publicKey);
            byte[] wrappedKey = cipher.wrap(key);
            out.writeInt(wrappedKey.length);
            out.write(wrappedKey);
         
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            Util.crypt(in, out, cipher);            
         }         
      }
      else
      {
         try (DataInputStream in = new DataInputStream(new FileInputStream(args[1]));
            ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
            OutputStream out = new FileOutputStream(args[2]))
         {
            int length = in.readInt();
            byte[] wrappedKey = new byte[length];
            in.read(wrappedKey, 0, length);

            // odszyfrowuje za pomocą klucza prywatnego RSA
            Key privateKey = (Key) keyIn.readObject();
   
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE, privateKey);
            Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
   
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
   
            Util.crypt(in, out, cipher);
         }
      }
   }
}
