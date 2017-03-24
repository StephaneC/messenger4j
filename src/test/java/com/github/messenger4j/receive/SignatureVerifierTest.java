package com.github.messenger4j.receive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Stéphane Castrec on 24/03/2017.
 */
public class SignatureVerifierTest {


  @Test
  public void checkPayloadSignatureTest() {
    //Mock part 
    String signatureHeader = "sha1=360ce018c33f3f338b4420991486442795f63802";
    String payload = "{\"object\":\"page\",\"entry\":[{\"id\":\"1811797742403366\",\"time\":1490257253862,\"messaging\":[{\"sender\":{\"id\":\"1390636220993304\"},\"recipient\":{\"id\":\"1811797742403366\"},\"timestamp\":1490257253792,\"message\":{\"mid\":\"mid.$cAAZv0ibyiHBhK4ZdoFa-kESg-VAN\",\"seq\":1686,\"text\":\"quelle heure est-il?\"}}]}]}";
    String appSecret = "0a81080421baa2e1336000bd60662b7b";

    //Run OK test 
    Assert.assertTrue(SignatureVerifier.isSignatureValid(payload, signatureHeader, appSecret));
  }

	@Test
	public void checkPayloadSignatureUnicodeTest()  {
		//Mock part 
		String signatureHeader = "sha1=be66eef40034847d3161498ff6aafb81550ea504";
		String payload = "{\"object\":\"page\",\"entry\":[{\"id\":\"1811797742403366\",\"time\":1490257253862,\"messaging\":[{\"sender\":{\"id\":\"1390636220993304\"},\"recipient\":{\"id\":\"1811797742403366\"},\"timestamp\":1490257253792,\"message\":{\"mid\":\"mid.$cAAZv0ibyiHBhK4ZdoFa-kESg-VAN\",\"seq\":1686,\"text\":\"äöå\"}}]}]}";
		String appSecret = "0a81080421baa2e1336000bd60662b7b";

		//Run OK test 
		Assert.assertTrue(SignatureVerifier.isSignatureValid(payload, signatureHeader, appSecret));
	}

  @Test
  public void checkPayloadSignatureKo() {
    //Mock part 
    String signatureHeader = "sha1=be66eef40034847d3161498ff6aafb81550ea505";
    String payload = "{\"object\":\"page\",\"entry\":[{\"id\":\"1811797742403366\",\"time\":1490257253862,\"messaging\":[{\"sender\":{\"id\":\"1390636220993304\"},\"recipient\":{\"id\":\"1811797742403366\"},\"timestamp\":1490257253792,\"message\":{\"mid\":\"mid.$cAAZv0ibyiHBhK4ZdoFa-kESg-VAN\",\"seq\":1686,\"text\":\"äöå\"}}]}]}";
    String appSecret = "0a81080421baa2e1336000bd60662b7b";

    //Run OK test 
    Assert.assertFalse(SignatureVerifier.isSignatureValid(payload, signatureHeader, appSecret));
  }
}
