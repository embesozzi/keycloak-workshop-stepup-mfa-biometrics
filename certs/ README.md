
```sh
## Here is an example on how to generate the PoC certificates.

openssl req -x509 -nodes -new -sha256 -days 1024 -newkey rsa:2048 -keyout root-ca.key -out root-ca.pem -subj "/C=US/CN=root-ca"

openssl req -new -nodes -newkey rsa:2048 -keyout localhost.key -out localhost.csr -subj "/C=US/ST=NY/L=NY/O=Bank/CN=localhost"

openssl x509 -req -sha256 -days 1024 -in localhost.csr -CA root-ca.pem -CAkey root-ca.key -CAcreateserial -extfile domains.ext -out localhost.crt
```