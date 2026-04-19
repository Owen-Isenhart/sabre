# mage
*machine access and governance engine*  
*this is being done in preparation for my goldman sachs internship on the IAM team*

## Project Outline
**Phase 0: Study Java, Spring Boot, and Other Resources**
* Go through textbooks and project specific resources, take notes, and follow along with examples

**Phase 1: Identity Broker (Java) & Vault Integration**
* Deploy HashiCorp Vault. Generate an RSA key pair and store the private key inside Vault.
* Build a Java API using Spring Boot with a `/token` endpoint.
* Implement dynamic JWT issuance: The Java service fetches the private signing key from the Vault API to sign JWTs. No hardcoded secrets.
* Implement verifiable decay: Enforce a strict 5-minute Time-To-Live (TTL) on tokens to force continuous client re-authentication.

**Phase 2: Attribute-Based Access Control (ABAC) with OPA**
* Deploy Open Policy Agent (OPA) as a standalone service.
* Write Rego policies: Enforce rules such as requiring a `trading-engine` machine attribute and restricting access to 09:00–16:00 EST.
* Integrate OPA into the Java broker: The Spring Boot service queries the OPA REST API with client attributes prior to token issuance. Return HTTP 403 on OPA denial.

**Phase 3: High-Performance Client (C++20) & mTLS**
* Develop a C++20 automated agent to programmatically request JWTs from the Java broker.
* Configure mutual TLS (mTLS) for the connection between the C++ client and the Java broker to ensure bidirectional cryptographic certificate verification.
* Configure the C++ client to pass the JWT as a Bearer token to access a protected `/execute-trade` endpoint on the Java server.

**Phase 4: Kafka Audit Trail & Red Teaming**
* Integrate a Kafka producer into the Java broker using Spring for Apache Kafka.
* Publish all access attempts (both `200 OK` and `403 Forbidden`) to an `auth-audit-logs` Kafka topic.
* Write an autonomous hacking script to fuzz the Java API (attempting policy bypasses and sending malformed JWTs) to generate verifiable audit logs in Kafka.
* Write Terraform scripts to provision the infrastructure (Vault, Kafka, OPA, Java runtime environment) on a headless Ubuntu Server.

## Study Guide & Resources

**Identity & JWTs**
* **Reading:** RFC 6749: The OAuth 2.0 Authorization Framework.
* **Reading:** JWT.io Introduction (Token headers, payloads, and signatures).

**Machine Identity & Zero Trust**
* **Concept:** SPIFFE (Secure Production Identity Framework for Everyone) and SPIRE framework specifications.
* **Reading:** Cloudflare documentation: "What is mTLS?" 

**Policy & Architecture**
* **Reading:** Goldman Sachs Developer Blog: "Using Entitlements for Privileged Access to APIs and Applications in a Cloud Environment" (Covers the GS Cloud Entitlements Service and OPA).
* **Reading:** Open Policy Agent Documentation: "Microservice API Authorization".

**Security Implementations**
* **Book:** *Spring Security in Action* by Laurentiu Spilca (Focuses on secure enterprise code, OAuth 2.0, and defensive Java backend services).
* **Video:** [Agentic Identity & SPIFFE Explained](https://www.youtube.com/watch?v=lxJfnbNPnsU) (Covers traditional IAM breakdown with automated agents and SPIFFE adoption).
