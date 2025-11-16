### Generate local keystore

```shell
keytool -genkeypair \
  -alias jwt-local-key \
  -keyalg RSA \
  -keysize 2048 \
  -sigalg SHA256withRSA \
  -dname "CN=portal, OU=local, O=gvu" \
  -keystore jwt-local-keys.p12 \
  -storetype PKCS12 \
  -storepass changeit \
  -keypass changeit
```
