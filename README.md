# mage
*machine access and governance engine*  
*this is being done in preparation for my goldman sachs internship on the IAM team*

## Project Outline

**Phase 1: Identity Broker (Go) & Vault Integration**
* Deploy HashiCorp Vault. Generate an RSA key pair and store the private key inside Vault.
* Build a Go API with a `/token` endpoint.
* Implement dynamic JWT issuance: The Go service fetches the private signing key from the Vault API to sign JWTs. No hardcoded secrets.
* Implement verifiable decay: Enforce a strict 5-minute Time-To-Live (TTL) on tokens to force continuous client re-authentication.

**Phase 2: Attribute-Based Access Control (ABAC) with OPA**
* Deploy Open Policy Agent (OPA) as a standalone service.
* Write Rego policies: Enforce rules such as requiring a `trading-engine` machine attribute and restricting access to 09:00–16:00 EST.
* Integrate OPA into the Go broker: The Go service queries the OPA REST API with client attributes prior to token issuance. Return HTTP 403 on OPA denial.

**Phase 3: High-Performance Client (C++20) & mTLS**
* Develop a C++20 automated agent to programmatically request JWTs from the Go broker.
* Configure mutual TLS (mTLS) for the connection between the C++ client and the Go broker to ensure bidirectional cryptographic certificate verification.
* Configure the C++ client to pass the JWT as a Bearer token to access a protected `/execute-trade` endpoint on the Go server.

**Phase 4: Kafka Audit Trail & Red Teaming**
* Integrate a Kafka producer into the Go broker.
* Publish all access attempts (both `200 OK` and `403 Forbidden`) to an `auth-audit-logs` Kafka topic.
* Write an autonomous hacking script to fuzz the Go API (attempting policy bypasses and sending malformed JWTs) to generate verifiable audit logs in Kafka.
* Write Terraform scripts to provision the infrastructure (Vault, Kafka, OPA, Go binary) on a headless Ubuntu Server.

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
* **Book:** *Black Hat Go* by Tom Steele (Focuses on secure network code and defensive Go backend services).
* **Video:** [Agentic Identity & SPIFFE Explained](https://www.youtube.com/watch?v=lxJfnbNPnsU) (Covers traditional IAM breakdown with automated agents and SPIFFE adoption).
