-- 1. Habilita a extensão do TimescaleDB
CREATE EXTENSION IF NOT EXISTS timescaledb;

-- 2. Cria a tabela normal do PostgreSQL
CREATE TABLE registro_medicao (
                        id UUID,
                        cpf_paciente VARCHAR(11) NOT NULL,
                        id_evento UUID NOT NULL,
                        valor_medicao DECIMAL(10,2) NOT NULL,
                        data_hora TIMESTAMPTZ NOT NULL,
                        origem_registro VARCHAR(20) NOT NULL,
                        PRIMARY KEY (id, data_hora)
);

-- 3. Transforma a tabela normal em uma hypertable do TimescaleDB
SELECT create_hypertable('registro_medicao', by_range('data_hora'));