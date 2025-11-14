keytool -genkeypair \
 -alias mylocalcert \
 -keyalg RSA \
 -keysize 2048 \
 -validity 365 \
 -keystore mylocalcert.jks \
 -storepass changeit \
 -keypass changeit \
 -dname "CN=localhost, OU=Dev, O=MyOrg, L=City, ST=State, C=IN"