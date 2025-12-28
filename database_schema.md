# Decision Engine – Database Schema

## Overview
This document describes the database schema for the **Decision Engine** service.

The Decision Engine is a generic, lightweight rule-evaluation service that determines outcomes based on configurable rules and runtime facts. It is intentionally designed to be reusable across multiple domains such as promotions, recommendations, feature flags, eligibility checks, and other contextual decisions.

---

## Design Principles
- **Generic and extensible**: Not tied to any specific business domain
- **Stateless runtime**: No user state persisted in the database
- **Configuration-driven**: Behavior controlled via data, not code
- **Safe and auditable**: Supports enable/disable through `is_active`
- **Clear contracts**:
  - `_id` columns are internal database identifiers
  - `_key` columns are stable identifiers used in APIs and external contracts

---

## Tables Overview

| Table | Purpose |
|------|---------|
| `decisions` | Defines a decision domain or use case |
| `rules` | Defines ordered rules under a decision |
| `rule_conditions` | Defines conditions that make up a rule |
| `facts` | Defines supported input facts |
| `outcomes` | Defines the result returned when a rule matches |

---

## Table Definitions

### 1. `decisions`
Represents a logical decision domain (for example: recommendation selection, feature evaluation, or message selection).

```sql
CREATE TABLE decisions (
  decision_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  decision_key VARCHAR(100) NOT NULL UNIQUE,

  name VARCHAR(100) NOT NULL,
  description VARCHAR(255),
  type VARCHAR(50),

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100)
);
```

---

### 2. `outcomes`
Represents the result returned when a rule matches.

```sql
CREATE TABLE outcomes (
  outcome_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  outcome_key VARCHAR(100) NOT NULL UNIQUE,

  name VARCHAR(100),
  type VARCHAR(50) NOT NULL,
  payload JSON NOT NULL,

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100)
);
```

**Notes**
- `payload` is stored as JSON to allow flexible outcome structures
- Outcomes are reusable and decoupled from rule logic

---

### 3. `facts`
Represents supported input attributes that can be used in rule conditions.

```sql
CREATE TABLE facts (
  fact_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  fact_key VARCHAR(100) NOT NULL UNIQUE,

  name VARCHAR(100),
  data_type ENUM('BOOLEAN','STRING','NUMBER','DATE') NOT NULL,

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100)
);
```

**Notes**
- Facts are centrally defined and strongly typed
- New facts must be explicitly registered before use

---

### 4. `rules`
Represents a single rule within a decision.

```sql
CREATE TABLE rules (
  rule_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  decision_id BIGINT NOT NULL,
  outcome_id BIGINT NOT NULL,

  priority INT NOT NULL,
  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100),

  CONSTRAINT fk_rules_decision
    FOREIGN KEY (decision_id) REFERENCES decisions(decision_id),

  CONSTRAINT fk_rules_outcome
    FOREIGN KEY (outcome_id) REFERENCES outcomes(outcome_id)
);
```

**Notes**
- Rules are evaluated in ascending priority order
- Each rule maps to exactly one outcome

---

### 5. `rule_conditions`
Represents individual conditions that together define a rule.

```sql
CREATE TABLE rule_conditions (
  rule_con_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  rule_id BIGINT NOT NULL,
  fact_id BIGINT NOT NULL,

  operator ENUM('EQ','NEQ','IN','NOT_IN','GT','LT') NOT NULL,
  expected_value VARCHAR(255) NOT NULL,

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100),

  CONSTRAINT fk_rule_conditions_rule
    FOREIGN KEY (rule_id) REFERENCES rules(rule_id),

  CONSTRAINT fk_rule_conditions_fact
    FOREIGN KEY (fact_id) REFERENCES facts(fact_id)
);
```

**Notes**
- All conditions within a rule are ANDed together (V1)
- Expected values are cast at runtime based on fact data type

---

## Relationships Summary

```
Decision
  └── Rules (1:N)
        └── Rule Conditions (1:N)
              └── Facts (N:1)

Rule
  └── Outcome (N:1)
```

---

## Sample Example (Generic)

### Requirement
> If a user belongs to a specific category and has not completed a required action, return a reminder message.

---

### Defined Facts

| fact_key | data_type |
|--------|-----------|
| userCategory | STRING |
| actionCompleted | BOOLEAN |

---

### Outcome (example payload)

```json
{
  "outcome_key": "reminder_message",
  "type": "TEXT",
  "payload": {
    "title": "Action Pending",
    "message": "Please complete the required action to continue.",
    "ctaLabel": "Complete Now",
    "ctaUrl": "https://example.com/action"
  }
}
```

---

### Rule Definition

| Condition Fact | Operator | Expected Value |
|---------------|----------|----------------|
| userCategory | EQ | standard |
| actionCompleted | EQ | false |

---

### Runtime Evaluation

Input:
```json
{
  "decisionKey": "action_reminder",
  "context": {
    "userCategory": "standard"
  }
}
```

Resolved facts:
```json
{
  "userCategory": "standard",
  "actionCompleted": false
}
```

Result:
```json
{
  "outcomeKey": "reminder_message",
  "type": "TEXT",
  "payload": { ... }
}
```

---

## Conclusion
This schema provides a strong foundation for building a generic, extensible decision engine. It cleanly separates configuration from execution and supports safe evolution as new decisioning use cases are introduced.
