-- Add not null in decisions.type
ALTER TABLE decisions MODIFY COLUMN type VARCHAR(50) NOT NULL;