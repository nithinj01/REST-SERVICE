# Data Flow Diagram

```mermaid
graph TD
  subgraph Data Ingestion
    A[Sources (Partners, Hotels)]
    B[Google Cloud Storage (Apache Kafka)]
    A --> B
  end

  subgraph Data Storage
    C[Google Cloud Storage (Images)]
    D[Google BigQuery (Metadata)]
    B --> C
    B --> D
  end

  subgraph Feature Extraction
    E[Google AI Platform (ML Models)]
    F[Google BigQuery (Extracted Features)]
    C --> E
    E --> F
  end

  subgraph Main Image Selection
    G[Google Compute Engine (GKE)]
    H[Google BigQuery (Selected Images)]
    F --> G
    G --> H
  end

  subgraph Output Generation
    I[APIs (Snapshots)]
    J[Google BigQuery (Changes)]
    H --> I
    H --> J
  end

  subgraph Reporting
    K[Google Cloud Monitoring (Metrics)]
    H --> K
  end

  style A fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style B fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style C fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style D fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style E fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style F fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style G fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style H fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style I fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style J fill:#E6E6FA,stroke:#000,stroke-width:2px;
  style K fill:#E6E6FA,stroke:#000,stroke-width:2px;
