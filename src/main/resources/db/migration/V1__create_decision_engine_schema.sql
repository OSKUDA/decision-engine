-- ============================================================
-- V1__create_decision_engine_schema.sql
-- Initial schema for Decision Engine
-- ============================================================

-- ------------------------------------------------------------
-- Table: decisions
-- ------------------------------------------------------------
CREATE TABLE decisions (
  decision_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  decision_key VARCHAR(100) NOT NULL UNIQUE,

  name VARCHAR(100) NOT NULL,
  description VARCHAR(255),
  type VARCHAR(50),

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100)
);

-- ------------------------------------------------------------
-- Table: outcomes
-- ------------------------------------------------------------
CREATE TABLE outcomes (
  outcome_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  outcome_key VARCHAR(100) NOT NULL UNIQUE,

  name VARCHAR(100),
  type VARCHAR(50) NOT NULL,
  payload JSON NOT NULL,

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100)
);

-- ------------------------------------------------------------
-- Table: facts
-- ------------------------------------------------------------
CREATE TABLE facts (
  fact_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  fact_key VARCHAR(100) NOT NULL UNIQUE,

  name VARCHAR(100),
  data_type ENUM('BOOLEAN','STRING','NUMBER','DATE') NOT NULL,

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100)
);

-- ------------------------------------------------------------
-- Table: rules
-- ------------------------------------------------------------
CREATE TABLE rules (
  rule_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  decision_id BIGINT NOT NULL,
  outcome_id BIGINT NOT NULL,

  priority INT NOT NULL,
  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100),

  CONSTRAINT fk_rules_decision
    FOREIGN KEY (decision_id)
    REFERENCES decisions(decision_id),

  CONSTRAINT fk_rules_outcome
    FOREIGN KEY (outcome_id)
    REFERENCES outcomes(outcome_id)
);

-- ------------------------------------------------------------
-- Table: rule_conditions
-- ------------------------------------------------------------
CREATE TABLE rule_conditions (
  rule_con_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  rule_id BIGINT NOT NULL,
  fact_id BIGINT NOT NULL,

  operator ENUM(
    'EQ',
    'NEQ',
    'IN',
    'NOT_IN',
    'GT',
    'LT'
  ) NOT NULL,

  expected_value VARCHAR(255) NOT NULL,

  is_active BOOLEAN DEFAULT TRUE,

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(100),
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
  modified_by VARCHAR(100),

  CONSTRAINT fk_rule_conditions_rule
    FOREIGN KEY (rule_id)
    REFERENCES rules(rule_id),

  CONSTRAINT fk_rule_conditions_fact
    FOREIGN KEY (fact_id)
    REFERENCES facts(fact_id)
);

-- ============================================================
-- End of V1 schema
-- ============================================================