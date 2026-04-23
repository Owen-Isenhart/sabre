# SABRE
*System Account & Binary Resource Engine*  
*This is being done in preparation for my Goldman Sachs internship on the IAM Infrastructure team.*

---

## Project Outline

**Phase 0: Language Mastery & BCL Fundamentals**
* Study the **Base Class Library (BCL)** and modern C# features to transition from low-level systems paradigms (C++/Rust) to high-level enterprise architecture.
* Focus on **LINQ**, **Asynchronous Programming (async/await)**, and **Memory Management** to ensure efficient handling of large-scale directory data.
* Gain a technical understanding of the differences between **.NET Framework 4.8** (common in established corporate environments) and modern **.NET 8/9**.

**Phase 1: High-Performance Directory Persistence (C# & MongoDB)**
* Build a backend service using **C#** and **ASP.NET Web API 2** to simulate an enterprise identity provider.
* Implement a hierarchical "Shadow Directory" in **MongoDB**. Use Document-based storage to represent complex organizational units (OUs), mirroring how directory services handle unstructured identity metadata.
* **Expert Implementation:** Use the **Repository Pattern** and **Dependency Injection** to keep the core identity logic independent of the database driver, allowing for professional-grade testability and scaling.

**Phase 2: Automated Lifecycle Orchestration (JML)**
* Develop the **Joiner-Mover-Leaver (JML)** pipeline, which is the industry standard for managing identity lifecycles:
    * **Joiner:** Automated account creation with standardized attributes for new system entities.
    * **Mover:** Logic to handle permission changes and attribute updates when a system account’s role or location shifts.
    * **Leaver:** A secure "Deprovisioning Engine" that manages account tombstoning and resource reclamation.
* **Expert Implementation:** Utilize **TPL Dataflow** or **System.Threading.Channels** to process account updates in high-concurrency batches, ensuring the system remains responsive under heavy administrative load.



**Phase 3: Secure Auth Middleware & Directory Handshakes**
* Build a custom **Authentication Provider** in .NET that simulates secure directory handshakes (LDAP-style).
* Create a machine-to-machine (M2M) token issuance service to allow automated systems to request short-lived, verifiable credentials.
* **Expert Implementation:** Apply **Span<T>** and **Memory<T>** where appropriate to optimize string parsing during log analysis or token validation, demonstrating a "systems-first" approach to managed code.



**Phase 4: Governance Dashboard & Compliance Observability**
* Build a reactive administrative dashboard in **Angular** to visualize the directory tree and manage account states.
* Implement a **Unified Audit Log** that records every modification and authentication attempt into MongoDB for regulatory compliance and security forensics.
* **Expert Implementation:** Integrate **BenchmarkDotNet** to profile critical paths in the authentication logic, ensuring the service meets the low-latency requirements of a high-frequency infrastructure environment.



---

## Study Guide & Resources

### **Core C# & .NET Framework**
* **Book:** *C# 12 in a Nutshell: The Definitive Reference* by Joseph Albahari. (Focus specifically on the chapters regarding **LINQ**, **Collections**, **Threading**, and **Networking**).
* **Book:** *Effective C# (50 Specific Ways to Improve Your C#)* by Bill Wagner. (Focus on the items regarding **Resource Management** and **Generic Design**).
* **Documentation:** [Microsoft .NET Framework 4.8 Documentation](https://learn.microsoft.com/en-us/dotnet/framework/) (Review the specific limitations and capabilities of the older framework).

### **Directory Services & IAM Standards**
* **Reading:** *Active Directory: Designing, Deploying, and Running Active Directory* (Focus on **LDAP Queries**, **Forest/Domain Trusts**, and **Schema Management**).
* **Protocol:** [RFC 4511: Lightweight Directory Access Protocol (LDAP)](https://datatracker.ietf.org/doc/html/rfc4511) (The technical foundation for how directory services communicate).
* **Concept:** **Least Privilege Access** and **Identity as the Perimeter** in enterprise security models.

### **Software Architecture**
* **Book:** *Dependency Injection Principles, Practices, and Patterns* by Steven van Deursen and Mark Seemann. (Essential for building decoupled, professional-grade .NET services).
* **Book:** *Pro ASP.NET Web API 2* by Kevin Hoffman. (The standard guide for building RESTful services on the .NET Framework).
* **Concept:** **Repository and Unit of Work Patterns** for abstracting data access in high-performance environments.

### **Database & Frontend**
* **Book:** *MongoDB: The Definitive Guide* by Shannon Bradshaw. (Specifically the chapters on **Indexing**, **Aggregation**, and **Schema Design** for hierarchical data).
* **Framework:** [Angular.io - Reactive Forms](https://angular.io/guide/reactive-forms) (Essential for the complex validation required in identity management dashboards).
